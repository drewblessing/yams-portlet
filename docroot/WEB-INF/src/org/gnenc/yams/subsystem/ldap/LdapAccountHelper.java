package org.gnenc.yams.subsystem.ldap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Name;
import javax.naming.directory.SearchControls;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.gnenc.yams.model.Account;
import org.gnenc.yams.portlet.util.PropsValues;
import org.gnenc.yams.subsystem.ldap.model.LdapAccount;
import org.gnenc.yams.subsystem.ldap.model.LdapAccountEsuccStaff;
import org.gnenc.yams.subsystem.ldap.model.LdapAccountEsuccStudent;
import org.gnenc.yams.subsystem.ldap.model.LdapEntityGroup;
import org.gnenc.yams.subsystem.ldap.model.LdapGroup;
import org.springframework.ldap.core.DistinguishedName;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
public class LdapAccountHelper {

		private static final Logger logger = Logger.getLogger(LdapAccountHelper.class);

		private static final boolean DEFAULT_MODE_SIMPLE = PropsValues.LDAP_ACCOUNT_DEFAULT_MODE == "simple" ? true : false;
		private static final String EMPTY_FIELD = StringPool.BLANK;
		private static final List<String> EMPTY_FIELD_LIST = getSingleValuedList(EMPTY_FIELD);
		private static final String EMPTY_DOB = PropsValues.LDAP_ACCOUNT_EMPTY_DOB;
		private static final String DEFAULT_PREFERRED_LANGUAGE = 
				PropsValues.LDAP_ACCOUNT_DEFAULT_PREFERRED_LANGUAGE;
//		private static final String DEFAULT_PROVINCE = "SK"; //?
		public static final String DEFAULT_DOMAIN = PropsValues.LDAP_ACCOUNT_DEFAULT_DOMAIN; 
		private static final String DEFAULT_USER_CONTAINER_DN = 
				PropsValues.LDAP_ACCOUNT_DEFAULT_USER_CONTAINER_DN;

		public static final String STAFF_PPOLICY_DN = "cn=staff,ou=ppolicies," + LdapHelper.DEFAULT_BASE_DN;
		public static final String STUDENT_PPOLICY_DN = "cn=student,ou=ppolicies," + LdapHelper.DEFAULT_BASE_DN;
		public static final String DEFAULT_PPOLICY_DN = "cn=default,ou=ppolicies," + LdapHelper.DEFAULT_BASE_DN;

		private static enum CheckedGroups { BOARD, FACILITY, LIBRARY, TIS, TRANSPORTATION, STUDENT_SERVICES, COORDINATORS, SUPERINTENDENTS, OFFICE_MANAGERS, TEACHERS, PRINCIPALS };

		public static final String[] ACCOUNT_OPR_ATTRS = new String[]
				{"pwdAccountLockedTime","pwdPolicySubentry","pwdChangedTime","pwdHistory","memberOf"};

		public static final SearchControls ACCOUNT_OPR_ATTRS_SEARCH_CONTROLS = new SearchControls(
				SearchControls.SUBTREE_SCOPE, 0, 0, null, true, false);

		static {
			final List<String> accountAttrs = LdapHelper.getAttributes(LdapAccount.class);
			accountAttrs.addAll(Arrays.asList(ACCOUNT_OPR_ATTRS));
			ACCOUNT_OPR_ATTRS_SEARCH_CONTROLS.setReturningAttributes(accountAttrs.toArray(new String[accountAttrs.size()]));
		}

		private LdapAccountHelper() {}

		public static final void validateSystemAccountCommon(final Account account, final List<String> validationErrors) {

			for (String m : account.getMail()) {
				if (m.split("@").length < 2) {
					validationErrors.add("Mail is invalid.");
					return;
				}
			}

			if (account.getUid() == null || account.getUid().trim().isEmpty()) {
				validationErrors.add("UID is invalid");
				return;
			}
			if (account.getSn() == null || account.getSn().trim().isEmpty()) {
				validationErrors.add("Last Name is invalid");
				return;
			}
			if (account.getSn() == null || account.getSn().trim().isEmpty()) {
				validationErrors.add("Last Name is invalid.");
				return;
			}
			if (account.getGivenName() == null || account.getGivenName().trim().isEmpty()) {
				validationErrors.add("First Name is invalid.");
				return;
			}
			if (account.getEmployeeNumber() == null || account.getEmployeeNumber() <= 0 || account.getEmployeeNumber() > 99999) {
				validationErrors.add("Employee Number is invalid");
				return;
			}
//			if (account.getL().isEmpty()) {
//				validationErrors.add("Location is invalid");
//				return;
//			}
		}
		
