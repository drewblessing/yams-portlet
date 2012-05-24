package org.gnenc.yams.service;

import java.util.List;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.model.SubSystem;

public interface AccountManagementService {
	/**
	 * Gets all accounts that match the given filter. The filter should be a well formed
	 * standard ldap query string. If the fiter is null or empty, all accounts are returned.
	 * 
	 * @param filter
	 * @param subsystems from which accounts should be returned
	 * @return
	 */
	public List<Account> getAccounts(
			List<SearchFilter> filters,Operand operand,List<SubSystem> subsystems);
	
}
