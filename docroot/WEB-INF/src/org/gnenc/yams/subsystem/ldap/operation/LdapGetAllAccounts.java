package org.gnenc.yams.subsystem.ldap.operation;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.AccountType;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.operation.account.GetAllAccounts;
import org.gnenc.yams.subsystem.ldap.LdapAccountHelper;
import org.gnenc.yams.subsystem.ldap.LdapHelper;
import org.gnenc.yams.subsystem.ldap.model.LdapAccountEsuccStaff;
import org.gnenc.yams.subsystem.ldap.model.LdapAccountEsuccStudent;
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

	
	public void getAllAccounts(final Map<String, Account> accounts, final String filter, final String esuccAccountType) {

		long start = System.currentTimeMillis();
		
		if (esuccAccountType.equals(AccountType.ESUCC_STAFF) || esuccAccountType.equals(AccountType.ALL)) {
			
			final List<LdapAccountEsuccStaff> ldapAccounts = manager.search(LdapAccountEsuccStaff.class,
					DistinguishedName.EMPTY_PATH, filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
			
			for (LdapAccountEsuccStaff ldap : ldapAccounts) {
				try {
					final String accountId = ldap.getUidNumber();
					final Account account = accounts.containsKey(accountId) ? accounts.get(accountId) : new Account();
					LdapAccountHelper.convertLdapAccountToSystemAccount(ldap, account);
					account.getSubsystems().add(SubSystem.LDAP);
					accounts.put(accountId, account);
				} catch (NullPointerException npe) {
					// User has incorrect object classes
				}
			}	
		}
		
		if (esuccAccountType.equals(AccountType.ESUCC_STUDENT) || esuccAccountType.equals(AccountType.ALL)) {
			final List<LdapAccountEsuccStudent> ldapAccounts = manager.search(LdapAccountEsuccStudent.class,
					DistinguishedName.EMPTY_PATH, filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
			
			for (LdapAccountEsuccStudent ldap : ldapAccounts) {
				try {
					final String accountId = ldap.getUidNumber();
					final Account account = accounts.containsKey(accountId) ? accounts.get(accountId) : new Account();
					LdapAccountHelper.convertLdapAccountToSystemAccount(ldap, account);
					account.getSubsystems().add(SubSystem.LDAP);
					accounts.put(accountId, account);
				} catch (NullPointerException npe) {
					// User has incorrect object classes
				}
			}
		}

		if (debug) {
			logger.debug("Time to parse LDAP search results ms: " + (System.currentTimeMillis() - start));
			start = System.currentTimeMillis();
		}

	}

}