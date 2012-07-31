package org.gnenc.yams.subsystem.ldap.operation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.AccountStatus;
import org.gnenc.yams.model.Group;
import org.gnenc.yams.operation.account.ModifyAccount;
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
@Service("ldapModifyAccount")
public class LdapModifyAccount extends AbstractLdapOperation implements
		ModifyAccount {
	
//	private static final Logger logger = Logger.getLogger(LdapModifyAccount.class);
//	private static final boolean debug = logger.isDebugEnabled();
	
	@Autowired
	private OdmManager manager;
	
	@Autowired
	private LdapCheckAccountExists checker;
	
	@Autowired
	private PasswordManager passwordEncoder;

	@Override
	public void validateAccount(final Account account,
			final Map<String, List<Group>> membershipGroups, final List<String> validationErrors) {		
		LdapAccountHelper.validateSystemAccountCommon(account, validationErrors);
		
		if(!checker.checkAccountExists(account.getUid())) {
			validationErrors.add("The Account does not exist.");
		}
	}

	@Override
	public void modifyAccount(final Account account,
			final Map<String, List<Group>> membershipGroups) {
		
		final LdapAccount ldap = manager.read(LdapAccount.class, LdapAccountHelper.computeDn(account));
		
		LdapAccountHelper.convertSystemAccountToLdapAccount(account, ldap);
		
		if(!account.getPassword().isEmpty()) {
			ldap.setUserPassword(passwordEncoder.encryptSha1(account.getPassword()));
			
//			ldap.setChinookUserPassword(passwordEncoder.encryptBlowfish(account.getPassword()));
			
//			logger.info("Updating password for LDAP Account: " + account.getUid());
		}
		
		manager.update(ldap);
		
//		logger.info("Successfully updated LDAP Account: " + account.getUid());
		
//		final String accountDn = (new DistinguishedName(ldap.getDn()).toUrl() + "," + LdapHelper.DEFAULT_BASE_DN).toLowerCase();
//		
//		final Set<String> sanitizedGroupNames = new HashSet<String>();
//		for(final String key : membershipGroups.keySet()) {
//			if(LdapGroupHelper.isLdapGroup(key)) {
//				final List<Group> gl = membershipGroups.get(key);
//				if(gl != null) {
//					for(final Group g : gl)
//						sanitizedGroupNames.add(g.getCn().toLowerCase());
//				}
//			}
//		}
//		
////		if(debug) {
////			logger.debug("Groups the client selected: \n");
////			for(final String s : sanitizedGroupNames) {
////				logger.debug(s);
////			}
////		}
//		
//		// Groups that this account is currently a member of
//		final Set<String> existingGroups = new HashSet<String>();
//		for(final LdapGroup group : manager.search(LdapGroup.class, DistinguishedName.EMPTY_PATH,
//				"(uniqueMember=" + accountDn + ")",
//				LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE)) {
//			existingGroups.add(group.getCn().toLowerCase());
//		}
//		
////		if(debug) {
////			logger.debug("Groups the account is currently a member of: \n");
////			for(final String s : existingGroups) {
////				logger.debug(s);
////			}
////		}
//		
//		// All available groups in the ldap
//		final List<LdapGroup> ldapGroups = manager.findAll(
//				LdapGroup.class, DistinguishedName.EMPTY_PATH, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
//		
//		// System determined additional groups based on department and job title
//		final Set<String> additionalGroupNames = new HashSet<String>();
//		final Set<String> removedGroups = new HashSet<String>();
//		
//		// Don't process additional automatic groups if account status is closed.
//		if(!AccountStatus.CLOSED.equals(account.getAccountStatus())) {
//			LdapAccountHelper.processAdditionalGroupMemberships(ldap, sanitizedGroupNames, existingGroups, ldapGroups,
//					additionalGroupNames, removedGroups);
//		}
//		
//		// Add all additional groups into sanitized groups so we can check for existence.
//		sanitizedGroupNames.addAll(additionalGroupNames);
		
//		if(debug) {
//			logger.debug("Groups to be processed: \n");
//			for(final String s : sanitizedGroupNames) {
//				logger.debug(s);
//			}
//		}
		
		// Remove groups the user is no longer a member of
//		for(final LdapGroup g : ldapGroups) {
//			final String groupCn = g.getCn().toLowerCase();
//			
//			for(final String removedCn : removedGroups) {
//				if(groupCn.equalsIgnoreCase(removedCn)) {
//					
//					boolean found = false;
//					// Process automatically removed groups
//					if(removedGroups.contains(groupCn)) {
//						for(final Iterator<String> ite = g.getMembers().iterator(); ite.hasNext();) {
//							final String m = ite.next();
//							if(m.equalsIgnoreCase(accountDn)) {
//								ite.remove();
//								found = true;
//								break;
//							}
//						}
//					}
//					
//					// process groups that the client removed
//					if(!sanitizedGroupNames.contains(groupCn)) {
//						for(final Iterator<String> ite = g.getMembers().iterator(); ite.hasNext();) {
//							final String m = ite.next();
//							if(m.equalsIgnoreCase(accountDn)) {
//								ite.remove();
//								found = true;
//								break;
//							}
//						}
//					}
//					
//					if(found) {
//						if(g.getMembers().isEmpty()) {
//							// LDAP doesn't like empty groups
//							g.getMembers().add(LdapGroupHelper.EMPTY_GROUP_MEMBER_DN);
//						}
//						manager.update(g);
////						logger.info("Removed Account: " + account.getUid() + " from Group: " + g.getCn());
//					}
//					
//					break;
//				}
//			}
			
//		}
//		
//		for(final LdapGroup group : ldapGroups) {
//			
//			final String cn = group.getCn().toLowerCase();
//			
//			for(final String selectedCn : sanitizedGroupNames) {
//				if(cn.equalsIgnoreCase(selectedCn)) {
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
////						logger.info("Added LDAP Account: " + account.getUid() + " to Group: " + group.getCn());
//					}
//					
//					break;
//				}
//			}
//		}

	}

}
