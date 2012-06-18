package org.gnenc.yams.subsystem.ldap;

import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Name;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.Group;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.subsystem.ldap.model.LdapGroup;

import org.springframework.ldap.core.DistinguishedName;

/**
 *
 * @author Jeshurun Daniel
 *
 */
public final class LdapGroupHelper {

	private static final Logger logger = Logger.getLogger(LdapGroupHelper.class);

	private static final String GROUP_SPACER = " - ";
	private static final String GROUP_BASE_OU = "groups";
	public static final String GROUP_BASE_DN = "ou=" + GROUP_BASE_OU;

	private LdapGroupHelper() {}

	private static final String EMPTY_GROUP_MEMBER = "cn=admin";
	public static final String EMPTY_GROUP_MEMBER_DN = EMPTY_GROUP_MEMBER + "," + LdapHelper.DEFAULT_BASE_DN;

	public static final boolean isLdapGroup(String groupContainer) {
		return groupContainer.toLowerCase().startsWith(SubSystem.LDAP.name().toLowerCase());
	}

	public static final Name buildGroupDn(final String containerName, final String groupCn) {
		if (!isLdapGroup(containerName))
			return null;
		final String[] containerNameToken = containerName.split(GROUP_SPACER);
		if (containerNameToken.length < 2)
			return null;
		final DistinguishedName dn = new DistinguishedName();
		dn.append("ou", GROUP_BASE_OU);
		dn.append("ou", containerNameToken[1]);
		dn.append("cn", groupCn);
		return dn;
	}

	public static final LdapGroup convertSystemGroupToLdapGroup(final String container, final Group group) {

		final Name dn = buildGroupDn(container, group.getCn());
		if (dn == null) {
			return null;
		}

		final LdapGroup ldap = new LdapGroup();
		ldap.setDn(dn);
		ldap.setCn(group.getCn());
		ldap.setDescription(group.getDescription());
		ldap.setMembers(convertUidToMemberDns(group.getMembers()));

		return ldap;
	}

	public static final Group convertLdapGroupToSystemGroup(final LdapGroup ldap, final Group group) {

		group.setCn(ldap.getCn());
		group.setDescription(ldap.getDescription() == null ?
				StringPool.BLANK : ldap.getDescription());
		group.setDisplayName(StringUtils.capitalize(ldap.getCn()));
		group.addAttribute("dn", ldap.getDn().toString());

		for (final String m : ldap.getMembers()) {
			try{
				final DistinguishedName dn = new DistinguishedName(m);
				final String uid = dn.getValue("uid");
				final Account a = new Account();
				a.setUid(uid);
				group.getMembers().add(a);
			} catch(IllegalArgumentException e) {} // admin dn
		}

		return group;
	}

	public static final List<String> convertUidToMemberDns(final List<Account> accounts) {
		final List<String> members = new ArrayList<String>();

		if (accounts.isEmpty()) {
			members.add(EMPTY_GROUP_MEMBER_DN);
		} else {
			for (final Account a : accounts) {
				logger.debug("Adding LDAP Member: uniqueMember=" +
						LdapAccountHelper.computeDn(a).toUrl() + "," + LdapHelper.DEFAULT_BASE_DN);
				members.add(LdapAccountHelper.computeDn(a).toUrl() + "," + LdapHelper.DEFAULT_BASE_DN);
			}
		}

		return members;
	}

	public static final String getGroupContainer(Name name) {
		final DistinguishedName dn = new DistinguishedName(name);

		String groupContainer;
		try {
			groupContainer = SubSystem.LDAP.name() + GROUP_SPACER + new DistinguishedName(dn.get(1)).getValue("ou");
		} catch (Exception e) {
			// Groups must exist at the Group base ou only
			groupContainer = GROUP_BASE_OU;
		}

		return groupContainer;
	}

}