		public static final void convertLdapAccountToSystemAccount(final LdapAccountEsuccStudent ldap, final Account account) {

			account.setCn(ldap.getCn());
			account.setGivenName(ldap.getGivenName());
			account.getMail().addAll(ldap.getMail());
			account.setSn(ldap.getSn());
			account.setDn(ldap.getDn().toString());
			account.setAttribute("dn", ldap.getDn().toString());
			account.setAttribute("esuccEntity", ldap.getEsuccEntity());
			account.setAttribute("esuccProvider", ldap.getEsuccProvider());
			account.setAttribute("esuccSystem", ldap.getEsuccSystem());
			account.setAttribute("esuccMailPrimaryLocalPart", ldap.getEsuccMailPrimaryLocalPart());
			account.setAttribute("esuccMailPrimaryDomain", ldap.getEsuccMailPrimaryDomain());
			account.setAttribute("uidNumber", ldap.getUidNumber());
			
			//Optional attributes
			account.setDescription(
					ldap.getDescription() == null ? StringPool.BLANK : ldap.getDescription());
			account.setDisplayName(
					ldap.getDisplayName() == null ? ldap.getCn().get(0) : ldap.getDisplayName());
			account.setEmployeeNumber(
					ldap.getEmployeeNumber() == null ? 0 : ldap.getEmployeeNumber());
			account.setUid(ldap.getUid() == null ? StringPool.BLANK : ldap.getUid());
			account.setAttribute("esuccMailForward", ldap.getEsuccMailForward());
//			account.setAttribute("title", Validator.isNotNull(ldap.getTitle()) ? ldap.getTitle() : StringPool.BLANK);
		}

		public static final void convertLdapAccountToSystemAccount(final LdapAccountEsuccStaff ldap, final Account account) {
//			account.setAccountStatus(AccountStatus.valueOf(ldap.getStatus()));
//			account.setAccountType((ldap.getOrganizationalUnitName().equalsIgnoreCase(STAFF_OU)
//					|| ldap.getOrganizationalUnitName().equalsIgnoreCase(STAFF_ARCHIVE_OU))
//					? AccountType.EMPLOYEE : AccountType.STUDENT);

			account.setCn(ldap.getCn());
			account.setGivenName(ldap.getGivenName());
			account.getMail().addAll(ldap.getMail());
			account.setSn(ldap.getSn());
			account.setDn(ldap.getDn().toString());
			account.setAttribute("dn", ldap.getDn().toString());
			account.setAttribute("esuccEntity", ldap.getEsuccEntity());
			account.setAttribute("esuccProvider", ldap.getEsuccProvider());
			account.setAttribute("esuccSystem", ldap.getEsuccSystem());
			account.setAttribute("esuccMailPrimaryLocalPart", ldap.getEsuccMailPrimaryLocalPart());
			account.setAttribute("esuccMailPrimaryDomain", ldap.getEsuccMailPrimaryDomain());
			account.setAttribute("uidNumber", ldap.getUidNumber());
			
			System.out.println("UID Number: " + ldap.getUidNumber());
			
			//Optional attributes
			account.setDescription(
					ldap.getDescription() == null ? StringPool.BLANK : ldap.getDescription());
			account.setDisplayName(
					ldap.getDisplayName() == null ? ldap.getCn().get(0) : ldap.getDisplayName());
			account.setEmployeeNumber(
					ldap.getEmployeeNumber() == null ? 0 : ldap.getEmployeeNumber());
			account.setUid(ldap.getUid() == null ? StringPool.BLANK : ldap.getUid());
			account.setAttribute("esuccMailForward", ldap.getEsuccMailForward());
//			account.setAttribute("title", Validator.isNotNull(ldap.getTitle()) ? ldap.getTitle() : StringPool.BLANK);
//			account.setDepartmentName(ldap.getDepartmentName());

//			account.setEmployeeType(EmployeeType.valueOf(ldap.getEmployeeType()));
//			account.setInitials(ldap.getInitials());
//			account.setJpegPhoto(ldap.getJpegPhoto() == null ? "" : new String(ldap.getJpegPhoto()));
//			account.setOrganizationName(ldap.getOrganizationName());
//			account.setPostalCode(ldap.getPostalCode());
//			account.setPreferredLanguage(ldap.getPreferredLanguage().get(0));
//			account.setRoomNumber(ldap.getRoomNumber());
//			account.setSt(ldap.getSt());

//			account.getHomePostalAddress().addAll(ldap.getHomePostalAddress());
//			account.getHomePhone().addAll(ldap.getHomePhone());
//			account.getL().addAll(ldap.getL());
//			account.getMobile().addAll(ldap.getMobile());
//			account.getSecurityQuestion().addAll(ldap.getSecurityQuestion());
//			account.getSecurityAnswer().addAll(ldap.getSecurityAnswer());
//			account.getStreet().addAll(ldap.getStreet());
//			account.getTelephoneNumber().addAll(ldap.getTelephoneNumber());

//			if (!ldap.getDateOfBirth().equals(EMPTY_DOB)) {
//				int year = Integer.valueOf(ldap.getDateOfBirth().substring(0, 3));
//				int month = Integer.valueOf(ldap.getDateOfBirth().substring(4, 5)) - 1;
//				int date = Integer.valueOf(ldap.getDateOfBirth().substring(6, 7));
//				Calendar cal = Calendar.getInstance();
//				cal.set(year, month, date);
//				account.setDateOfBirth(cal.getTime());
//			}
		}
		
