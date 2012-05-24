/**
 *  Copyright (c) 2012-2013 Educational Service Unit 10. 
 *
 *  This file is part of the YAMS portlet.
 *  
 *  YAMS portlet is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  YAMS portlet is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with the YAMS portlet.  If not, see <http://www.gnu.org/licenses/>.
 **/
package org.gnenc.yams.portlet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.portlet.search.UserSearchTerms;
import org.gnenc.yams.portlet.search.util.SearchUtil;
import org.gnenc.yams.service.AccountManagementService;
import org.gnenc.yams.service.impl.AccountManagementServiceImpl;

import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Class for the search portlet
 * 
 * @author Drew A. Blessing
 *
 */
public class Search extends MVCPortlet {
	
	/**
	 * Returns a list of accounts matching the search terms
	 * 
	 * @param searchTerms UserSearchTerms object containing
	 * 					  the values from the search form
	 * @return a list of accounts matching the search terms
	 */
	public static List<Account> getAccounts(
			UserSearchTerms searchTerms, String orderByType, String orderByCol) {
		AccountManagementService ams = AccountManagementServiceImpl.getInstance();
		List<SubSystem> subsystems = new ArrayList<SubSystem>();
		List<Account> accounts = new ArrayList<Account>();
		
		subsystems.add(SubSystem.LDAP);

		List<SearchFilter> filters = SearchUtil.getUserFilterList(searchTerms);
		Operand operand = SearchUtil.getOperand(searchTerms);
		
		accounts = ams.getAccounts(filters, operand, subsystems);
		SearchUtil.sortAccounts(accounts, orderByType, orderByCol);
		
		return accounts;
	}
	
}
