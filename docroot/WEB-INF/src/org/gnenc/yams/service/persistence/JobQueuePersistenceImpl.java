/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.gnenc.yams.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.gnenc.yams.NoSuchJobQueueException;
import org.gnenc.yams.model.JobQueue;
import org.gnenc.yams.model.impl.JobQueueImpl;
import org.gnenc.yams.model.impl.JobQueueModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the job queue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Drew A. Blessing
 * @see JobQueuePersistence
 * @see JobQueueUtil
 * @generated
 */
public class JobQueuePersistenceImpl extends BasePersistenceImpl<JobQueue>
	implements JobQueuePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JobQueueUtil} to access the job queue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JobQueueImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBACTION =
		new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, JobQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByJobAction",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBACTION =
		new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, JobQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByJobAction",
			new String[] { String.class.getName() },
			JobQueueModelImpl.JOBACTION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JOBACTION = new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByJobAction",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBDATEANDJOBACTION =
		new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, JobQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByJobDateAndJobAction",
			new String[] {
				Date.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBDATEANDJOBACTION =
		new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, JobQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByJobDateAndJobAction",
			new String[] { Date.class.getName(), String.class.getName() },
			JobQueueModelImpl.JOBDATE_COLUMN_BITMASK |
			JobQueueModelImpl.JOBACTION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JOBDATEANDJOBACTION = new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByJobDateAndJobAction",
			new String[] { Date.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBUSEREMAILADDRESS =
		new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, JobQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByJobUserEmailAddress",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUSEREMAILADDRESS =
		new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, JobQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByJobUserEmailAddress",
			new String[] { String.class.getName() },
			JobQueueModelImpl.JOBUSEREMAILADDRESS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JOBUSEREMAILADDRESS = new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByJobUserEmailAddress",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBUSEREMAILADDRESSANDJOBACTION =
		new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, JobQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByJobUserEmailAddressAndJobAction",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUSEREMAILADDRESSANDJOBACTION =
		new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, JobQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByJobUserEmailAddressAndJobAction",
			new String[] { String.class.getName(), String.class.getName() },
			JobQueueModelImpl.JOBUSEREMAILADDRESS_COLUMN_BITMASK |
			JobQueueModelImpl.JOBACTION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JOBUSEREMAILADDRESSANDJOBACTION =
		new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByJobUserEmailAddressAndJobAction",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, JobQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, JobQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the job queue in the entity cache if it is enabled.
	 *
	 * @param jobQueue the job queue
	 */
	public void cacheResult(JobQueue jobQueue) {
		EntityCacheUtil.putResult(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueImpl.class, jobQueue.getPrimaryKey(), jobQueue);

		jobQueue.resetOriginalValues();
	}

	/**
	 * Caches the job queues in the entity cache if it is enabled.
	 *
	 * @param jobQueues the job queues
	 */
	public void cacheResult(List<JobQueue> jobQueues) {
		for (JobQueue jobQueue : jobQueues) {
			if (EntityCacheUtil.getResult(
						JobQueueModelImpl.ENTITY_CACHE_ENABLED,
						JobQueueImpl.class, jobQueue.getPrimaryKey()) == null) {
				cacheResult(jobQueue);
			}
			else {
				jobQueue.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all job queues.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(JobQueueImpl.class.getName());
		}

		EntityCacheUtil.clearCache(JobQueueImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the job queue.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JobQueue jobQueue) {
		EntityCacheUtil.removeResult(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueImpl.class, jobQueue.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<JobQueue> jobQueues) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JobQueue jobQueue : jobQueues) {
			EntityCacheUtil.removeResult(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
				JobQueueImpl.class, jobQueue.getPrimaryKey());
		}
	}

	/**
	 * Creates a new job queue with the primary key. Does not add the job queue to the database.
	 *
	 * @param id the primary key for the new job queue
	 * @return the new job queue
	 */
	public JobQueue create(long id) {
		JobQueue jobQueue = new JobQueueImpl();

		jobQueue.setNew(true);
		jobQueue.setPrimaryKey(id);

		return jobQueue;
	}

	/**
	 * Removes the job queue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the job queue
	 * @return the job queue that was removed
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue remove(long id)
		throws NoSuchJobQueueException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the job queue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the job queue
	 * @return the job queue that was removed
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JobQueue remove(Serializable primaryKey)
		throws NoSuchJobQueueException, SystemException {
		Session session = null;

		try {
			session = openSession();

			JobQueue jobQueue = (JobQueue)session.get(JobQueueImpl.class,
					primaryKey);

			if (jobQueue == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJobQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(jobQueue);
		}
		catch (NoSuchJobQueueException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected JobQueue removeImpl(JobQueue jobQueue) throws SystemException {
		jobQueue = toUnwrappedModel(jobQueue);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, jobQueue);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(jobQueue);

		return jobQueue;
	}

	@Override
	public JobQueue updateImpl(org.gnenc.yams.model.JobQueue jobQueue,
		boolean merge) throws SystemException {
		jobQueue = toUnwrappedModel(jobQueue);

		boolean isNew = jobQueue.isNew();

		JobQueueModelImpl jobQueueModelImpl = (JobQueueModelImpl)jobQueue;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, jobQueue, merge);

			jobQueue.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !JobQueueModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((jobQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBACTION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobQueueModelImpl.getOriginalJobAction()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JOBACTION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBACTION,
					args);

				args = new Object[] { jobQueueModelImpl.getJobAction() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JOBACTION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBACTION,
					args);
			}

			if ((jobQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBDATEANDJOBACTION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobQueueModelImpl.getOriginalJobDate(),
						
						jobQueueModelImpl.getOriginalJobAction()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JOBDATEANDJOBACTION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBDATEANDJOBACTION,
					args);

				args = new Object[] {
						jobQueueModelImpl.getJobDate(),
						
						jobQueueModelImpl.getJobAction()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JOBDATEANDJOBACTION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBDATEANDJOBACTION,
					args);
			}

			if ((jobQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUSEREMAILADDRESS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobQueueModelImpl.getOriginalJobUserEmailAddress()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JOBUSEREMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUSEREMAILADDRESS,
					args);

				args = new Object[] { jobQueueModelImpl.getJobUserEmailAddress() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JOBUSEREMAILADDRESS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUSEREMAILADDRESS,
					args);
			}

			if ((jobQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUSEREMAILADDRESSANDJOBACTION.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jobQueueModelImpl.getOriginalJobUserEmailAddress(),
						
						jobQueueModelImpl.getOriginalJobAction()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JOBUSEREMAILADDRESSANDJOBACTION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUSEREMAILADDRESSANDJOBACTION,
					args);

				args = new Object[] {
						jobQueueModelImpl.getJobUserEmailAddress(),
						
						jobQueueModelImpl.getJobAction()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JOBUSEREMAILADDRESSANDJOBACTION,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUSEREMAILADDRESSANDJOBACTION,
					args);
			}
		}

		EntityCacheUtil.putResult(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
			JobQueueImpl.class, jobQueue.getPrimaryKey(), jobQueue);

		return jobQueue;
	}

	protected JobQueue toUnwrappedModel(JobQueue jobQueue) {
		if (jobQueue instanceof JobQueueImpl) {
			return jobQueue;
		}

		JobQueueImpl jobQueueImpl = new JobQueueImpl();

		jobQueueImpl.setNew(jobQueue.isNew());
		jobQueueImpl.setPrimaryKey(jobQueue.getPrimaryKey());

		jobQueueImpl.setId(jobQueue.getId());
		jobQueueImpl.setCompanyId(jobQueue.getCompanyId());
		jobQueueImpl.setUserId(jobQueue.getUserId());
		jobQueueImpl.setUserName(jobQueue.getUserName());
		jobQueueImpl.setModifiedDate(jobQueue.getModifiedDate());
		jobQueueImpl.setUserEmailAddress(jobQueue.getUserEmailAddress());
		jobQueueImpl.setJobUserId(jobQueue.getJobUserId());
		jobQueueImpl.setJobUserName(jobQueue.getJobUserName());
		jobQueueImpl.setJobUserEmailAddress(jobQueue.getJobUserEmailAddress());
		jobQueueImpl.setJobDescription(jobQueue.getJobDescription());
		jobQueueImpl.setJobAction(jobQueue.getJobAction());
		jobQueueImpl.setJobDate(jobQueue.getJobDate());

		return jobQueueImpl;
	}

	/**
	 * Returns the job queue with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the job queue
	 * @return the job queue
	 * @throws com.liferay.portal.NoSuchModelException if a job queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JobQueue findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the job queue with the primary key or throws a {@link org.gnenc.yams.NoSuchJobQueueException} if it could not be found.
	 *
	 * @param id the primary key of the job queue
	 * @return the job queue
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue findByPrimaryKey(long id)
		throws NoSuchJobQueueException, SystemException {
		JobQueue jobQueue = fetchByPrimaryKey(id);

		if (jobQueue == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchJobQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return jobQueue;
	}

	/**
	 * Returns the job queue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the job queue
	 * @return the job queue, or <code>null</code> if a job queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JobQueue fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the job queue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the job queue
	 * @return the job queue, or <code>null</code> if a job queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue fetchByPrimaryKey(long id) throws SystemException {
		JobQueue jobQueue = (JobQueue)EntityCacheUtil.getResult(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
				JobQueueImpl.class, id);

		if (jobQueue == _nullJobQueue) {
			return null;
		}

		if (jobQueue == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				jobQueue = (JobQueue)session.get(JobQueueImpl.class,
						Long.valueOf(id));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (jobQueue != null) {
					cacheResult(jobQueue);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(JobQueueModelImpl.ENTITY_CACHE_ENABLED,
						JobQueueImpl.class, id, _nullJobQueue);
				}

				closeSession(session);
			}
		}

		return jobQueue;
	}

	/**
	 * Returns all the job queues where jobAction = &#63;.
	 *
	 * @param jobAction the job action
	 * @return the matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findByJobAction(String jobAction)
		throws SystemException {
		return findByJobAction(jobAction, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the job queues where jobAction = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jobAction the job action
	 * @param start the lower bound of the range of job queues
	 * @param end the upper bound of the range of job queues (not inclusive)
	 * @return the range of matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findByJobAction(String jobAction, int start, int end)
		throws SystemException {
		return findByJobAction(jobAction, start, end, null);
	}

	/**
	 * Returns an ordered range of all the job queues where jobAction = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jobAction the job action
	 * @param start the lower bound of the range of job queues
	 * @param end the upper bound of the range of job queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findByJobAction(String jobAction, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBACTION;
			finderArgs = new Object[] { jobAction };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBACTION;
			finderArgs = new Object[] { jobAction, start, end, orderByComparator };
		}

		List<JobQueue> list = (List<JobQueue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JobQueue jobQueue : list) {
				if (!Validator.equals(jobAction, jobQueue.getJobAction())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_JOBQUEUE_WHERE);

			if (jobAction == null) {
				query.append(_FINDER_COLUMN_JOBACTION_JOBACTION_1);
			}
			else {
				if (jobAction.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JOBACTION_JOBACTION_3);
				}
				else {
					query.append(_FINDER_COLUMN_JOBACTION_JOBACTION_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JobQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (jobAction != null) {
					qPos.add(jobAction);
				}

				list = (List<JobQueue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first job queue in the ordered set where jobAction = &#63;.
	 *
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job queue
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue findByJobAction_First(String jobAction,
		OrderByComparator orderByComparator)
		throws NoSuchJobQueueException, SystemException {
		JobQueue jobQueue = fetchByJobAction_First(jobAction, orderByComparator);

		if (jobQueue != null) {
			return jobQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobAction=");
		msg.append(jobAction);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJobQueueException(msg.toString());
	}

	/**
	 * Returns the first job queue in the ordered set where jobAction = &#63;.
	 *
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job queue, or <code>null</code> if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue fetchByJobAction_First(String jobAction,
		OrderByComparator orderByComparator) throws SystemException {
		List<JobQueue> list = findByJobAction(jobAction, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job queue in the ordered set where jobAction = &#63;.
	 *
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job queue
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue findByJobAction_Last(String jobAction,
		OrderByComparator orderByComparator)
		throws NoSuchJobQueueException, SystemException {
		JobQueue jobQueue = fetchByJobAction_Last(jobAction, orderByComparator);

		if (jobQueue != null) {
			return jobQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobAction=");
		msg.append(jobAction);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJobQueueException(msg.toString());
	}

	/**
	 * Returns the last job queue in the ordered set where jobAction = &#63;.
	 *
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job queue, or <code>null</code> if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue fetchByJobAction_Last(String jobAction,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByJobAction(jobAction);

		List<JobQueue> list = findByJobAction(jobAction, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the job queues before and after the current job queue in the ordered set where jobAction = &#63;.
	 *
	 * @param id the primary key of the current job queue
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job queue
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue[] findByJobAction_PrevAndNext(long id, String jobAction,
		OrderByComparator orderByComparator)
		throws NoSuchJobQueueException, SystemException {
		JobQueue jobQueue = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			JobQueue[] array = new JobQueueImpl[3];

			array[0] = getByJobAction_PrevAndNext(session, jobQueue, jobAction,
					orderByComparator, true);

			array[1] = jobQueue;

			array[2] = getByJobAction_PrevAndNext(session, jobQueue, jobAction,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JobQueue getByJobAction_PrevAndNext(Session session,
		JobQueue jobQueue, String jobAction,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOBQUEUE_WHERE);

		if (jobAction == null) {
			query.append(_FINDER_COLUMN_JOBACTION_JOBACTION_1);
		}
		else {
			if (jobAction.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JOBACTION_JOBACTION_3);
			}
			else {
				query.append(_FINDER_COLUMN_JOBACTION_JOBACTION_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(JobQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (jobAction != null) {
			qPos.add(jobAction);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jobQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JobQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the job queues where jobDate = &#63; and jobAction = &#63;.
	 *
	 * @param jobDate the job date
	 * @param jobAction the job action
	 * @return the matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findByJobDateAndJobAction(Date jobDate,
		String jobAction) throws SystemException {
		return findByJobDateAndJobAction(jobDate, jobAction, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the job queues where jobDate = &#63; and jobAction = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jobDate the job date
	 * @param jobAction the job action
	 * @param start the lower bound of the range of job queues
	 * @param end the upper bound of the range of job queues (not inclusive)
	 * @return the range of matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findByJobDateAndJobAction(Date jobDate,
		String jobAction, int start, int end) throws SystemException {
		return findByJobDateAndJobAction(jobDate, jobAction, start, end, null);
	}

	/**
	 * Returns an ordered range of all the job queues where jobDate = &#63; and jobAction = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jobDate the job date
	 * @param jobAction the job action
	 * @param start the lower bound of the range of job queues
	 * @param end the upper bound of the range of job queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findByJobDateAndJobAction(Date jobDate,
		String jobAction, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBDATEANDJOBACTION;
			finderArgs = new Object[] { jobDate, jobAction };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBDATEANDJOBACTION;
			finderArgs = new Object[] {
					jobDate, jobAction,
					
					start, end, orderByComparator
				};
		}

		List<JobQueue> list = (List<JobQueue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JobQueue jobQueue : list) {
				if (!Validator.equals(jobDate, jobQueue.getJobDate()) ||
						!Validator.equals(jobAction, jobQueue.getJobAction())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_JOBQUEUE_WHERE);

			if (jobDate == null) {
				query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBDATE_2);
			}

			if (jobAction == null) {
				query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBACTION_1);
			}
			else {
				if (jobAction.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBACTION_3);
				}
				else {
					query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBACTION_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JobQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (jobDate != null) {
					qPos.add(CalendarUtil.getTimestamp(jobDate));
				}

				if (jobAction != null) {
					qPos.add(jobAction);
				}

				list = (List<JobQueue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first job queue in the ordered set where jobDate = &#63; and jobAction = &#63;.
	 *
	 * @param jobDate the job date
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job queue
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue findByJobDateAndJobAction_First(Date jobDate,
		String jobAction, OrderByComparator orderByComparator)
		throws NoSuchJobQueueException, SystemException {
		JobQueue jobQueue = fetchByJobDateAndJobAction_First(jobDate,
				jobAction, orderByComparator);

		if (jobQueue != null) {
			return jobQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobDate=");
		msg.append(jobDate);

		msg.append(", jobAction=");
		msg.append(jobAction);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJobQueueException(msg.toString());
	}

	/**
	 * Returns the first job queue in the ordered set where jobDate = &#63; and jobAction = &#63;.
	 *
	 * @param jobDate the job date
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job queue, or <code>null</code> if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue fetchByJobDateAndJobAction_First(Date jobDate,
		String jobAction, OrderByComparator orderByComparator)
		throws SystemException {
		List<JobQueue> list = findByJobDateAndJobAction(jobDate, jobAction, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job queue in the ordered set where jobDate = &#63; and jobAction = &#63;.
	 *
	 * @param jobDate the job date
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job queue
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue findByJobDateAndJobAction_Last(Date jobDate,
		String jobAction, OrderByComparator orderByComparator)
		throws NoSuchJobQueueException, SystemException {
		JobQueue jobQueue = fetchByJobDateAndJobAction_Last(jobDate, jobAction,
				orderByComparator);

		if (jobQueue != null) {
			return jobQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobDate=");
		msg.append(jobDate);

		msg.append(", jobAction=");
		msg.append(jobAction);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJobQueueException(msg.toString());
	}

	/**
	 * Returns the last job queue in the ordered set where jobDate = &#63; and jobAction = &#63;.
	 *
	 * @param jobDate the job date
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job queue, or <code>null</code> if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue fetchByJobDateAndJobAction_Last(Date jobDate,
		String jobAction, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByJobDateAndJobAction(jobDate, jobAction);

		List<JobQueue> list = findByJobDateAndJobAction(jobDate, jobAction,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the job queues before and after the current job queue in the ordered set where jobDate = &#63; and jobAction = &#63;.
	 *
	 * @param id the primary key of the current job queue
	 * @param jobDate the job date
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job queue
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue[] findByJobDateAndJobAction_PrevAndNext(long id,
		Date jobDate, String jobAction, OrderByComparator orderByComparator)
		throws NoSuchJobQueueException, SystemException {
		JobQueue jobQueue = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			JobQueue[] array = new JobQueueImpl[3];

			array[0] = getByJobDateAndJobAction_PrevAndNext(session, jobQueue,
					jobDate, jobAction, orderByComparator, true);

			array[1] = jobQueue;

			array[2] = getByJobDateAndJobAction_PrevAndNext(session, jobQueue,
					jobDate, jobAction, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JobQueue getByJobDateAndJobAction_PrevAndNext(Session session,
		JobQueue jobQueue, Date jobDate, String jobAction,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOBQUEUE_WHERE);

		if (jobDate == null) {
			query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBDATE_2);
		}

		if (jobAction == null) {
			query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBACTION_1);
		}
		else {
			if (jobAction.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBACTION_3);
			}
			else {
				query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBACTION_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(JobQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (jobDate != null) {
			qPos.add(CalendarUtil.getTimestamp(jobDate));
		}

		if (jobAction != null) {
			qPos.add(jobAction);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jobQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JobQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the job queues where jobUserEmailAddress = &#63;.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @return the matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findByJobUserEmailAddress(String jobUserEmailAddress)
		throws SystemException {
		return findByJobUserEmailAddress(jobUserEmailAddress,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the job queues where jobUserEmailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param start the lower bound of the range of job queues
	 * @param end the upper bound of the range of job queues (not inclusive)
	 * @return the range of matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findByJobUserEmailAddress(
		String jobUserEmailAddress, int start, int end)
		throws SystemException {
		return findByJobUserEmailAddress(jobUserEmailAddress, start, end, null);
	}

	/**
	 * Returns an ordered range of all the job queues where jobUserEmailAddress = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param start the lower bound of the range of job queues
	 * @param end the upper bound of the range of job queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findByJobUserEmailAddress(
		String jobUserEmailAddress, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUSEREMAILADDRESS;
			finderArgs = new Object[] { jobUserEmailAddress };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBUSEREMAILADDRESS;
			finderArgs = new Object[] {
					jobUserEmailAddress,
					
					start, end, orderByComparator
				};
		}

		List<JobQueue> list = (List<JobQueue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JobQueue jobQueue : list) {
				if (!Validator.equals(jobUserEmailAddress,
							jobQueue.getJobUserEmailAddress())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_JOBQUEUE_WHERE);

			if (jobUserEmailAddress == null) {
				query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESS_JOBUSEREMAILADDRESS_1);
			}
			else {
				if (jobUserEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESS_JOBUSEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESS_JOBUSEREMAILADDRESS_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JobQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (jobUserEmailAddress != null) {
					qPos.add(jobUserEmailAddress);
				}

				list = (List<JobQueue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first job queue in the ordered set where jobUserEmailAddress = &#63;.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job queue
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue findByJobUserEmailAddress_First(
		String jobUserEmailAddress, OrderByComparator orderByComparator)
		throws NoSuchJobQueueException, SystemException {
		JobQueue jobQueue = fetchByJobUserEmailAddress_First(jobUserEmailAddress,
				orderByComparator);

		if (jobQueue != null) {
			return jobQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobUserEmailAddress=");
		msg.append(jobUserEmailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJobQueueException(msg.toString());
	}

	/**
	 * Returns the first job queue in the ordered set where jobUserEmailAddress = &#63;.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job queue, or <code>null</code> if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue fetchByJobUserEmailAddress_First(
		String jobUserEmailAddress, OrderByComparator orderByComparator)
		throws SystemException {
		List<JobQueue> list = findByJobUserEmailAddress(jobUserEmailAddress, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job queue in the ordered set where jobUserEmailAddress = &#63;.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job queue
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue findByJobUserEmailAddress_Last(String jobUserEmailAddress,
		OrderByComparator orderByComparator)
		throws NoSuchJobQueueException, SystemException {
		JobQueue jobQueue = fetchByJobUserEmailAddress_Last(jobUserEmailAddress,
				orderByComparator);

		if (jobQueue != null) {
			return jobQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobUserEmailAddress=");
		msg.append(jobUserEmailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJobQueueException(msg.toString());
	}

	/**
	 * Returns the last job queue in the ordered set where jobUserEmailAddress = &#63;.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job queue, or <code>null</code> if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue fetchByJobUserEmailAddress_Last(
		String jobUserEmailAddress, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByJobUserEmailAddress(jobUserEmailAddress);

		List<JobQueue> list = findByJobUserEmailAddress(jobUserEmailAddress,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the job queues before and after the current job queue in the ordered set where jobUserEmailAddress = &#63;.
	 *
	 * @param id the primary key of the current job queue
	 * @param jobUserEmailAddress the job user email address
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job queue
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue[] findByJobUserEmailAddress_PrevAndNext(long id,
		String jobUserEmailAddress, OrderByComparator orderByComparator)
		throws NoSuchJobQueueException, SystemException {
		JobQueue jobQueue = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			JobQueue[] array = new JobQueueImpl[3];

			array[0] = getByJobUserEmailAddress_PrevAndNext(session, jobQueue,
					jobUserEmailAddress, orderByComparator, true);

			array[1] = jobQueue;

			array[2] = getByJobUserEmailAddress_PrevAndNext(session, jobQueue,
					jobUserEmailAddress, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JobQueue getByJobUserEmailAddress_PrevAndNext(Session session,
		JobQueue jobQueue, String jobUserEmailAddress,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOBQUEUE_WHERE);

		if (jobUserEmailAddress == null) {
			query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESS_JOBUSEREMAILADDRESS_1);
		}
		else {
			if (jobUserEmailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESS_JOBUSEREMAILADDRESS_3);
			}
			else {
				query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESS_JOBUSEREMAILADDRESS_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(JobQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (jobUserEmailAddress != null) {
			qPos.add(jobUserEmailAddress);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jobQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JobQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the job queues where jobUserEmailAddress = &#63; and jobAction = &#63;.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param jobAction the job action
	 * @return the matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findByJobUserEmailAddressAndJobAction(
		String jobUserEmailAddress, String jobAction) throws SystemException {
		return findByJobUserEmailAddressAndJobAction(jobUserEmailAddress,
			jobAction, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the job queues where jobUserEmailAddress = &#63; and jobAction = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param jobAction the job action
	 * @param start the lower bound of the range of job queues
	 * @param end the upper bound of the range of job queues (not inclusive)
	 * @return the range of matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findByJobUserEmailAddressAndJobAction(
		String jobUserEmailAddress, String jobAction, int start, int end)
		throws SystemException {
		return findByJobUserEmailAddressAndJobAction(jobUserEmailAddress,
			jobAction, start, end, null);
	}

	/**
	 * Returns an ordered range of all the job queues where jobUserEmailAddress = &#63; and jobAction = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param jobAction the job action
	 * @param start the lower bound of the range of job queues
	 * @param end the upper bound of the range of job queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findByJobUserEmailAddressAndJobAction(
		String jobUserEmailAddress, String jobAction, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JOBUSEREMAILADDRESSANDJOBACTION;
			finderArgs = new Object[] { jobUserEmailAddress, jobAction };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_JOBUSEREMAILADDRESSANDJOBACTION;
			finderArgs = new Object[] {
					jobUserEmailAddress, jobAction,
					
					start, end, orderByComparator
				};
		}

		List<JobQueue> list = (List<JobQueue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JobQueue jobQueue : list) {
				if (!Validator.equals(jobUserEmailAddress,
							jobQueue.getJobUserEmailAddress()) ||
						!Validator.equals(jobAction, jobQueue.getJobAction())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_JOBQUEUE_WHERE);

			if (jobUserEmailAddress == null) {
				query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBUSEREMAILADDRESS_1);
			}
			else {
				if (jobUserEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBUSEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBUSEREMAILADDRESS_2);
				}
			}

			if (jobAction == null) {
				query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBACTION_1);
			}
			else {
				if (jobAction.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBACTION_3);
				}
				else {
					query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBACTION_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(JobQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (jobUserEmailAddress != null) {
					qPos.add(jobUserEmailAddress);
				}

				if (jobAction != null) {
					qPos.add(jobAction);
				}

				list = (List<JobQueue>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first job queue in the ordered set where jobUserEmailAddress = &#63; and jobAction = &#63;.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job queue
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue findByJobUserEmailAddressAndJobAction_First(
		String jobUserEmailAddress, String jobAction,
		OrderByComparator orderByComparator)
		throws NoSuchJobQueueException, SystemException {
		JobQueue jobQueue = fetchByJobUserEmailAddressAndJobAction_First(jobUserEmailAddress,
				jobAction, orderByComparator);

		if (jobQueue != null) {
			return jobQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobUserEmailAddress=");
		msg.append(jobUserEmailAddress);

		msg.append(", jobAction=");
		msg.append(jobAction);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJobQueueException(msg.toString());
	}

	/**
	 * Returns the first job queue in the ordered set where jobUserEmailAddress = &#63; and jobAction = &#63;.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching job queue, or <code>null</code> if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue fetchByJobUserEmailAddressAndJobAction_First(
		String jobUserEmailAddress, String jobAction,
		OrderByComparator orderByComparator) throws SystemException {
		List<JobQueue> list = findByJobUserEmailAddressAndJobAction(jobUserEmailAddress,
				jobAction, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last job queue in the ordered set where jobUserEmailAddress = &#63; and jobAction = &#63;.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job queue
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue findByJobUserEmailAddressAndJobAction_Last(
		String jobUserEmailAddress, String jobAction,
		OrderByComparator orderByComparator)
		throws NoSuchJobQueueException, SystemException {
		JobQueue jobQueue = fetchByJobUserEmailAddressAndJobAction_Last(jobUserEmailAddress,
				jobAction, orderByComparator);

		if (jobQueue != null) {
			return jobQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jobUserEmailAddress=");
		msg.append(jobUserEmailAddress);

		msg.append(", jobAction=");
		msg.append(jobAction);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJobQueueException(msg.toString());
	}

	/**
	 * Returns the last job queue in the ordered set where jobUserEmailAddress = &#63; and jobAction = &#63;.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching job queue, or <code>null</code> if a matching job queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue fetchByJobUserEmailAddressAndJobAction_Last(
		String jobUserEmailAddress, String jobAction,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByJobUserEmailAddressAndJobAction(jobUserEmailAddress,
				jobAction);

		List<JobQueue> list = findByJobUserEmailAddressAndJobAction(jobUserEmailAddress,
				jobAction, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the job queues before and after the current job queue in the ordered set where jobUserEmailAddress = &#63; and jobAction = &#63;.
	 *
	 * @param id the primary key of the current job queue
	 * @param jobUserEmailAddress the job user email address
	 * @param jobAction the job action
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next job queue
	 * @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JobQueue[] findByJobUserEmailAddressAndJobAction_PrevAndNext(
		long id, String jobUserEmailAddress, String jobAction,
		OrderByComparator orderByComparator)
		throws NoSuchJobQueueException, SystemException {
		JobQueue jobQueue = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			JobQueue[] array = new JobQueueImpl[3];

			array[0] = getByJobUserEmailAddressAndJobAction_PrevAndNext(session,
					jobQueue, jobUserEmailAddress, jobAction,
					orderByComparator, true);

			array[1] = jobQueue;

			array[2] = getByJobUserEmailAddressAndJobAction_PrevAndNext(session,
					jobQueue, jobUserEmailAddress, jobAction,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JobQueue getByJobUserEmailAddressAndJobAction_PrevAndNext(
		Session session, JobQueue jobQueue, String jobUserEmailAddress,
		String jobAction, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOBQUEUE_WHERE);

		if (jobUserEmailAddress == null) {
			query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBUSEREMAILADDRESS_1);
		}
		else {
			if (jobUserEmailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBUSEREMAILADDRESS_3);
			}
			else {
				query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBUSEREMAILADDRESS_2);
			}
		}

		if (jobAction == null) {
			query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBACTION_1);
		}
		else {
			if (jobAction.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBACTION_3);
			}
			else {
				query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBACTION_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(JobQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (jobUserEmailAddress != null) {
			qPos.add(jobUserEmailAddress);
		}

		if (jobAction != null) {
			qPos.add(jobAction);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jobQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JobQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the job queues.
	 *
	 * @return the job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the job queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of job queues
	 * @param end the upper bound of the range of job queues (not inclusive)
	 * @return the range of job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the job queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of job queues
	 * @param end the upper bound of the range of job queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of job queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<JobQueue> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<JobQueue> list = (List<JobQueue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_JOBQUEUE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JOBQUEUE.concat(JobQueueModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<JobQueue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<JobQueue>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the job queues where jobAction = &#63; from the database.
	 *
	 * @param jobAction the job action
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByJobAction(String jobAction) throws SystemException {
		for (JobQueue jobQueue : findByJobAction(jobAction)) {
			remove(jobQueue);
		}
	}

	/**
	 * Removes all the job queues where jobDate = &#63; and jobAction = &#63; from the database.
	 *
	 * @param jobDate the job date
	 * @param jobAction the job action
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByJobDateAndJobAction(Date jobDate, String jobAction)
		throws SystemException {
		for (JobQueue jobQueue : findByJobDateAndJobAction(jobDate, jobAction)) {
			remove(jobQueue);
		}
	}

	/**
	 * Removes all the job queues where jobUserEmailAddress = &#63; from the database.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByJobUserEmailAddress(String jobUserEmailAddress)
		throws SystemException {
		for (JobQueue jobQueue : findByJobUserEmailAddress(jobUserEmailAddress)) {
			remove(jobQueue);
		}
	}

	/**
	 * Removes all the job queues where jobUserEmailAddress = &#63; and jobAction = &#63; from the database.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param jobAction the job action
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByJobUserEmailAddressAndJobAction(
		String jobUserEmailAddress, String jobAction) throws SystemException {
		for (JobQueue jobQueue : findByJobUserEmailAddressAndJobAction(
				jobUserEmailAddress, jobAction)) {
			remove(jobQueue);
		}
	}

	/**
	 * Removes all the job queues from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (JobQueue jobQueue : findAll()) {
			remove(jobQueue);
		}
	}

	/**
	 * Returns the number of job queues where jobAction = &#63;.
	 *
	 * @param jobAction the job action
	 * @return the number of matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByJobAction(String jobAction) throws SystemException {
		Object[] finderArgs = new Object[] { jobAction };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JOBACTION,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOBQUEUE_WHERE);

			if (jobAction == null) {
				query.append(_FINDER_COLUMN_JOBACTION_JOBACTION_1);
			}
			else {
				if (jobAction.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JOBACTION_JOBACTION_3);
				}
				else {
					query.append(_FINDER_COLUMN_JOBACTION_JOBACTION_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (jobAction != null) {
					qPos.add(jobAction);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JOBACTION,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of job queues where jobDate = &#63; and jobAction = &#63;.
	 *
	 * @param jobDate the job date
	 * @param jobAction the job action
	 * @return the number of matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByJobDateAndJobAction(Date jobDate, String jobAction)
		throws SystemException {
		Object[] finderArgs = new Object[] { jobDate, jobAction };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JOBDATEANDJOBACTION,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOBQUEUE_WHERE);

			if (jobDate == null) {
				query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBDATE_2);
			}

			if (jobAction == null) {
				query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBACTION_1);
			}
			else {
				if (jobAction.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBACTION_3);
				}
				else {
					query.append(_FINDER_COLUMN_JOBDATEANDJOBACTION_JOBACTION_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (jobDate != null) {
					qPos.add(CalendarUtil.getTimestamp(jobDate));
				}

				if (jobAction != null) {
					qPos.add(jobAction);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JOBDATEANDJOBACTION,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of job queues where jobUserEmailAddress = &#63;.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @return the number of matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByJobUserEmailAddress(String jobUserEmailAddress)
		throws SystemException {
		Object[] finderArgs = new Object[] { jobUserEmailAddress };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JOBUSEREMAILADDRESS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOBQUEUE_WHERE);

			if (jobUserEmailAddress == null) {
				query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESS_JOBUSEREMAILADDRESS_1);
			}
			else {
				if (jobUserEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESS_JOBUSEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESS_JOBUSEREMAILADDRESS_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (jobUserEmailAddress != null) {
					qPos.add(jobUserEmailAddress);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JOBUSEREMAILADDRESS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of job queues where jobUserEmailAddress = &#63; and jobAction = &#63;.
	 *
	 * @param jobUserEmailAddress the job user email address
	 * @param jobAction the job action
	 * @return the number of matching job queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByJobUserEmailAddressAndJobAction(
		String jobUserEmailAddress, String jobAction) throws SystemException {
		Object[] finderArgs = new Object[] { jobUserEmailAddress, jobAction };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JOBUSEREMAILADDRESSANDJOBACTION,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JOBQUEUE_WHERE);

			if (jobUserEmailAddress == null) {
				query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBUSEREMAILADDRESS_1);
			}
			else {
				if (jobUserEmailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBUSEREMAILADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBUSEREMAILADDRESS_2);
				}
			}

			if (jobAction == null) {
				query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBACTION_1);
			}
			else {
				if (jobAction.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBACTION_3);
				}
				else {
					query.append(_FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBACTION_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (jobUserEmailAddress != null) {
					qPos.add(jobUserEmailAddress);
				}

				if (jobAction != null) {
					qPos.add(jobAction);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JOBUSEREMAILADDRESSANDJOBACTION,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of job queues.
	 *
	 * @return the number of job queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JOBQUEUE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the job queue persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.gnenc.yams.model.JobQueue")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<JobQueue>> listenersList = new ArrayList<ModelListener<JobQueue>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<JobQueue>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(JobQueueImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = ActionLogPersistence.class)
	protected ActionLogPersistence actionLogPersistence;
	@BeanReference(type = JobQueuePersistence.class)
	protected JobQueuePersistence jobQueuePersistence;
	@BeanReference(type = PermissionsPersistence.class)
	protected PermissionsPersistence permissionsPersistence;
	@BeanReference(type = PermissionsDefinedPersistence.class)
	protected PermissionsDefinedPersistence permissionsDefinedPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_JOBQUEUE = "SELECT jobQueue FROM JobQueue jobQueue";
	private static final String _SQL_SELECT_JOBQUEUE_WHERE = "SELECT jobQueue FROM JobQueue jobQueue WHERE ";
	private static final String _SQL_COUNT_JOBQUEUE = "SELECT COUNT(jobQueue) FROM JobQueue jobQueue";
	private static final String _SQL_COUNT_JOBQUEUE_WHERE = "SELECT COUNT(jobQueue) FROM JobQueue jobQueue WHERE ";
	private static final String _FINDER_COLUMN_JOBACTION_JOBACTION_1 = "jobQueue.jobAction IS NULL";
	private static final String _FINDER_COLUMN_JOBACTION_JOBACTION_2 = "jobQueue.jobAction = ?";
	private static final String _FINDER_COLUMN_JOBACTION_JOBACTION_3 = "(jobQueue.jobAction IS NULL OR jobQueue.jobAction = ?)";
	private static final String _FINDER_COLUMN_JOBDATEANDJOBACTION_JOBDATE_1 = "jobQueue.jobDate IS NULL AND ";
	private static final String _FINDER_COLUMN_JOBDATEANDJOBACTION_JOBDATE_2 = "jobQueue.jobDate = ? AND ";
	private static final String _FINDER_COLUMN_JOBDATEANDJOBACTION_JOBACTION_1 = "jobQueue.jobAction IS NULL";
	private static final String _FINDER_COLUMN_JOBDATEANDJOBACTION_JOBACTION_2 = "jobQueue.jobAction = ?";
	private static final String _FINDER_COLUMN_JOBDATEANDJOBACTION_JOBACTION_3 = "(jobQueue.jobAction IS NULL OR jobQueue.jobAction = ?)";
	private static final String _FINDER_COLUMN_JOBUSEREMAILADDRESS_JOBUSEREMAILADDRESS_1 =
		"jobQueue.jobUserEmailAddress IS NULL";
	private static final String _FINDER_COLUMN_JOBUSEREMAILADDRESS_JOBUSEREMAILADDRESS_2 =
		"jobQueue.jobUserEmailAddress = ?";
	private static final String _FINDER_COLUMN_JOBUSEREMAILADDRESS_JOBUSEREMAILADDRESS_3 =
		"(jobQueue.jobUserEmailAddress IS NULL OR jobQueue.jobUserEmailAddress = ?)";
	private static final String _FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBUSEREMAILADDRESS_1 =
		"jobQueue.jobUserEmailAddress IS NULL AND ";
	private static final String _FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBUSEREMAILADDRESS_2 =
		"jobQueue.jobUserEmailAddress = ? AND ";
	private static final String _FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBUSEREMAILADDRESS_3 =
		"(jobQueue.jobUserEmailAddress IS NULL OR jobQueue.jobUserEmailAddress = ?) AND ";
	private static final String _FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBACTION_1 =
		"jobQueue.jobAction IS NULL";
	private static final String _FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBACTION_2 =
		"jobQueue.jobAction = ?";
	private static final String _FINDER_COLUMN_JOBUSEREMAILADDRESSANDJOBACTION_JOBACTION_3 =
		"(jobQueue.jobAction IS NULL OR jobQueue.jobAction = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jobQueue.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JobQueue exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JobQueue exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(JobQueuePersistenceImpl.class);
	private static JobQueue _nullJobQueue = new JobQueueImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<JobQueue> toCacheModel() {
				return _nullJobQueueCacheModel;
			}
		};

	private static CacheModel<JobQueue> _nullJobQueueCacheModel = new CacheModel<JobQueue>() {
			public JobQueue toEntityModel() {
				return _nullJobQueue;
			}
		};
}