		public static final void convertSystemAccountToLdapAccount(Account account, LdapAccountEsuccStudent ldap) {
			if (!account.getCn().isEmpty()) {
				ldap.setCn(account.getCn());
			} else {
				List<String> cn = new ArrayList<String>(1);
				cn.add(account.getGivenName() + " " + account.getSn());
				ldap.setCn(cn);
			}
			ldap.setGivenName(account.getGivenName());
//			ldap.setMail(parseMail(account));
			ldap.setMail(account.getMail());
//			ldap.setUid(account.getUid());
			ldap.setUid(account.getUid());
			ldap.setSn(account.getSn());
			ldap.setEsuccEntity(account.getAttribute("esuccEntity"));
			ldap.setEsuccMailPrimaryLocalPart(account.getAttribute("esuccMailPrimaryLocalPart"));
			ldap.setEsuccMailPrimaryDomain(account.getAttribute("esuccMailPrimaryDomain"));
			ldap.setOrganizationName(account.getAttribute("esuccMailPrimaryDomain"));
			ldap.setHomeDirectory("/tmp/" + account.getUid());
			ldap.setGidNumber("10243");
			
			//Optional attributes
			ldap.setDescription(
					account.getDescription() == null ? StringPool.BLANK : ldap.getDescription());
			ldap.setDisplayName(account.getDisplayName().isEmpty() ? 
							account.getGivenName() + StringPool.SPACE + account.getSn() : account.getDisplayName());
//			ldap.setTitle(account.getAttribute("title") == null ? StringPool.NULL : parseAccountField(account.getAttribute("title")));
		}

		public static final void convertSystemAccountToLdapAccount(Account account, LdapAccountEsuccStaff ldap) {
			ldap.setCn(account.getCn());
			ldap.setGivenName(account.getGivenName());
			ldap.setMail(parseMail(account));
			ldap.setUid(parseUid(account));
			ldap.setSn(account.getSn());
			ldap.setEsuccEntity(account.getAttribute("esuccEntity"));
			ldap.setEsuccMailPrimaryLocalPart(account.getAttribute("esuccMailPrimaryLocalPart"));
			ldap.setEsuccMailPrimaryDomain(account.getAttribute("esuccMailPrimaryDomain"));
			ldap.setOrganizationName(account.getAttribute("esuccMailPrimaryDomain"));
			ldap.setUidNumber(account.getAttribute("uidNumber"));
			ldap.setHomeDirectory("/tmp/" + account.getUid());
			ldap.setGidNumber("10243");
			
			//Optional attributes
			ldap.setDisplayName(account.getDisplayName().isEmpty() ? 
							account.getGivenName() + StringPool.SPACE + account.getSn() : account.getDisplayName());
//			ldap.setTitle(account.getAttribute("title") == null ? StringPool.NULL : parseAccountField(account.getAttribute("title")));
			
		}
		
		public static final void convertSystemAccountToExistingLdapAccount(Account account, LdapAccountEsuccStaff ldap) {
			ldap.setCn(account.getCn());
			ldap.setDisplayName(account.getDisplayName());
			ldap.setGivenName(account.getGivenName());
			ldap.setSn(account.getSn());
//			ldap.setTitle(account.getAttribute("title") == null ? StringPool.BLANK : parseAccountField(account.getAttribute("title")));
			ldap.setEsuccScreenName(account.getAttribute("screenName"));
		}
		
