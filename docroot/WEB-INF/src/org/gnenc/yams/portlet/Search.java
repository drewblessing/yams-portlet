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
import java.util.List;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.portlet.search.UserSearchTerms;
import org.gnenc.yams.service.AccountManagementService;
import org.gnenc.yams.service.impl.AccountManagementServiceImpl;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class Search extends MVCPortlet {	
	public static List<Account> getAccounts(UserSearchTerms searchTerms) {
		AccountManagementService ams = AccountManagementServiceImpl.getInstance();
		List<Account> accounts = new ArrayList<Account>();
		Operand operand = null;
		subsystems.add(SubSystem.LDAP);

		List<SearchFilter> filters = buildFilterList(searchTerms);
		
		if (searchTerms.isAdvancedSearch() && searchTerms.isAndOperator()) { 
			operand = Operand.AND; 
		} else {
			operand = Operand.OR;
		}
		
		accounts = ams.getAccounts(filters, operand, subsystems);
		
		return accounts;
	}

	private static List<SearchFilter> buildFilterList(
			UserSearchTerms searchTerms) {
		final List<SearchFilter> filters = new ArrayList<SearchFilter>();
		
		/** Each search term that wishes to be a part of the basic keyword
		  * search should check for false isAdvancedSearch() and then provide
		  * a fall back to getKeywords() in the filter
		  */
		if(searchTerms.getFirstName() != null || !searchTerms.isAdvancedSearch()) {
			SearchFilter filter = new SearchFilter(
					Filter.givenName,
					searchTerms.getFirstName() != null ? 
							searchTerms.getFirstName() : searchTerms.getKeywords(),
					false);
			filters.add(filter);
		}
		
		if(searchTerms.getLastName() != null || !searchTerms.isAdvancedSearch()) {
			SearchFilter filter = new SearchFilter(
					Filter.sn,
					searchTerms.getLastName() != null ? 
							searchTerms.getLastName() : searchTerms.getKeywords(),
					false);
			filters.add(filter);
		}
		
		if(searchTerms.getEmailAddress() != null || !searchTerms.isAdvancedSearch()) {
			SearchFilter filter = new SearchFilter(
					Filter.mail,
					searchTerms.getEmailAddress() != null ? 
							searchTerms.getEmailAddress() : searchTerms.getKeywords(),
					false);
			filters.add(filter);
		}
		
		return filters;
	}
	
	private static List<SubSystem> subsystems = new ArrayList<SubSystem>();
	
}
