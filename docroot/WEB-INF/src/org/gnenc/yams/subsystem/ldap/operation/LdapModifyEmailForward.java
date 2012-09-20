package org.gnenc.yams.subsystem.ldap.operation;


import java.util.ArrayList;
import java.util.List;

import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

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
	private LdapTemplate template;
	
	@Autowired
	private OdmManager manager;
	
	public void validateEmailForward(Account account, String emailForward) {
		
	}
	
	public void modifyEmailForward(Account account, String emailForward, boolean delete) {
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
				Name dn = LdapAccountHelper.parseDn(staff.getDn().toString());
				
				staff.setDn(dn);
				
				if (delete) {
					
					Attribute attr = new BasicAttribute("esucc-mailForward");
					ModificationItem item = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, attr); 

					template.modifyAttributes(dn, new ModificationItem[] {item});
				} else if (!emailForward.isEmpty()) {
					staff.setEsuccMailForward(emailForward);
					manager.update(staff);
				} 
				
			} else {
				throw new IndexOutOfBoundsException();
			}
		} catch (IndexOutOfBoundsException e) {
			final List<LdapAccountEsuccStudent> students = manager.search(LdapAccountEsuccStudent.class,
					DistinguishedName.EMPTY_PATH, filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
			
			if (students.size() == 1) {
				LdapAccountEsuccStudent student = students.get(0);
				LdapAccountHelper.convertSystemAccountToExistingLdapAccount(account, student);
				Name dn = LdapAccountHelper.parseDn(student.getDn().toString());
				student.setDn(dn);
				
				if (delete) {
					Attribute attr = new BasicAttribute("esucc-mailForward");
					ModificationItem item = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, attr); 

					template.modifyAttributes(dn, new ModificationItem[] {item});
				} else if (!emailForward.isEmpty()) {
					student.setEsuccMailForward(emailForward);
					manager.update(student);
				}
			}
		}

	}

}
