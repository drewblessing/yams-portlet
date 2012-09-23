package org.gnenc.yams.subsystem.ldap.operation;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.apache.log4j.Logger;
import org.gnenc.yams.model.GNENCSystem;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.operation.account.ModifyGNENCSystem;
import org.gnenc.yams.subsystem.ldap.LdapAccountHelper;
import org.gnenc.yams.subsystem.ldap.LdapHelper;
import org.gnenc.yams.subsystem.ldap.model.LdapSystem;
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
@Service("ldapModifyGNENCSystem")
public class LdapModifyGNENCSystem extends AbstractLdapOperation implements
	ModifyGNENCSystem {

	@Autowired
	private LdapTemplate template;
	
	@Autowired
	private OdmManager manager;

	private static final Logger logger = Logger.getLogger(LdapModifyGNENCSystem.class);
	private static final boolean debug = logger.isDebugEnabled();

	public void modifyGNENCSystem(final GNENCSystem system) {

		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.o, "GNENC", false));

		final String filter = SearchFilter.buildFilterString(filters, Operand.AND, false);
		
		final List<LdapSystem> ldapSystems = manager.search(LdapSystem.class,
				new DistinguishedName(), 
				filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
		System.out.println("LDAP SYSTEM: " + ldapSystems.get(0).getEsuccSystemUIDNext());
		System.out.println("LDAP SYSTEM: " + ldapSystems.get(0).getOrganization());

		System.out.println("LDAP SYSTEM: " + ldapSystems.get(0).getEsuccSystemPadding());
		try {
			LdapSystem ldap = ldapSystems.get(0);
			long uidNext = Long.valueOf(ldap.getEsuccSystemUIDNext());
			
			ldap.setEsuccSystemUIDNext(String.valueOf(uidNext++));
			String uidNumber = String.valueOf(uidNext++);
			Name dn = LdapAccountHelper.parseDn(ldap.getDn().toString());
			
			Attribute attr = new BasicAttribute("esucc-systemuidnext", uidNumber);
			ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr); 

			template.modifyAttributes(dn, new ModificationItem[] {item});
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		

	}

}