package org.gnenc.yams.subsystem.ldap.operation;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.gnenc.yams.model.GNENCSystem;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.operation.account.GetGNENCSystem;
import org.gnenc.yams.subsystem.ldap.LdapHelper;
import org.gnenc.yams.subsystem.ldap.LdapSystemHelper;
import org.gnenc.yams.subsystem.ldap.model.LdapSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.odm.core.OdmManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author Drew A. Blessing
 *
 */
@Service("ldapGetGNENCSystem")
public class LdapGetGNENCSystem extends AbstractLdapOperation implements
	GetGNENCSystem {

	@Autowired
	private OdmManager manager;

	private static final Logger logger = Logger.getLogger(LdapGetGNENCSystem.class);
	private static final boolean debug = logger.isDebugEnabled();

	public void getGNENCSystem(final Map<String, GNENCSystem> systems, final String filter) {

		long start = System.currentTimeMillis();
		
		final List<LdapSystem> ldapSystems = manager.search(LdapSystem.class,
				new DistinguishedName(), 
				filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
		
//		System.out.println("GNENC System: " + ldapSystems.get(0).getEsuccSystemPadding());
		
		try {
			LdapSystem ldap = ldapSystems.get(0);
			final String organization = ldap.getOrganization();
			final GNENCSystem system = new GNENCSystem();
			LdapSystemHelper.convertLdapSystemToGNENCSystem(ldap, system);
			system.getSubsystems().add(SubSystem.LDAP);
			systems.put(organization, system);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		if (debug) {
			logger.debug("Time to parse LDAP search results ms: " + (System.currentTimeMillis() - start));
			start = System.currentTimeMillis();
		}

	}

}