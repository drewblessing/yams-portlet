package org.gnenc.yams.subsystem.ldap.operation;

import java.util.ArrayList;
import java.util.List;

import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.operation.account.CheckAccountExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
@Service("ldapCheckAccountExists")
public class LdapCheckAccountExists extends AbstractLdapOperation implements
		CheckAccountExists {

	@Autowired
	private LdapTemplate template;

	private static final ContextMapper EMPTY_CONTEXT_MAPPER = new ContextMapper() {
		public Object mapFromContext(Object arg0) {
			return null;
		}
	};

	public boolean checkAccountExists(String mail) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(Filter.mail, mail, false));
		
		String filter = SearchFilter.buildFilterString(filters, null, false);
		
		try {
			template.searchForObject(new DistinguishedName().EMPTY_PATH, 
					filter, EMPTY_CONTEXT_MAPPER);
			return true;
		} catch (IncorrectResultSizeDataAccessException e) {
			return false;
		}
	}

}