package org.gnenc.yams.subsystem.ldap.operation;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.operation.account.GetAllAccounts;
import org.gnenc.yams.subsystem.ldap.LdapAccountHelper;
import org.gnenc.yams.subsystem.ldap.LdapHelper;
import org.gnenc.yams.subsystem.ldap.model.LdapAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.odm.core.OdmManager;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
@Service("ldapGetAllAccounts")
public class LdapGetAllAccounts extends AbstractLdapOperation implements
		GetAllAccounts {
	
	@Autowired
	private OdmManager manager;
	
	private static final Logger logger = Logger.getLogger(LdapGetAllAccounts.class);
	private static final boolean debug = logger.isDebugEnabled();

	@Override
	public void getAllAccounts(final Map<String, Account> accounts, final String filter) {
		
		long start = System.currentTimeMillis();
		
		
		final List<LdapAccount> ldapAccounts = manager.search(LdapAccount.class,
				DistinguishedName.EMPTY_PATH, filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
		
		if(debug) {
			logger.debug("Time to search LDAP ms: " + (System.currentTimeMillis() - start));
			start = System.currentTimeMillis();
		}
		
		for(LdapAccount ldap : ldapAccounts) {
			final String accountId = ldap.getUid();
			final Account account = accounts.containsKey(accountId) ? accounts.get(accountId) : new Account();
			LdapAccountHelper.convertLdapAccountToSystemAccount(ldap, account);
			account.getSubsystems().add(SubSystem.LDAP);
			accounts.put(accountId, account);
		}
		
		if(debug) {
			logger.debug("Time to parse LDAP search results ms: " + (System.currentTimeMillis() - start));
			start = System.currentTimeMillis();
		}

	}

}
