package org.gnenc.yams.service.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.ValidationException;

import org.apache.log4j.Logger;

import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.operation.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
//import com.liferay.mail.service.MailService;

/**
 *
 * @author Jeshurun Daniel
 *
 */
@Service("operationExecutionManager")
public class ExecutionManager {

	private final Map<Class<? extends Operation>, CountDownLatch> concurrentExceutionLatch =
			Collections.synchronizedMap(new HashMap<Class<? extends Operation>, CountDownLatch>());

	private final ExecutorService pooledExecutor = Executors.newCachedThreadPool();

	private static final Logger logger = Logger.getLogger(ExecutionManager.class);

	private static final int EXECUTION_TIMEOUT_SECONDS = 60;

	@Autowired
	private ApplicationContext context;

//	@Autowired
//	private MailService mailService;

//	@Value("${warehouse.is_production_mode}")
	private boolean isProductionMode = true;

	/**
	 *
	 * @param type the type of operation to invoke
	 * @param subsystems the subsystems on which this operation should be invoked
	 * @param callback
	 * @param concurrentExecutionAllowed
	 *
	 * @throws ConcurrentModificationException if this operation disallows concurrent executions
	 * @throws ValidationException if this operation must be validated before execution and the validations did not complete normally.
	 * @throws AppException if an unrecoverable system exception occurred during an operation's execution
	 */
	public <T extends Operation> void execute(final Class<T> type, final List<SubSystem> subsystems,
			final ExecutionCallback<T> callback, boolean concurrentExecutionAllowed) throws ValidationException {

		final CountDownLatch executionLatch = new CountDownLatch(subsystems.size());

		if (!concurrentExecutionAllowed) {
			synchronized(concurrentExceutionLatch) {
				if (concurrentExceutionLatch.containsKey(type)) {
					throw new ConcurrentModificationException("Another operation of type " + type.getSimpleName() + " is already in progress.");
				}
				concurrentExceutionLatch.put(type, executionLatch);
			}
		}

		try {
			// Get all implementations of this action from the context
			final Map<String, T> actions = new HashMap<String, T>(context.getBeansOfType(type));

			// Remove the ones that weren't requested
			for (String key : new HashSet<String>(actions.keySet())) {
				if (!subsystems.contains(actions.get(key).getSubsystemType())) {
					actions.remove(key);
				}
			}

			// validate here or forever hold your peace
			// !!!!!!! Use callable here instead of runnable as we want exceptions thrown to
			// propogate back to the parent thread!!!!!!!!!!!!!!
			// see here http://stackoverflow.com/questions/2248131/handling-exceptions-from-java-executorservice-tasks
			if (callback instanceof ValidatedExecutionCallback) {

				final CountDownLatch validationLatch = new CountDownLatch(actions.size());
				final List<Callable<Void>> validationActions = new ArrayList<Callable<Void>>();
				for (final T action : actions.values()) {
					validationActions.add(new Callable<Void>() {
						
						public Void call() throws Exception {
							((ValidatedExecutionCallback<T>) callback).validateAction(action);
							validationLatch.countDown();
							return null;
						}
					});
				}

				try {
					for (final Future<Void> result : pooledExecutor.invokeAll(validationActions)) {
						try {
							result.get();
						} catch (ExecutionException e) {
							if (e.getCause() instanceof ValidationException)
								throw (ValidationException) e.getCause();
							else throw new ValidationException(e.getCause());
						}
					}

					validationLatch.await(10, TimeUnit.SECONDS);

					// last resort. this code should never have to execute.
					if (validationLatch.getCount() > 0) {
						throw new ValidationException("The operation did not validate successfully.");
					}
				} catch (InterruptedException e) {
					logger.error("An exception occurred when waiting for validators to terminate.", e);
					// we don't want to proceed at this point.
					throw new ValidationException("The operation did not validate successfully.", e);
				}

			}

			/*
			 * The latch is first initialized to the size of subsystems requested.
			 * Since the list of actions can be less than this size, we adjust the latch before
			 * to the correct size before proceeding to execute the operations.
			 */
			while (executionLatch.getCount() > actions.size()) {
				executionLatch.countDown();
			}

			// if we got to this point, we assume that all validations, if any, were successful.
			for (final T action : actions.values()) {
				pooledExecutor.execute(new Runnable() {
					
					public void run() {
						try {
							callback.executeAction(action);
							executionLatch.countDown();
						} catch (Exception e) {
							e.printStackTrace();
							if (isProductionMode) {
								final String NEW_LINE = "<br />";
								final StringBuilder sb = new StringBuilder(e.getMessage() != null ? e.getMessage() : "").append(NEW_LINE);
								sb.append(e.getClass().getName());

								for (StackTraceElement element : e.getStackTrace() ) {
									if (element != null) {
										sb.append( element );
										sb.append( NEW_LINE );
									}
								 }

//								mailService.sendErrorMail(sb.toString());
							}
						}
					}
				});
			}

			try {
				executionLatch.await(EXECUTION_TIMEOUT_SECONDS, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				logger.error("An exception occurred when waiting for all actions to terminate.", e);
				// we don't want to proceed at this point.
//				throw new AppException("The operation did not complete successfully.", e);
			}

		} finally {
			concurrentExceutionLatch.remove(type);
		}
	}

	/**
	 * Computes and returns an approximate percentage of completion for an operation of a given type.
	 * Note that for operations that are allowed to execute concurrently,
	 * the results of this method are indeterministic.
	 *
	 * @param operation
	 * @return
	 */
	public <T extends Operation> int getProgress(Class<T> operation) {
		if (!concurrentExceutionLatch.containsKey(operation)) {
			return 100;
		}
		// we can't reliably determine which subsystems this operation was initially invoked on, so we assume all available subsystems.
		// TODO store initially invoked subsystems in the latch so it can be used here.
		return (int) (Math.abs((SubSystem.values().length - concurrentExceutionLatch.get(operation).getCount())) * 100);
	}

}