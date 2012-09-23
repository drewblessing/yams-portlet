package org.gnenc.yams.subsystem.ldap.operation;


import java.util.ArrayList;
import java.util.List;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.operation.account.ModifyEmailForward;
import org.gnenc.yams.service.internal.PasswordManager;
import org.gnenc.yams.subsystem.ldap.LdapAccountHelper;
import org.gnenc.yams.subsystem.ldap.LdapHelper;
import org.gnenc.yams.subsystem.ldap.model.LdapAccountEsuccStaff;
import org.gnenc.yams.subsystem.ldap.model.LdapAccountEsuccStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.odm.core.OdmManager;
import org.springframework.stereotype.Service;
/**
 * 
 * @author Drew A. Blessing
 *
 */
@Service("l")
public class LdapModifyEmailForward extends AbstractLdapOperation implements ModifyEmailForward {

	@Autowired
	private OdmManager manager;
	
	public void validateEmailForward(Account account, String emailForward) {
		
	}
	
	public void modifyEmailForward(Account account, String emailForward) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.uidNumber, account.getAttribute("uidNumber"), false));
		
		String filter = SearchFilter.buildFilterString(filters, null, false);
		
		try {
			final List<LdapAccountEsuccStaff> staffs = manager.search(LdapAccountEsuccStaff.class,
					DistinguishedName.EMPTY_PATH, filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
			
			if (staffs.size() == 1) {
				LdapAccountEsuccStaff staff = staffs.get(0);
				LdapAccountHelper.convertSystemAccountToExistingLdapAccount(account, staff);
				
				staff.setDn(LdapAccountHelper.parseDn(staff.getDn().toString()));
				
				if (!account.getAttribute("esuccEmailForward").isEmpty()) {
					staff.setEsuccMailForward(account.getAttribute("esuccEmailForward"));
				}
				
				manager.update(staff);
			} else {
				throw new IndexOutOfBoundsException();
			}
		} catch (IndexOutOfBoundsException e) {
			final List<LdapAccountEsuccStudent> students = manager.search(LdapAccountEsuccStudent.class,
					DistinguishedName.EMPTY_PATH, filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
			
			if (students.size() == 1) {
				LdapAccountEsuccStudent student = students.get(0);
				LdapAccountHelper.convertSystemAccountToExistingLdapAccount(account, student);
			
				student.setDn(LdapAccountHelper.parseDn(student.getDn().toString()));
				
				if (!account.getAttribute("esuccEmailForward").isEmpty()) {
					student.setEsuccMailForward(account.getAttribute("esuccEmailForward"));
				}
				
				manager.update(students);
			}
		}

	}

}