		public static final void convertSystemAccountToExistingLdapAccount(Account account, LdapAccountEsuccStudent ldap) {
			ldap.setCn(account.getCn());
			ldap.setDisplayName(account.getDisplayName());
			ldap.setGivenName(account.getGivenName());
			ldap.setSn(account.getSn());
//			ldap.setTitle(account.getAttribute("title") == null ? StringPool.BLANK : parseAccountField(account.getAttribute("title")));
			ldap.setEsuccScreenName(account.getAttribute("screenName"));
		}
		
		
		private static String parseUid(Account account) {
			return account.getUid().toLowerCase();
		}

		private static final String parseAccountField(String field) {
			if (!field.trim().isEmpty()) return field;
			return EMPTY_FIELD;
		}

		private static final List<String> parseAccountList(final List<String> listValue) {
			if (!listValue.isEmpty()) return listValue;
			return EMPTY_FIELD_LIST;
		}

		public static final List<String> getSingleValuedList(final String value) {
			if (value == null || value.isEmpty()) return EMPTY_FIELD_LIST;
			final List<String> result = new ArrayList<String>();
			result.add(value);
			return result;
		}
		
//		public static final DistinguishedName computeDn(final LdapAccountEsuccStaff account) {
			
			
//			StringBuilder sb = new StringBuilder("uid=");
//			sb.append(account.getUid()).append(",");
//			sb.append("ou=staff,");
//			sb.append("o=").append(account.getEsuccMailPrimaryDomain()).append(",");
//			sb.append("o=").append(account.getEsuccProvider()).append(",");
//			sb.append("o=").append(account.getEsuccSystem());
//			
//			return new DistinguishedName(sb.toString());
//		}
		
		public static final DistinguishedName computeDn(final LdapAccountEsuccStudent account, final LdapGroup group) {
			StringBuilder sb = new StringBuilder();
			sb.append("uid=").append(account.getUid()).append(",");
			
			List<String> seeAlsos = group.getSeeAlso();
			for (String seeAlso : seeAlsos) {
				if (seeAlso.contains(account.getEsuccMailPrimaryDomain())) {

					String dnString = parseDnToString(seeAlso);
					sb.append(dnString);
				}
			}
//			String dnString = seeAlso.substring(0, seeAlso.indexOf(LdapHelper.DEFAULT_BASE_DN)-1)
			
			System.out.println("New dn: " + sb.toString());
			
			return new DistinguishedName(sb.toString());
		}

		public static final DistinguishedName computeDn(final LdapAccountEsuccStaff account, final LdapGroup group) {
			StringBuilder sb = new StringBuilder();
			sb.append("uid=").append(account.getUid()).append(",");
			
			List<String> seeAlsos = group.getSeeAlso();
			for (String seeAlso : seeAlsos) {
				if (seeAlso.contains(account.getEsuccMailPrimaryDomain())) {

					String dnString = parseDnToString(seeAlso);
					sb.append(dnString);
				}
			}
			
			System.out.println("New dn: " + sb.toString());
			
			return new DistinguishedName(sb.toString());
		}
		
		public static final DistinguishedName parseDn(String dn) {
			String dnString;
			if (dn.indexOf(",dc") != -1) {
				dnString = dn.substring(0, dn.indexOf(",dc"));
			} else {
				dnString = dn;
			}
			
			return new DistinguishedName(dnString);
		}
		
		public static final String parseDnToString(String dn) {
			Name trueDn = parseDn(dn);
			
			return trueDn.toString(); 
		}
		
		private static final String getOrganization(final String dn) {
			return dn.substring(dn.indexOf("o=")) + "," + LdapHelper.DEFAULT_BASE_DN;
		}
		
		private static final String getOrganizationalUnit(final String dn) {
			return dn.substring(dn.indexOf("ou=")) + "," + LdapHelper.DEFAULT_BASE_DN;
		}

//		private static final String getOrganizationalUnit(final AccountType type, final AccountStatus status) {
//			switch(type) {
//			case EMPLOYEE:
//				switch(status) {
//				case ACTIVE:
//					return STAFF_OU;
//				default:
//					return STAFF_ARCHIVE_OU;
//				}
//			case STUDENT:
//				switch(status) {
//				case ACTIVE:
//					return STUDENT_OU;
//				default:
//					return STUDENT_ARCHIVE_OU;
//				}
//			default:
//				return STAFF_OU;
//			}
//		}

//		private static final List<String> getObjectClass(final AccountType type) {
//			switch(type) {
//			case EMPLOYEE:
//				return getSingleValuedList(STAFF_OBJECT_CLASS);
//			case STUDENT:
//				return getSingleValuedList(STUDENT_OBJECT_CLASS);
//			default:
//				return getSingleValuedList(STAFF_OBJECT_CLASS);
//			}
//		}

