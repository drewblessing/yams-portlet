package org.gnenc.yams.subsystem.ldap;

import org.gnenc.yams.model.Domain;
import org.gnenc.yams.subsystem.ldap.model.LdapDomain;
import org.springframework.ldap.core.DistinguishedName;
public class LdapDomainHelper {

		private LdapDomainHelper() {}

		public static final void convertLdapDomainToSystemDomain(final LdapDomain ldap, final Domain domain) {
			domain.setDn(ldap.getDn().toString());
			domain.setEsuccEntity(ldap.getEsuccEntity());
			domain.setEsuccMailProvider(ldap.getEsuccMailProvider());
			domain.setEsuccProvider(ldap.getEsuccProvider());
			domain.setEsuccSystem(ldap.getEsuccSystem());
			domain.setOrganization(ldap.getOrganization());
			
		}

		public static final void convertSystemDomainToLdapDomain(Domain domain, LdapDomain ldap) {
			ldap.setDn(new DistinguishedName(domain.getDn()));
			ldap.setEsuccEntity(domain.getEsuccEntity());
			ldap.setEsuccMailProvider(domain.getEsuccMailProvider());
			ldap.setEsuccProvider(domain.getEsuccProvider());
			ldap.setEsuccSystem(domain.getEsuccSystem());
			ldap.setOrganization(domain.getOrganization());

		}

}