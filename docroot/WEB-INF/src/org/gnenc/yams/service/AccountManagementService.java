package org.gnenc.yams.service;

import java.util.List;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.model.SubSystem;

/**
 * 
 * @author Drew A. Blessing
 *
 */
public interface AccountManagementService {
	/**
	 * Gets all accounts that match the given filter. The filter should be a well formed
	 * standard ldap query string. If the filter is null or empty, no accounts are returned.
	 * 
	 * @param filter standard ldap compliant search filter string
	 * @param subsystems from which accounts should be returned
	 * @return a list of accounts matching the search filter
	 */
	public List<Account> getAccounts(
			List<SearchFilter> filters,Operand operand,List<SubSystem> subsystems);
	
}
