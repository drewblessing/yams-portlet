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
import org.gnenc.yams.model.AccountType;
import org.gnenc.yams.model.Domain;
import org.gnenc.yams.model.EntityGroup;
import org.gnenc.yams.model.EntityGroupMap;
import org.gnenc.yams.model.Group;
import org.gnenc.yams.model.GroupMap;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.portlet.search.OrganizationSearchTerms;
import org.gnenc.yams.portlet.search.UserSearchTerms;
import org.gnenc.yams.portlet.search.util.SearchUtil;
import org.gnenc.yams.service.AccountManagementService;
import org.gnenc.yams.service.DomainManagementService;
import org.gnenc.yams.service.GroupManagementService;
import org.gnenc.yams.service.impl.AccountManagementServiceImpl;
import org.gnenc.yams.service.impl.DomainManagementServiceImpl;
import org.gnenc.yams.service.impl.GroupManagementServiceImpl;

import com.liferay.portal.kernel.util.Validator;
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
			UserSearchTerms searchTerms,
			String orderByType, String orderByCol, boolean like) {

		List<SearchFilter> filters = SearchUtil.getUserFilterList(searchTerms);
		Operand operand = SearchUtil.getOperand(searchTerms);
		String accountType = Validator.isNotNull(searchTerms.getEsuccAccountType()) ? searchTerms.getEsuccAccountType() : AccountType.ALL;

		List<Account> accounts = getAccounts(filters, operand, orderByType, orderByCol, like, accountType);

		return accounts;
	}

	public static List<Account> getAccounts(List<SearchFilter> filters, Operand operand,
			String orderByType, String orderByCol, boolean like, String accountType) {
		AccountManagementService ams = AccountManagementServiceImpl.getInstance();
		List<SubSystem> subsystems = new ArrayList<SubSystem>();
		List<Account> accounts = new ArrayList<Account>();

		subsystems.add(SubSystem.LDAP);
		try {
			accounts = ams.getAccounts(filters, operand, subsystems, like, accountType);
		} catch (IllegalArgumentException e) {
			// That's Ok
		}
		SearchUtil.sortAccounts(accounts, orderByType, orderByCol);

		return accounts;
	}
	
//	public static List<Domain> getDomains(List<SearchFilter> filters, Operand operand,
//			String orderByType, String orderByCol, boolean like) {
//		AccountManagementService ams = AccountManagementServiceImpl.getInstance();
//		List<SubSystem> subsystems = new ArrayList<SubSystem>();
//		List<Domain> domains = new ArrayList<Domain>();
//		
//		subsystems.add(SubSystem.LDAP);
//		try {
//			domains = ams.getDomains(filters, operand, subsystems, like);
//		} catch (IllegalArgumentException e) {
//			// That's Ok
//		}
//		SearchUtil.sortDomains(domains, orderByType, orderByCol);
//	}

	public static List<EntityGroup> getGroups(
			OrganizationSearchTerms searchTerms,
			String orderByType, String orderByCol, boolean like) {

		List<SearchFilter> filters = SearchUtil.getOrganizationFilterList(searchTerms);
		Operand operand = SearchUtil.getOperand(searchTerms);

		List<EntityGroup> groups = getGroups(filters, operand, orderByType, orderByCol, like);
		SearchUtil.sortGroups(groups, orderByType, orderByCol);

		return groups;
	}
	
	public static List<Domain> getDomains(List<SearchFilter> filters,
			Operand operand, String orderByType, String orderByCol, boolean like) {
		DomainManagementService dms = DomainManagementServiceImpl.getInstance();
		List<SubSystem> subsystems = new ArrayList<SubSystem>();
		List<Domain> domains = new ArrayList<Domain>();

		subsystems.add(SubSystem.LDAP);
		try {
			domains = dms.getAllDomains(filters, operand, subsystems, like);
		} catch (IllegalArgumentException e) {
			// That's Ok
		}
		SearchUtil.sortDomains(domains, orderByType, orderByCol);

		return domains;
	}
	
//	public static List<Group> getGroupsByGroupType(List<SearchFilter> filters, Operand operand,
//			String orderByType, String orderByCol, boolean like) {
//		
//	}

	public static List<Group> getGroupsForAccountType(List<SearchFilter> filters, Operand operand,
			String orderByType, String orderByCol, boolean like) {
		GroupManagementService gms = GroupManagementServiceImpl.getInstance();
		List<SubSystem> subsystems = new ArrayList<SubSystem>();
		List<GroupMap> groupMap = new ArrayList<GroupMap>();

		subsystems.add(SubSystem.LDAP);
		try {
			groupMap = gms.getGroups(filters, operand, subsystems, like);
		} catch (IllegalArgumentException e) {
			// That's Ok
		}

		List<Group> groups = new ArrayList<Group>();
		for (GroupMap map : groupMap) {
			for (Group group : map.getGroups()) {
			groups.add(group);
			}
		}

		return groups;
	}
	
	public static List<EntityGroup> getGroups(List<SearchFilter> filters, Operand operand,
			String orderByType, String orderByCol, boolean like) {
		GroupManagementService gms = GroupManagementServiceImpl.getInstance();
		List<SubSystem> subsystems = new ArrayList<SubSystem>();
		List<EntityGroupMap> groupMap = new ArrayList<EntityGroupMap>();

		subsystems.add(SubSystem.LDAP);
		try {
			groupMap = gms.getAllGroups(filters, operand, subsystems, like);
		} catch (IllegalArgumentException e) {
			// That's Ok
		}

		List<EntityGroup> groups = new ArrayList<EntityGroup>();
		for (EntityGroupMap map : groupMap) {
			for (EntityGroup group : map.getGroups()) {
			groups.add(group);
			}
		}
		SearchUtil.sortGroups(groups, orderByType, orderByCol);

		return groups;
	}

}