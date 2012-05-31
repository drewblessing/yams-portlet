package org.gnenc.yams.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.gnenc.yams.model.SearchFilter.Operand;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
public class SearchFilter implements Serializable {
	
	private static final long serialVersionUID = -1339369939736889982L;

	private static final Logger logger = Logger.getLogger(SearchFilter.class);
	
	public static enum Operand {AND, OR};

	public static enum Filter {
		givenName,
		name,
		sn,
		mail};
	
	private final Filter filter;
	
	private final boolean negated;
	
	private final String value;
	
	public SearchFilter(Filter filter, String value, boolean negated) {
		this.filter = filter;
		this.value = value;
		this.negated = negated;
	}
	
	public static String buildFilterString(Collection<SearchFilter> searchFilters, Operand operand) {
		if(searchFilters.isEmpty()) {
			throw new IllegalArgumentException("At Least one SearchFilter is required.");
		}
		final boolean singleFilter = searchFilters.size() == 1;
		if(!singleFilter && operand == null) {
			throw new IllegalArgumentException("Search operand cannot be null when searching with more than one filter.");
		}
		
		final StringBuilder result = new StringBuilder();
		if(!singleFilter) {
			result.append("(");
		}
		if(operand != null && searchFilters.size() > 1) {
			result.append(getOperandValue(operand));
		}
		
		/** Adding a asterisk (*) at the beginning and end of the filter
		 *  allows for zero or more characters both before and after the 
		 *  search term. As an added bonus it also returns zero results
		 *  when all search terms are null (we wouldn't want to return 
		 *  all of potentially thousands of users in one request if it
		 *  isn't necessary)
		 */
		for(SearchFilter f : searchFilters) {
			result.append("(");
			if(f.negated) {
				result.append("!(");
			}
			result.append(f.filter.name()).append("=*").append(f.value).append("*)");
			if(f.negated) {
				result.append(")");
			}
		}
		if(!singleFilter) {
			result.append(")");
		}
		
		final String r = result.toString();
		logger.debug("Search Filter: " + r);
		
		return r;
	}
	
	private static String getOperandValue(Operand operand) {
		switch(operand) {
		case AND:
			return "&";
		case OR:
			return "|";
		default:
			return "&";
		}
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Filter: ").append(filter == null ? "" : filter.name()).append("\n")
		.append("Negated: ").append(negated).append("\n")
		.append("Value: ").append(value);
		
		return sb.toString();
	}

}
