package org.gnenc.yams.subsystem.ldap.operation;

import java.util.ArrayList;
import java.util.List;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.AccountType;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.operation.account.ChangePassword;
import org.gnenc.yams.operation.account.ResetPassword;
import org.gnenc.yams.service.internal.PasswordManager;
import org.gnenc.yams.subsystem.ldap.LdapAccountHelper;
import org.gnenc.yams.subsystem.ldap.LdapHelper;
import org.gnenc.yams.subsystem.ldap.model.LdapAccount;
import org.gnenc.yams.subsystem.ldap.model.LdapAccountEsuccStaff;
import org.gnenc.yams.subsystem.ldap.model.LdapAccountEsuccStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.odm.core.OdmManager;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
@Service("ldapChangePassword")
public class LdapChangePassword extends AbstractLdapOperation implements ChangePassword, ResetPassword {
	
//	private static final Logger logger = Logger.getLogger(LdapModifyPassword.class);
	
	@Autowired
	private OdmManager manager;
	
	@Autowired
	private PasswordManager pwdManager;
	
//	@Autowired
//	private MessageService messages;
	
	@Autowired
	private LdapTemplate template;
	
//	@Autowired
//	private LdapGetPasswordPolicy pwdPolicyService;
	
	public void validateChangePassword(final Account account, final String oldPassword,
			final String newPassword, final List<String> validationErrors) {
		
		if(!template.authenticate("", "(uid=" + account.getUid() + ")", oldPassword)) {
//			validationErrors.add(messages.getMessage(13));
			return;
		}
		
		if(oldPassword.equals(newPassword)) {
//			validationErrors.add(messages.getMessage(20));
			return;
		}
		
		validateChangePassword(account, newPassword, validationErrors);
	}
	
	public void validateChangePassword(final Account account, final String newPassword,
			final List<String> validationErrors) {
		
		pwdManager.validatePassword(newPassword, validationErrors);	
	}

	public void changePassword(final Account account, final String newPassword) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.uidNumber, account.getAttribute("uidNumber"), false));
		
		String filter = SearchFilter.buildFilterString(filters, null);
		
		
		try{
			final List<LdapAccountEsuccStaff> staffs = manager.search(LdapAccountEsuccStaff.class,
					DistinguishedName.EMPTY_PATH, filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
			
			if(staffs.size() == 1) {
				LdapAccountEsuccStaff staff = staffs.get(0);
				
				staff.setDn(LdapAccountHelper.parseDn(staff.getDn().toString()));
				
				staff.setUserPassword(pwdManager.encryptSha1(newPassword));
				manager.update(staff);
			} else {
				throw new IndexOutOfBoundsException();
			}
		} catch (IndexOutOfBoundsException e) {
			final List<LdapAccountEsuccStudent> students = manager.search(LdapAccountEsuccStudent.class,
					DistinguishedName.EMPTY_PATH, filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
			
			if(students.size() == 1) {
				LdapAccountEsuccStudent student = students.get(0);
				
				student.setDn(LdapAccountHelper.parseDn(student.getDn().toString()));
				
				student.setUserPassword(pwdManager.encryptSha1(newPassword));
				manager.update(student);
			}
		}

	}

}
