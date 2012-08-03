package org.gnenc.yams.subsystem.ldap.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.AccountType;
import org.gnenc.yams.model.EntityGroup;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.operation.account.CreateAccount;
import org.gnenc.yams.service.internal.PasswordManager;
import org.gnenc.yams.subsystem.ldap.LdapAccountHelper;
import org.gnenc.yams.subsystem.ldap.LdapGroupHelper;
import org.gnenc.yams.subsystem.ldap.LdapHelper;
import org.gnenc.yams.subsystem.ldap.LdapSystemHelper;
import org.gnenc.yams.subsystem.ldap.model.LdapAccountEsuccStaff;
import org.gnenc.yams.subsystem.ldap.model.LdapAccountEsuccStudent;
import org.gnenc.yams.subsystem.ldap.model.LdapEntityGroup;
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
	public void validateNewAccount(final Account account, final Map<String, List<EntityGroup>> membershipGroups, final List<String> validationErrors) {
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
	public void createNewAccount(Account account) {
		String esuccAccountType = account.getAttribute("esuccAccountType");
		String entity = account.getAttribute("esuccEntity");
		
		System.out.println("Account type: " + esuccAccountType);
		
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.esuccEntity, entity, false));
//		filters.add(new SearchFilter(
//				Filter.esuccGroupType, "staff", false));

		final String filter = SearchFilter.buildFilterString(filters, Operand.AND, false);
		
		System.out.println("Filter: " + filter);
		
		if (esuccAccountType.equals("Staff")) {
			final LdapAccountEsuccStaff ldap = new LdapAccountEsuccStaff();
			LdapAccountHelper.convertSystemAccountToLdapAccount(account, ldap);

			ldap.setUserPassword(passwordEncoder.encryptSha1(account.getPassword()));
			
			final List<LdapGroup> ldapGroups = manager.search(LdapGroup.class,
					new DistinguishedName(LdapGroupHelper.GROUP_BASE_DN),
					filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
			
			for (LdapGroup group : ldapGroups) {
				if (group.getEsuccGroupType().equals(esuccAccountType)) {
					ldap.setEsuccProvider(group.getEsuccProvider());
					ldap.setEsuccSystem(group.getEsuccSystem());
					ldap.setEsuccAccountEnabled("TRUE");
					ldap.setEsuccUidNumber(LdapSystemHelper.computeEsuccUidNumber());
					ldap.setUidNumber(LdapSystemHelper.computeUidNumber(ldap.getEsuccUidNumber()));
					System.out.println("LDAP info: " + ldap.getUid() + " group: " + group.getSeeAlso());
					ldap.setDn(LdapAccountHelper.computeDn(ldap, group));
					System.out.println("Final dn: " + ldap.getDn().toString());
				}
			}
//			
//			if (ldapGroups.size() == 1) {
//				
//			} else {
//				// Problem
//			}

			manager.create(ldap);
		} else if (esuccAccountType.equals("Student")) {
			final LdapAccountEsuccStudent ldap = new LdapAccountEsuccStudent();
			LdapAccountHelper.convertSystemAccountToLdapAccount(account, ldap);

			ldap.setUserPassword(passwordEncoder.encryptSha1(account.getPassword()));
			
			final List<LdapGroup> ldapGroups = manager.search(LdapGroup.class,
					new DistinguishedName(LdapGroupHelper.GROUP_BASE_DN),
					filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
			
			for (LdapGroup group : ldapGroups) {
				if (group.getEsuccGroupType().equals(esuccAccountType)) {
					ldap.setEsuccProvider(group.getEsuccProvider());
					ldap.setEsuccSystem(group.getEsuccSystem());
					ldap.setEsuccAccountEnabled("TRUE");
					ldap.setEsuccUidNumber(LdapSystemHelper.computeEsuccUidNumber());
					ldap.setUidNumber(LdapSystemHelper.computeUidNumber(ldap.getEsuccUidNumber()));
					System.out.println("LDAP info: " + ldap.getUid() + " group: " + group.getSeeAlso());
					ldap.setDn(LdapAccountHelper.computeDn(ldap, group));
					System.out.println("Final dn: " + ldap.getDn().toString());
				}
			}

			manager.create(ldap);
		}
		
		
		
		
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
		
//		logger.info("Successfully created LDAP Account: " + account.getUid());
//		
//		final Set<String> sanitizedGroupNames = new HashSet<String>();
//		
//		for(final List<EntityGroup> gl : membershipGroups.values()) {
//			if(gl != null) {
//				for(final EntityGroup g : gl)
//					sanitizedGroupNames.add(g.getCn().toLowerCase());
//			}
//		}
//		
//		final List<LdapEntityGroup> ldapGroups = manager.findAll(
//				LdapEntityGroup.class, DistinguishedName.EMPTY_PATH, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
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
//		for(final LdapEntityGroup group : ldapGroups) {
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
//						logger.info("Added LDAP Account: " + account.getUid() + " to EntityGroup: " + group.getCn());
//					}
//					
//					break;
//					
//				}
//			}
//		}
	}

}
