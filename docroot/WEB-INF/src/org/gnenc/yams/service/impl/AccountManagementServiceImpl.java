package org.gnenc.yams.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.ValidationException;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.Group;
import org.gnenc.yams.model.GroupMap;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.operation.account.ChangePassword;
import org.gnenc.yams.operation.account.CheckAccountExists;
import org.gnenc.yams.operation.account.CreateAccount;
import org.gnenc.yams.operation.account.GetAllAccounts;
import org.gnenc.yams.operation.account.ModifyAccount;
import org.gnenc.yams.service.AccountManagementService;
import org.gnenc.yams.service.internal.ExecutionCallback;
import org.gnenc.yams.service.internal.ExecutionManager;
import org.gnenc.yams.service.internal.ValidatedExecutionCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liferay.portal.kernel.util.Validator;

/**
 * Based on the original AccountManagementService created by Jeshurun Daniel.
 *
 * @author Drew A. Blessing
 *
 */
@Service("accountManagementService")
public class AccountManagementServiceImpl implements AccountManagementService {
	private static final String UID_DISALLOWED_CHARS_REGEX = "[^a-zA-Z0-9-]";

	@Autowired
	private ExecutionManager executor;

	private static AccountManagementService instance;

//	@Autowired
//	private PasswordManager passwordManager;

//	@Autowired
//	private MessageService messages;

	public static AccountManagementService getInstance() {
		if (instance == null) {
			instance = new AccountManagementServiceImpl();
		}
		return instance;
	}

	protected AccountManagementServiceImpl() {
		instance = this;
	}
	
	@Override
	public List<SubSystem> checkAccountExists(final String accountUsername) 
			throws ValidationException {
		final List<SubSystem> subsystems = new ArrayList<SubSystem>();
		executor.execute(CheckAccountExists.class, 
				SubSystem.ALL_SUBSYSTEMS, new ExecutionCallback<CheckAccountExists>() {
					@Override
					public  void executeAction(CheckAccountExists operation) {
						if(operation.checkAccountExists(accountUsername)) {
							subsystems.add(operation.getSubsystemType());
						}
					}
				}, true);
		
		return subsystems;
	}

	@Override
	public void changePassword(final Account account, final String oldPassword,
			final String newPassword) throws ValidationException {

    	if (Validator.isNull(oldPassword) || Validator.isNull(newPassword)) {
//    		throw new ValidationException(messages.getMessage(18));
    	}

		final List<String> validationErrors = Collections.synchronizedList(new ArrayList<String>());

		executor.execute(ChangePassword.class, SubSystem.ALL_SUBSYSTEMS,
				new ValidatedExecutionCallback<ChangePassword>() {
					@Override
					public void validateAction(ChangePassword operation)
							throws ValidationException {
						operation.validateChangePassword(account, oldPassword, newPassword, validationErrors);
//						if (!validationErrors.isEmpty()) {
//							throw new ValidationException(validationErrors.toArray(new String[validationErrors.size()]));
//						}
					}

					@Override
					public void executeAction(ChangePassword operation) {
						operation.changePassword(account, newPassword);
					}
				}, false);
	}
	
	@Override
	public void changePassword(final Account account, final String newPassword) 
			throws ValidationException {
		final List<String> validationErrors = Collections.synchronizedList(new ArrayList<String>());
		
		executor.execute(ChangePassword.class, SubSystem.ALL_SUBSYSTEMS,
				new ValidatedExecutionCallback<ChangePassword>() {
					@Override
					public void validateAction(ChangePassword operation)
							throws ValidationException {
						operation.validateChangePassword(account, newPassword, validationErrors);
//						if (!validationErrors.isEmpty()) {
//							throw new ValidationException(validationErrors.toArray(new String[validationErrors.size()]));
//						}
					}

					@Override
					public void executeAction(ChangePassword operation) {
						operation.changePassword(account, newPassword);
					}
				}, false);
	}
	
	@Override
	public Account createAccount(final Account newAccount, final List<SubSystem> subsystems) 
			throws ValidationException {
		
		final List<String> validationErrors = Collections.synchronizedList(new ArrayList<String>());
		
//		newAccount.setPassword(passwordManager.generateNewPassword(newAccount.getUid()));
		
//		final Map<String, List<Group>> membershipGroupsMap = Collections.synchronizedMap(GroupMap.toMap(groupMaps));
		
		
		
		executor.execute(CreateAccount.class, subsystems != null ? subsystems : SubSystem.ALL_SUBSYSTEMS,
				new ValidatedExecutionCallback<CreateAccount>() {
//					@Override
//					public void validateAction(CreateAccount operation)
//							throws ValidationException {
//						operation.validateNewAccount(newAccount, membershipGroupsMap, validationErrors);
//						if(!validationErrors.isEmpty()) {
//							throw new ValidationException(validationErrors.toArray(new String[validationErrors.size()]));
//						}
//					}

					@Override
					public void executeAction(CreateAccount operation) {
						operation.createNewAccount(newAccount);
					}

					@Override
					public void validateAction(CreateAccount operation)
							throws ValidationException {
						// TODO Auto-generated method stub
						
					}
				}, false);
		return newAccount;
	}

	@Override
	public List<Account> getAccounts(
			final List<SearchFilter> filters, final Operand operand,
			final List<SubSystem> subsystems, boolean like) {
		final Map<String, Account> accounts =
				Collections.synchronizedMap(new HashMap<String, Account>());
		final String searchFilter = SearchFilter.buildFilterString(filters, operand, like);
		System.out.println(searchFilter);
		try {
			executor.execute(
					GetAllAccounts.class, subsystems != null ? subsystems : SubSystem.ALL_SUBSYSTEMS,
					new ExecutionCallback<GetAllAccounts>() {
						@Override
						public void executeAction(GetAllAccounts operation) {
							operation.getAllAccounts(accounts, searchFilter);
						}
					}, true);
		} catch (ValidationException e) {
			e.printStackTrace();
		}

		final List<Account> results = new ArrayList<Account>(accounts.values());

		return results;
	}
	
	@Override
	public Account modifyAccount(final Account account,
			final List<GroupMap> groupMaps, final List<SubSystem> subsystems)
			throws ValidationException {
		
		final Map<String, List<Group>> membershipGroupsMap = Collections.synchronizedMap(GroupMap.toMap(groupMaps));
		final List<String> validationErrors = Collections.synchronizedList(new ArrayList<String>());
		
		executor.execute(ModifyAccount.class, subsystems,
				new ValidatedExecutionCallback<ModifyAccount>() {
					@Override
					public void validateAction(ModifyAccount operation)
							throws ValidationException {
//						operation.validateAccount(account, membershipGroupsMap, validationErrors);
//						if(!validationErrors.isEmpty()) {
//							throw new ValidationException(validationErrors.toArray(new String[validationErrors.size()]));
//						}
					}

					@Override
					public void executeAction(ModifyAccount operation) {
						operation.modifyAccount(account, membershipGroupsMap);
					}
				}, false);
		
		return account;
	}

}