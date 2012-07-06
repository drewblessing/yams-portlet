package org.gnenc.yams.subsystem.ldap.operation;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.Group;
import org.gnenc.yams.operation.account.CreateAccount;
import org.gnenc.yams.service.internal.PasswordManager;
import org.gnenc.yams.subsystem.ldap.LdapAccountHelper;
import org.gnenc.yams.subsystem.ldap.LdapGroupHelper;
import org.gnenc.yams.subsystem.ldap.LdapHelper;
import org.gnenc.yams.subsystem.ldap.model.LdapAccount;
import org.gnenc.yams.subsystem.ldap.model.LdapGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.odm.core.OdmManager;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
@Service("ldapCreateAccount")
public class LdapCreateAccount extends AbstractLdapOperation implements
		CreateAccount {
	
//	private static final Logger logger = Logger.getLogger(LdapCreateAccount.class);
	
	@Autowired
	private OdmManager manager;
	
	@Autowired
	private LdapCheckAccountExists checker;
	
	@Autowired
	private PasswordManager passwordEncoder;

	@Override
	public void validateNewAccount(final Account account, final Map<String, List<Group>> membershipGroups, final List<String> validationErrors) {
		LdapAccountHelper.validateSystemAccountCommon(account, validationErrors);
		if(account.getPassword() == null || account.getPassword().trim().isEmpty()) {
			validationErrors.add("Password cannot be empty for a new account.");
		}
		if(checker.checkAccountExists(account.getUid()) ||
				checker.checkAccountExists(String.valueOf(account
						.getEmployeeNumber()))) {
			validationErrors.add("Another account with that name already exists.");
		}
	}

	@Override
	public void createNewAccount(Account account, Map<String, List<Group>> membershipGroups) {
		final LdapAccount ldap = new LdapAccount();
		
		LdapAccountHelper.convertSystemAccountToLdapAccount(account, ldap);
		
		ldap.setUserPassword(passwordEncoder.encryptSSHA1(account.getPassword()));
		
//		switch (account.getAccountType()) {
//		case EMPLOYEE:
//			ldap.setPwdPolicy(LdapAccountHelper.STAFF_PPOLICY_DN);
//			break;
//		case STUDENT:
//			ldap.setPwdPolicy(LdapAccountHelper.STUDENT_PPOLICY_DN);
//			break;
//		default:
//			ldap.setPwdPolicy(LdapAccountHelper.DEFAULT_PPOLICY_DN);
//		}
		
		manager.create(ldap);
		
//		logger.info("Successfully created LDAP Account: " + account.getUid());
//		
//		final Set<String> sanitizedGroupNames = new HashSet<String>();
//		
//		for(final List<Group> gl : membershipGroups.values()) {
//			if(gl != null) {
//				for(final Group g : gl)
//					sanitizedGroupNames.add(g.getCn().toLowerCase());
//			}
//		}
//		
//		final List<LdapGroup> ldapGroups = manager.findAll(
//				LdapGroup.class, DistinguishedName.EMPTY_PATH, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
//		
//		final String accountDn = new DistinguishedName(ldap.getDn()).toUrl() + "," + LdapHelper.DEFAULT_BASE_DN;
//		
//		// System determined additional groups based on department and job title
//		final Set<String> additionalGroups = new HashSet<String>();
//		LdapAccountHelper.processAdditionalGroupMemberships(ldap, sanitizedGroupNames, null, ldapGroups,
//				additionalGroups, null);
//		
//		sanitizedGroupNames.addAll(additionalGroups);
//		
//		for(final LdapGroup group : ldapGroups) {
//			
//			final String cn = group.getCn().toLowerCase();
//			
//			for(final String selectedCn : sanitizedGroupNames) {
//				if(cn.equalsIgnoreCase(selectedCn)) {
//					
//					final List<String> members = group.getMembers();
//					boolean found = false;
//					for(final String m : members) {
//						if(m.equalsIgnoreCase(accountDn)) {
//							found = true;
//							break;
//						}
//					}
//					
//					if(!found) {
//						// if the only member is the empty placeholder, remove it.
//						if(members.size() == 1 && members.get(0).equalsIgnoreCase(LdapGroupHelper.EMPTY_GROUP_MEMBER_DN)) {
//							members.remove(LdapGroupHelper.EMPTY_GROUP_MEMBER_DN);
//						}
//						group.getMembers().add(accountDn);
//						
//						manager.update(group);
//						logger.info("Added LDAP Account: " + account.getUid() + " to Group: " + group.getCn());
//					}
//					
//					break;
//					
//				}
//			}
//		}
	}

}
