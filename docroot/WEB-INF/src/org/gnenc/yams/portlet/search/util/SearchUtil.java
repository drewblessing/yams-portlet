package org.gnenc.yams.portlet.search.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.Domain;
import org.gnenc.yams.model.EntityGroup;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.portlet.search.OrganizationSearchTerms;
import org.gnenc.yams.portlet.search.UserSearchTerms;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.Validator;

/**
 * Common methods used in preparation for searches.
 *
 * @author Drew A. Blessing
 *
 */
public class SearchUtil {

	/**
	 * Returns a list of the organization search filters based on the
	 * search terms object
	 *
	 * @param searchTerms OrganizationSearchTerms object containing
	 * 					  the values from the search form
	 * @return a list of the organization search filters
	 */
	public static List<SearchFilter> getOrganizationFilterList(
			OrganizationSearchTerms searchTerms) {
		final List<SearchFilter> filters = new ArrayList<SearchFilter>();

		/** Each search term that wishes to be a part of the basic keyword
		  * search should check for false isAdvancedSearch() and then provide
		  * a fall back to getKeywords() in the filter
		  */
		
		if (searchTerms.getName() != null ||
				(!searchTerms.isAdvancedSearch() && searchTerms.getKeywords() != null)) {
			SearchFilter filter = new SearchFilter(
					Filter.cn,
					searchTerms.getName() != null ?
							searchTerms.getName() : searchTerms.getKeywords(),
					false);
			filters.add(filter);
		}

		return filters;
	}

	/**
	 * Returns a list of the user search filters based on the search
	 * terms object
	 *
	 * @param searchTerms UserSearchTerms object containing the values
	 * 					  from the search form
	 * @return a list of the user search filters
	 */
	public static List<SearchFilter> getUserFilterList(
			UserSearchTerms searchTerms) {
		final List<SearchFilter> filters = new ArrayList<SearchFilter>();
		
//		if (!searchTerms.isAdvancedSearch() && StringUtils.isNotBlank(searchTerms.getKeywords()) &&
//				!StringUtils.isNotBlank(searchTerms.getUid())) {
//			return filters;
//		}
		/** Each search term that wishes to be a part of the basic keyword
		  * search should check for false isAdvancedSearch() and then provide
		  * a fall back to getKeywords() in the filter
		  */

		if (Validator.isNotNull(searchTerms.getFirstName()) ||
				(!searchTerms.isAdvancedSearch() && StringUtils.isNotBlank(searchTerms.getKeywords()))) {
			SearchFilter filter = new SearchFilter(
					Filter.givenName,
					searchTerms.getFirstName() != null ?
							searchTerms.getFirstName() : searchTerms.getKeywords(),
					false);
			filters.add(filter);
		}

		if (Validator.isNotNull(searchTerms.getLastName()) ||
				(!searchTerms.isAdvancedSearch() && StringUtils.isNotBlank(searchTerms.getKeywords()))) {
			SearchFilter filter = new SearchFilter(
					Filter.sn,
					searchTerms.getLastName() != null ?
							searchTerms.getLastName() : searchTerms.getKeywords(),
					false);
			filters.add(filter);
		}

		if (Validator.isNotNull(searchTerms.getEmailAddress()) ||
				(!searchTerms.isAdvancedSearch() && StringUtils.isNotBlank(searchTerms.getKeywords()))) {
			SearchFilter filter = new SearchFilter(
					Filter.mail,
					searchTerms.getEmailAddress() != null ?
							searchTerms.getEmailAddress() : searchTerms.getKeywords(),
					false);
			filters.add(filter);
		}
		
		if (Validator.isNotNull(searchTerms.getDomain()) ||
				(!searchTerms.isAdvancedSearch() && StringUtils.isNotBlank(searchTerms.getKeywords()))) {
			SearchFilter filter = new SearchFilter(
					Filter.esuccMailPrimaryDomain,
					searchTerms.getDomain() != null ?
							searchTerms.getDomain() : searchTerms.getKeywords(),
					false);
			filters.add(filter);
		}
		
		if (Validator.isNotNull(searchTerms.getEsuccEntity()) ||
				(!searchTerms.isAdvancedSearch() && StringUtils.isNotBlank(searchTerms.getKeywords()))) {
			SearchFilter filter = new SearchFilter(
					Filter.esuccEntity,
					searchTerms.getEsuccEntity() != null ?
							searchTerms.getEsuccEntity() : searchTerms.getKeywords(),
					false);
			filters.add(filter);
		}

		if (Validator.isNotNull(searchTerms.getUid())) {
			SearchFilter filter = new SearchFilter(
					Filter.uid,
					searchTerms.getUid(),
					false);
			filters.add(filter);
		}

		if (Validator.isNotNull(searchTerms.getUidNumber())) {
			SearchFilter filter = new SearchFilter(
					Filter.uidNumber,
					searchTerms.getUidNumber(),
					false);
			filters.add(filter);
		}

		
		return filters;
	}

	/**
	 * Returns the operand for the search.  The DisplayTerms object should
	 * be a child class specific to the search being conducted - i.e.
	 * UserSearchTerms or OrganizationSearchTerms.  It is generic in
	 * this signature so it can be used for multiple types of searches.
	 *
	 * @param displayTerms DisplayTerms object that contains the search
	 *    				   toggle's "and operator" boolean value
	 * @return the operand for the search
	 */
	public static Operand getOperand(DisplayTerms displayTerms) {
		Operand operand = null;

		if (displayTerms.isAdvancedSearch() && displayTerms.isAndOperator()) {
			operand = Operand.AND;
		} else {
			operand = Operand.OR;
		}

		return operand;
	}

	public static void sortAccounts(
			List<Account> accounts, String orderByType, String orderByCol) {

		if (orderByCol.equals("givenName") && orderByType.equals("asc")) {
			Collections.sort(accounts, Account.FIRST_NAME_COMPARATOR_ASC);
		} else if (orderByCol.equals("givenName") && orderByType.equals("desc")) {
			Collections.sort(accounts, Account.FIRST_NAME_COMPARATOR_DESC);
		} else if (orderByCol.equals("sn") && orderByType.equals("desc")) {
			Collections.sort(accounts, Account.LAST_NAME_COMPARATOR_DESC);
		} else {
			Collections.sort(accounts, Account.LAST_NAME_COMPARATOR_ASC);
		}
	}

	public static void sortGroups(List<EntityGroup> groups, String orderByType,
			String orderByCol) {

		if (orderByCol.equals("cn") && orderByType.equals("asc")) {
			Collections.sort(groups, EntityGroup.NAME_COMPARATOR_ASC);
		} else if (orderByCol.equals("cn") && orderByType.equals("desc")) {
			Collections.sort(groups, EntityGroup.NAME_COMPARATOR_DESC);
		}

	}
	
	public static void sortDomains(List<Domain> domains, String orderByType,
			String orderByCol) {

		if (orderByCol.equals("o") && orderByType.equals("asc")) {
			Collections.sort(domains, Domain.ORGANIZATION_COMPARATOR_ASC);
		} else if (orderByCol.equals("o") && orderByType.equals("desc")) {
			Collections.sort(domains, Domain.ORGANIZATION_COMPARATOR_DESC);
		}

	}
}