		private static final String parseInitials(Account account) {
//			if (!account.getInitials().isEmpty()) return account.getInitials();
			return account.getGivenName().charAt(0) + " " + account.getSn().charAt(0);
		}

		private static final String parseDateOfBirth(Date dob) {
			if (dob == null) return EMPTY_DOB;
			Calendar c = Calendar.getInstance();
			c.setTime(dob);
			return "" + c.get(Calendar.YEAR) + StringUtils.leftPad(String.valueOf(c.get(Calendar.MONTH) + 1), 2, "0") +
					StringUtils.leftPad(String.valueOf(c.get(Calendar.DATE)), 2, "0");
		}

//		private static final String parseDescription(Account account) {
//			if (!account.getDescription().isEmpty()) return account.getDescription();
//			return account.getAccountType().name() + " - " + account.getTitle() + " @ " + account.getL().get(0) + " for the " + ORGANIZATION_NAME;
//		}

//		private static final String parseState(String state) {
//			if (!state.isEmpty()) return state;
//			return DEFAULT_PROVINCE;
//		}

		private static final List<String> parseMail(Account account) {
			final List<String> mails = new ArrayList<String>();
			if (!account.getMail().isEmpty()) {
				boolean hasUidAccount = false;
				for (String m : account.getMail()) {
					if (!hasUidAccount && m.split(StringPool.AT)[0].equals(account.getUid())) {
						hasUidAccount = true;
					}
					mails.add(m.toLowerCase());
				}
//				if (!hasUidAccount) {
//					String mail = account.getUid() + StringPool.AT + DEFAULT_DOMAIN;
//					mails.add(mail.toLowerCase());
//				}
			} else {
				String mail = account.getUid() + StringPool.AT + DEFAULT_DOMAIN;
				mails.add(mail.toLowerCase());
			}
			return mails;
		}

		private static boolean isMember(final CheckedGroups membershipType, final String department, final String title,
				final Map<String, LdapEntityGroup> ldapGroupsMap, final Set<String> existingMembershipGroupNames) {

			switch (membershipType) {
			case BOARD:
				if (ldapGroupsMap.containsKey("board")) {
					return department.equals("board") || title.contains("executive secretary") ||
							(department.equals("coordinator") && title.contains("communications")) ||
									(department.equals("management") &&
											(title.contains("director") || title.contains("secretary treasurer")));
				}
				break;

			case COORDINATORS:
				return (ldapGroupsMap.containsKey("coordinators") && department.equals("coordinator"))
						|| existingMembershipGroupNames != null && existingMembershipGroupNames.contains("coordinators");

			case FACILITY:
				return (ldapGroupsMap.containsKey("facilities") && department.equals("facility"))
						|| existingMembershipGroupNames != null && existingMembershipGroupNames.contains("facilities");

			case LIBRARY:
				return (ldapGroupsMap.containsKey("library") && department.equals("library"))
						|| existingMembershipGroupNames != null && existingMembershipGroupNames.contains("library");

			case OFFICE_MANAGERS:
				return ldapGroupsMap.containsKey("officemanagers") &&
						(department.equals("school support") && title.equals("office manager"));
//						|| existingMembershipGroupNames != null && existingMembershipGroupNames.contains("officemanagers");
			case PRINCIPALS:
				return ldapGroupsMap.containsKey("principals") &&
						(title.contains("principal") || title.contains("vice principal")
								|| existingMembershipGroupNames != null && existingMembershipGroupNames.contains("principals"));

			case STUDENT_SERVICES:
				return (ldapGroupsMap.containsKey("studentservices") && department.equals("student servies"))
						|| existingMembershipGroupNames != null && existingMembershipGroupNames.contains("studentservices");

			case TIS:
				return ldapGroupsMap.containsKey("tis") && department.equals("tis");

			case SUPERINTENDENTS:
				return ldapGroupsMap.containsKey("superintendents") &&
						(department.equals("management") && title.contains("superintendent"))
						|| existingMembershipGroupNames != null && existingMembershipGroupNames.contains("superintendents");

			case TEACHERS:
				return department.equals("teacher")
						|| isMember(CheckedGroups.PRINCIPALS, department, title, ldapGroupsMap, existingMembershipGroupNames)
						|| isMember(CheckedGroups.SUPERINTENDENTS, department, title, ldapGroupsMap, existingMembershipGroupNames)
						|| isMember(CheckedGroups.COORDINATORS, department, title, ldapGroupsMap, existingMembershipGroupNames)
						|| (department.equals("management") && title.contains("director"));
			case TRANSPORTATION:
				return ldapGroupsMap.containsKey("transportation") && department.equals("transportation");
			}

			return false;
		}
}