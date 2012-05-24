package org.gnenc.yams.portlet.search.util;

import java.util.ArrayList;
import java.util.List;

import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.portlet.search.OrganizationSearchTerms;
import org.gnenc.yams.portlet.search.UserSearchTerms;

import com.liferay.portal.kernel.dao.search.DisplayTerms;

public class SearchUtil {
	public static List<SearchFilter> getOrganizationFilterList(
			OrganizationSearchTerms searchTerms) {
		// TODO: Implement org filter list
		
		return null;
	}
	
	public static List<SearchFilter> getUserFilterList(
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
	
	public static Operand getOperand(DisplayTerms displayTerms) {
		Operand operand = null;
		
		if (displayTerms.isAdvancedSearch() && displayTerms.isAndOperator()) { 
			operand = Operand.AND; 
		} else {
			operand = Operand.OR;
		}
		
		return operand;
	}
}
