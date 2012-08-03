package org.gnenc.yams.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.ValidationException;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.Domain;
import org.gnenc.yams.model.EntityGroup;
import org.gnenc.yams.model.EntityGroupMap;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.operation.account.ChangePassword;
import org.gnenc.yams.operation.account.CheckAccountExists;
import org.gnenc.yams.operation.account.CreateAccount;
import org.gnenc.yams.operation.account.GetAllAccounts;
import org.gnenc.yams.operation.account.GetAllDomains;
import org.gnenc.yams.operation.account.ModifyAccount;
import org.gnenc.yams.service.AccountManagementService;
import org.gnenc.yams.service.DomainManagementService;
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
@Service("domainManagementService")
public class DomainManagementServiceImpl implements DomainManagementService {

	@Autowired
	private ExecutionManager executor;

	private static DomainManagementService instance;

//	@Autowired
//	private PasswordManager passwordManager;

//	@Autowired
//	private MessageService messages;

	public static DomainManagementService getInstance() {
		if (instance == null) {
			instance = new DomainManagementServiceImpl();
		}
		return instance;
	}

	protected DomainManagementServiceImpl() {
		instance = this;
	}
	
	@Override
	public List<Domain> getAllDomains(List<SearchFilter> filters, Operand operand, 
			List<SubSystem> subsystems, boolean like) {
		final Map<String, Domain> domains =
				Collections.synchronizedMap(new HashMap<String, Domain>());
		final String searchFilter = SearchFilter.buildFilterString(filters, operand, like);
		System.out.println(searchFilter);
		try {
			executor.execute(
					GetAllDomains.class, subsystems != null ? subsystems : SubSystem.ALL_SUBSYSTEMS,
					new ExecutionCallback<GetAllDomains>() {
						@Override
						public void executeAction(GetAllDomains operation) {
							operation.getAllDomains(domains, searchFilter);
						}
					}, true);
		} catch (ValidationException e) {
			e.printStackTrace();
		}

		final List<Domain> results = new ArrayList<Domain>(domains.values());

		return results;
	}

}