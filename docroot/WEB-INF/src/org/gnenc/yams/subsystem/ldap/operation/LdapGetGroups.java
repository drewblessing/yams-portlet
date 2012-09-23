package org.gnenc.yams.subsystem.ldap.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.gnenc.yams.model.Group;
import org.gnenc.yams.operation.group.GetGroups;
import org.gnenc.yams.subsystem.ldap.LdapGroupHelper;
import org.gnenc.yams.subsystem.ldap.LdapHelper;
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
@Service("ldapGetGroups")
public class LdapGetGroups extends AbstractLdapOperation implements GetGroups {

	@Autowired
	private OdmManager manager;

	private static final Logger logger = Logger.getLogger(LdapGetGroups.class);
	private static final boolean debug = logger.isDebugEnabled();

	public void getGroups(final Map<String, List<Group>> groupsMap, final String filter) {

		long start = System.currentTimeMillis();

		final List<LdapGroup> ldap = manager.search(LdapGroup.class,
				new DistinguishedName(LdapGroupHelper.GROUP_BASE_DN),
				filter, LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);

//		final List<LdapGroup> ldap = manager.findAll(
//				LdapGroup.class, new DistinguishedName(LdapGroupHelper.GROUP_BASE_DN),
//				LdapHelper.SEARCH_CONTROL_ALL_SUBTREE_SCOPE);
		
		System.out.println("LDAP groups found: " + ldap.size());

		if (debug) {
			logger.debug("Time to fetch LDAP Groups ms: " + (System.currentTimeMillis() - start));
			logger.debug("Total Groups fetched: " + (ldap == null ? 0 : ldap.size()));
			start = System.currentTimeMillis();
		}

		if (ldap != null) {
			for (final LdapGroup l : ldap) {

				final String groupContainer = LdapGroupHelper.getGroupContainer(l.getDn());
				final List<Group> groups = groupsMap.containsKey(groupContainer) ?
						groupsMap.get(groupContainer) : new ArrayList<Group>();
				final Group group = new Group();
				groups.add(LdapGroupHelper.convertLdapGroupToSystemGroup(l, group));
				groupsMap.put(groupContainer, groups);
			}

			if (debug) {
				logger.debug("Time to parse LDAP Groups ms: " + (System.currentTimeMillis() - start));
			}
		}
	}
}