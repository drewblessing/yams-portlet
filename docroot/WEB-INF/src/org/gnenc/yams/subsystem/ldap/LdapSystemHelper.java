package org.gnenc.yams.subsystem.ldap;

import java.util.List;

import org.gnenc.yams.model.GNENCSystem;
import org.gnenc.yams.service.SystemManagementService;
import org.gnenc.yams.service.impl.SystemManagementServiceImpl;
import org.gnenc.yams.subsystem.ldap.model.LdapSystem;
import org.springframework.ldap.core.DistinguishedName;

public class LdapSystemHelper {

		private LdapSystemHelper() {}

		public static final void convertLdapSystemToGNENCSystem(final LdapSystem ldap, final GNENCSystem system) {
			system.setDn(ldap.getDn().toString());
			system.setEsuccSystemPadding(ldap.getEsuccSystemPadding());
			system.setEsuccSystemUIDNext(ldap.getEsuccSystemUIDNext());
			system.setOrganization(ldap.getOrganization());
			
		}

		public static String computeEsuccUidNumber() {
			SystemManagementService sms = SystemManagementServiceImpl.getInstance();
			
			List<GNENCSystem> systems = sms.getGNENCSystem();
			StringBuilder esuccUidNumber = new StringBuilder();
			
			System.out.println("System: " + systems.get(0).getEsuccSystemPadding());
			
			if (systems.size() == 1) {
				String padding = systems.get(0).getEsuccSystemPadding();
				String uidNext = systems.get(0).getEsuccSystemUIDNext();
				long uidCurrent = Long.valueOf(uidNext);
				if (uidNext.length() != 8) { 
					StringBuilder sb = new StringBuilder();
					for (int i=uidNext.length(); i<8; i++) {
						sb.append("0");
					}
					sb.append(uidNext);
					uidNext = sb.toString();
				}
				
				esuccUidNumber.append(padding).append(uidNext);
				
				GNENCSystem system = systems.get(0);
				sms.modifyGNENCSystem(system);
			} 
			
			return esuccUidNumber.toString();
		}
		
		public static String computeUidNumber(String esuccUidNumber) {
			String uidNumber = esuccUidNumber.substring(4,esuccUidNumber.length());
			return uidNumber.replaceFirst("^0+(?!$)", "");
		}

}