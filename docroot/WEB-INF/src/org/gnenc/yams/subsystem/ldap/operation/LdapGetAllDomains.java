package org.gnenc.yams.subsystem.ldap.operation;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.gnenc.yams.model.Domain;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.operation.account.GetAllDomains;
import org.gnenc.yams.subsystem.ldap.LdapDomainHelper;
import org.gnenc.yams.subsystem.ldap.LdapHelper;
import org.gnenc.yams.subsystem.ldap.model.LdapDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.odm.core.OdmManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author Drew A. Blessing
 *
 */
@Service("ldapGetAllDomains")
public class LdapGetAllDomains extends AbstractLdapOperation implements
	GetAllDomains {

	@Autowired
	private OdmManager manager;

	private static final Logger logger = Logger.getLogger(LdapGetAllDomains.class);
	private static final boolean debug = logger.isDebugEnabled();

	@Override
	public void getAllDomains(final Map<String, Domain> domains, final String filter) {

		long start = System.currentTimeMillis();
		
		final List<LdapDomain> ldapDomains = manager.search(LdapDomain.class,
				DistinguishedName.EMPTY_PATH, filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
		
		for (LdapDomain ldap : ldapDomains) {
			try {
				final String organization = ldap.getOrganization();
				final Domain domain = domains.containsKey(organization) ? domains.get(organization) : new Domain();
				LdapDomainHelper.convertLdapDomainToSystemDomain(ldap, domain);
				domain.getSubsystems().add(SubSystem.LDAP);
				domains.put(organization, domain);
			} catch (NullPointerException npe) {
				// User has incorrect object classes
			}
		}
		System.out.println("Domains:" + domains.size());
		if (debug) {
			logger.debug("Time to parse LDAP search results ms: " + (System.currentTimeMillis() - start));
			start = System.currentTimeMillis();
		}

	}

}