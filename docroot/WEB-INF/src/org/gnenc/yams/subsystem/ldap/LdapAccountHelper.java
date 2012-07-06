package org.gnenc.yams.subsystem.ldap;

import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Name;
import javax.naming.directory.SearchControls;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.AccountStatus;
import org.gnenc.yams.model.AccountType;
import org.gnenc.yams.subsystem.ldap.model.LdapAccount;
import org.gnenc.yams.subsystem.ldap.model.LdapGroup;

import org.springframework.ldap.core.DistinguishedName;
public class LdapAccountHelper {

		private static final Logger logger = Logger.getLogger(LdapAccountHelper.class);

		private static final String EMPTY_FIELD = "NONE";
		private static final List<String> EMPTY_FIELD_LIST = getSingleValuedList(EMPTY_FIELD);
		private static final String EMPTY_DOB = "00000000";
		private static final String DEFAULT_PREFERRED_LANGUAGE = "en-US";
		private static final String DEFAULT_PROVINCE = "SK"; //?
		public static final String DEFAULT_DOMAIN = "chinooksd.ca"; //?

		private static final String O_M_SUFFIX = "officemanagers";
		private static final String PRINCIPALS_SUFFIX = "principals";
		private static final String TEACHERS_SUFFIX = "teachers";

		public static final String STAFF_OU = "staff";
		public static final String STAFF_ARCHIVE_OU = "staffArchive";
		public static final String STUDENT_OU = "students";
		public static final String STUDENT_ARCHIVE_OU = "studentsArchive";
		private static final String STAFF_OBJECT_CLASS = "chinookStaff";
		private static final String STUDENT_OBJECT_CLASS = "chinookStudent";

		private static final String ORGANIZATION_NAME = "Chinook School Division";

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

		public static final void convertLdapAccountToSystemAccount(final LdapAccount ldap, final Account account) {
//			account.setAccountStatus(AccountStatus.valueOf(ldap.getStatus()));
//			account.setAccountType((ldap.getOrganizationalUnitName().equalsIgnoreCase(STAFF_OU)
//					|| ldap.getOrganizationalUnitName().equalsIgnoreCase(STAFF_ARCHIVE_OU))
//					? AccountType.EMPLOYEE : AccountType.STUDENT);

			// Required attributes
			try {
				account.setCn(ldap.getCn());
				account.setSn(ldap.getSn());
			} catch (NullPointerException e) {
				// Invalid account in LDAP
				// TODO: handle this better
				e.printStackTrace();
			}

			//Optional attributes
			account.setDescription(
					ldap.getDescription() == null ? StringPool.BLANK : ldap.getDescription());
			account.setDisplayName(
					ldap.getDisplayName() == null ? ldap.getCn().get(0) : ldap.getDisplayName());
			account.setEmployeeNumber(
					ldap.getEmployeeNumber() == null ? 0 : ldap.getEmployeeNumber());
			account.setGivenName(ldap.getGivenName() == null ? StringPool.BLANK : ldap.getGivenName());
			account.getMail().addAll(
					ldap.getMail() == null ? Collections.<String>emptyList() : ldap.getMail());
			account.setUid(ldap.getUid() == null ? StringPool.BLANK : ldap.getUid());
			account.setAttribute("dn", ldap.getDn().toString() + "," + LdapHelper.DEFAULT_BASE_DN);
//			account.setDepartmentName(ldap.getDepartmentName());

//			account.setEmployeeType(EmployeeType.valueOf(ldap.getEmployeeType()));
//			account.setInitials(ldap.getInitials());
//			account.setJpegPhoto(ldap.getJpegPhoto() == null ? "" : new String(ldap.getJpegPhoto()));
//			account.setOrganizationName(ldap.getOrganizationName());
//			account.setPostalCode(ldap.getPostalCode());
//			account.setPreferredLanguage(ldap.getPreferredLanguage().get(0));
//			account.setRoomNumber(ldap.getRoomNumber());
//			account.setSt(ldap.getSt());
//			account.setTitle(ldap.getTitle());

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

		public static final void convertSystemAccountToLdapAccount(Account account, LdapAccount ldap) {
			ldap.setUid(account.getUid());
			ldap.setGivenName(account.getGivenName());
			ldap.setSn(account.getSn());
			if (!account.getCn().isEmpty()) {
				ldap.setCn(account.getCn());
			} else {
				List<String> cn = new ArrayList<String>(1);
				cn.add(account.getGivenName() + " " + account.getSn());
				ldap.setCn(cn);
			}

			ldap.setDisplayName(account.getDisplayName().isEmpty() ? account.getGivenName() :
				account.getDisplayName().indexOf(" ") != -1 ? account.getDisplayName().split(" ")[0] : account.getDisplayName() );

			ldap.setDn(computeDn(account));

			ldap.setEmployeeNumber(account.getEmployeeNumber());

//			ldap.setEmployeeType(account.getEmployeeType().name());

//			ldap.setHomePhone(parseAccountList(account.getHomePhone()));

//			ldap.setHomePostalAddress(parseAccountList(account.getHomePostalAddress()));

			ldap.setMail(parseMail(account));

//			ldap.setMobile(parseAccountList(account.getMobile()));

//			ldap.setOrganizationalUnitName(getOrganizationalUnit(account.getAccountType(), account.getAccountStatus()));

//			ldap.setTitle(parseAccountField(account.getTitle()));

//			ldap.setTelephoneNumber(parseAccountList(account.getTelephoneNumber()));

			ldap.setOrganizationName(ORGANIZATION_NAME);

//			ldap.setStreet(parseAccountList(account.getStreet()));

//			ldap.setInitials(parseInitials(account));

//			ldap.setDateOfBirth(parseDateOfBirth(account.getDateOfBirth()));

//			ldap.setDepartmentName(parseAccountField(account.getDepartmentName()));

//			ldap.setPreferredLanguage(getSingleValuedList(DEFAULT_PREFERRED_LANGUAGE));

//			ldap.setRoomNumber(parseAccountField(account.getRoomNumber()));

//			ldap.setPostalCode(parseAccountField(account.getPostalCode()));

//			ldap.setDepartmentNumber(EMPTY_FIELD);

//			ldap.setSecurityQuestion(parseAccountList(account.getSecurityQuestion()));

//			ldap.setSecurityAnswer(parseAccountList(account.getSecurityAnswer()));

//			ldap.setSt(parseState(account.getSt()));

//			ldap.setL(parseAccountList(account.getL()));

//			ldap.setDescription(parseDescription(account));

//			ldap.setObjectClass(getObjectClass(account.getAccountType()));

//			ldap.setStatus(account.getAccountStatus().name());

		}

//		public static final void processAdditionalGroupMemberships(
//				final LdapAccount account, final Set<String> newMembershipGroupNames, final Set<String> existingMembershipGroupNames, List<LdapGroup> ldapGroups,
//				final Set<String> additionalGroups, final Set<String> removedGroups) {
////			final String department = account.getDepartmentName().toLowerCase();
//			final String title = account.getTitle().toLowerCase();
//			final Set<String> locations = new HashSet<String>();
//
//			// Make sure everything is in lower case
//			for (String l : account.getL()) {
//				locations.add(l.trim().toLowerCase());
//			}
//
//			final String school = getLdapSchoolName(locations);
//
//			final Map<String, LdapGroup> ldapGroupsMap = new HashMap<String, LdapGroup>();
//			for (LdapGroup g : ldapGroups) {
////				ldapGroupsMap.put(g.getCn().toLowerCase(), g);
//			}
//
//			final boolean checkForRemovedGroups = removedGroups != null && existingMembershipGroupNames != null;
//
//			if (isMember(CheckedGroups.BOARD, department, title, ldapGroupsMap, existingMembershipGroupNames)) {
//				additionalGroups.add(ldapGroupsMap.get("board").getCn());
//			} else if (checkForRemovedGroups && existingMembershipGroupNames.contains("board")) {
//				removedGroups.add("board");
//			}
//
//			if (isMember(CheckedGroups.FACILITY, department, title, ldapGroupsMap, existingMembershipGroupNames)) {
//				additionalGroups.add(ldapGroupsMap.get("facilities").getCn());
//			} else if (checkForRemovedGroups && existingMembershipGroupNames.contains("facilities")) {
//				removedGroups.add("facility");
//			}
//
//			if (isMember(CheckedGroups.LIBRARY, department, title, ldapGroupsMap, existingMembershipGroupNames)) {
//				additionalGroups.add(ldapGroupsMap.get("library").getCn());
//			} else if (checkForRemovedGroups && existingMembershipGroupNames.contains("library")) {
//				removedGroups.add("library");
//			}
//
//			if (isMember(CheckedGroups.STUDENT_SERVICES, department, title, ldapGroupsMap, existingMembershipGroupNames)) {
//				additionalGroups.add(ldapGroupsMap.get("studentservices").getCn());
//			} else if (checkForRemovedGroups && existingMembershipGroupNames.contains("studentservices")) {
//				removedGroups.add("student servies");
//			}
//
//			if (isMember(CheckedGroups.TIS, department, title, ldapGroupsMap, existingMembershipGroupNames)) {
//				additionalGroups.add(ldapGroupsMap.get("tis").getCn());
//			} else if (checkForRemovedGroups && existingMembershipGroupNames.contains("tis")) {
//				removedGroups.add("tis");
//			}
//
//			if (isMember(CheckedGroups.TRANSPORTATION, department, title, ldapGroupsMap, existingMembershipGroupNames)) {
//				additionalGroups.add(ldapGroupsMap.get("transportation").getCn());
//			} else if (checkForRemovedGroups && existingMembershipGroupNames.contains("transportation")) {
//				removedGroups.add("transportation");
//			}
//
//			if (isMember(CheckedGroups.COORDINATORS, department, title, ldapGroupsMap, existingMembershipGroupNames)) {
//				additionalGroups.add(ldapGroupsMap.get("coordinators").getCn());
//			} else if (checkForRemovedGroups && existingMembershipGroupNames.contains("coordinators")) {
//				removedGroups.add("coordinator");
//			}
//
//			if (isMember(CheckedGroups.SUPERINTENDENTS, department, title, ldapGroupsMap, existingMembershipGroupNames)) {
//				additionalGroups.add(ldapGroupsMap.get("superintendents").getCn());
//			} else if (checkForRemovedGroups && existingMembershipGroupNames.contains("superintendents")) {
//				removedGroups.add("superintendents");
//			}
//
//			if (isMember(CheckedGroups.OFFICE_MANAGERS, department, title, ldapGroupsMap, existingMembershipGroupNames)) {
//				additionalGroups.add(ldapGroupsMap.get("officemanagers").getCn());
//				if (!school.isEmpty()) {
//					final String omSchool = school + O_M_SUFFIX;
//					if (ldapGroupsMap.containsKey(omSchool)) {
//						additionalGroups.add(ldapGroupsMap.get(omSchool).getCn());
//					} else if (checkForRemovedGroups && existingMembershipGroupNames.contains(omSchool)) {
//						removedGroups.add(omSchool.toLowerCase());
//					}
//				}
//			} else if (checkForRemovedGroups) {
//
//				if (existingMembershipGroupNames.contains("officemanagers")) {
//					removedGroups.add("officemanagers");
//				}
//
//				// Handle role changes within the same location
//				for (final String m : existingMembershipGroupNames) {
//					if (m.endsWith("officemanagers") && m.length() > 14) {
//						removedGroups.add(m);
//					}
//				}
//			}
//
//			if (isMember(CheckedGroups.PRINCIPALS, department, title, ldapGroupsMap, existingMembershipGroupNames)) {
//				additionalGroups.add(ldapGroupsMap.get("principals").getCn());
//
//				if (!school.isEmpty()) {
//					final String principalSchool = school + PRINCIPALS_SUFFIX;
//
//					if (ldapGroupsMap.containsKey(principalSchool)) {
//						additionalGroups.add(ldapGroupsMap.get(principalSchool).getCn());
//					} else if (checkForRemovedGroups && existingMembershipGroupNames.contains(principalSchool)) {
//						removedGroups.add(principalSchool.toLowerCase());
//					}
//				}
//			} else if (checkForRemovedGroups) {
//				if (existingMembershipGroupNames.contains("principals")) {
//					removedGroups.add("principals");
//				}
//
//				for (final String m : existingMembershipGroupNames) {
//					if (m.endsWith("principals") && m.length() > 10) {
//						removedGroups.add(m);
//					}
//				}
//			}
//
//			if (isMember(CheckedGroups.TEACHERS, department, title, ldapGroupsMap, existingMembershipGroupNames)) {
//				if (ldapGroupsMap.containsKey("teachers")) {
//					additionalGroups.add(ldapGroupsMap.get("teachers").getCn());
//
//					if (!school.isEmpty()) {
//						final String teacherSchool = school + TEACHERS_SUFFIX;
//
//						if (ldapGroupsMap.containsKey(teacherSchool)) {
//							additionalGroups.add(ldapGroupsMap.get(teacherSchool).getCn());
//						} else if (checkForRemovedGroups && existingMembershipGroupNames.contains(teacherSchool)) {
//							removedGroups.add(teacherSchool.toLowerCase());
//						}
//					}
//				}
//
//			} else if (checkForRemovedGroups) {
//
//				if (existingMembershipGroupNames.contains("teachers")) {
//					removedGroups.add("teachers");
//				}
//
//				// Handle role changes within the same location
//				for (final String m : existingMembershipGroupNames) {
//					if (m.endsWith("teachers") && m.length() > 8) {
//						removedGroups.add(m);
//					}
//				}
//
//			}
//
//			if (locations.contains("chinook education center") || locations.contains("chinook main office")) {
//				if (!newMembershipGroupNames.contains("cec") && ldapGroupsMap.containsKey("cec")) {
//					additionalGroups.add(ldapGroupsMap.get("cec").getCn());
//				}
//			} else if (checkForRemovedGroups && existingMembershipGroupNames.contains("cec")) {
//				removedGroups.add("cec");
//			}
//
//			// Handle location changes
//			if (checkForRemovedGroups) {
//
//				for (final String m : existingMembershipGroupNames) {
//					if (m.endsWith("teachers") && m.length() > 8) {
//						final String teacherSchool = school + TEACHERS_SUFFIX;
//						if (!m.equalsIgnoreCase(teacherSchool)) {
//							removedGroups.add(m);
//						}
//					}
//
//					if (m.endsWith("principals") && m.length() > 10) {
//						final String principalSchool = school + PRINCIPALS_SUFFIX;
//						if (!m.equalsIgnoreCase(principalSchool)) {
//							removedGroups.add(m);
//						}
//					}
//
//					if (m.endsWith("officemanagers") && m.length() > 14) {
//						final String omSchool = school + O_M_SUFFIX;
//						if (!m.equalsIgnoreCase(omSchool)) {
//							removedGroups.add(m);
//						}
//					}
//				}
//			}
//
//
//			if (logger.isInfoEnabled()) {
//				for (String g : additionalGroups) {
//					logger.info("Automatically determined additional Group: " + g + " for Account: " + account.getUid());
//				}
//			}
//
//			// When we automatically remove a group, we also want it to be removed from the client's selection list.
//			if (checkForRemovedGroups) {
//				newMembershipGroupNames.removeAll(removedGroups);
//
//				if (logger.isInfoEnabled()) {
//					for (String g : removedGroups) {
//						logger.info("Automatically removed Account: " + account.getUid() + " from Group: " + g);
//					}
//				}
//			}
//
//		}
//
		private static final String getLdapSchoolName(final Set<String> locations) {
			for (String l : locations) {
				if (l.endsWith("school")) {
					// anamolies
					if (l.equalsIgnoreCase("Swift Current Comprehensive High School")) return "scchs";
					if (l.equalsIgnoreCase("Maple Creek Composite HS")) return "mcchs";
					if (l.equalsIgnoreCase("O.M. Irwin Middle School")) return "omirwin";
					if (l.equalsIgnoreCase("CAMPS (Transition) School")) return "camps";
					if (l.equalsIgnoreCase("Fairview Middle School")) return "fairview";
					if (l.equalsIgnoreCase("Success Elem School")) return "success";
					if (l.equalsIgnoreCase("Leader Composite School")) return "leader";

					String name = "";
					String[] names = l.split(" ");
					for (int i=0; i< names.length - 1; i++) {
						if (i == 0)
							name += names[i].toLowerCase();
						else
							name += names[i];
					}
					logger.info("Determined location name: " + name);
					return name.toLowerCase();
				}
			}
			return "";
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

		public static final DistinguishedName computeDn(final Account account) {
			StringBuilder sb = new StringBuilder("uid=");
			sb.append(account.getUid()).append(",ou=");
			sb.append("esu10.org,o=ESU10,o=GNENC");
//			sb.append(getOrganizationalUnit(account.getAccountType(), account.getAccountStatus()));
			return new DistinguishedName(sb.toString());
		}

		public static final Name computeDn(final String account, final AccountType accountType) {
			StringBuilder sb = new StringBuilder("uid=");
			sb.append(account).append(",ou=");
			sb.append("esu10.org,o=ESU10,o=GNENC");
			return new DistinguishedName(sb.toString());
		}

		public static final Name computeDn(final String account, final AccountType accountType, final AccountStatus status) {
			StringBuilder sb = new StringBuilder("uid=");
			sb.append(account).append(",ou=");
			sb.append("esu10.org,o=ESU10,o=GNENC");
//			sb.append(getOrganizationalUnit(accountType, status));
			return new DistinguishedName(sb.toString());
		}

		private static final String getOrganizationalUnit(final AccountType type, final AccountStatus status) {
			switch(type) {
			case EMPLOYEE:
				switch(status) {
				case ACTIVE:
					return STAFF_OU;
				default:
					return STAFF_ARCHIVE_OU;
				}
			case STUDENT:
				switch(status) {
				case ACTIVE:
					return STUDENT_OU;
				default:
					return STUDENT_ARCHIVE_OU;
				}
			default:
				return STAFF_OU;
			}
		}

		private static final List<String> getObjectClass(final AccountType type) {
			switch(type) {
			case EMPLOYEE:
				return getSingleValuedList(STAFF_OBJECT_CLASS);
			case STUDENT:
				return getSingleValuedList(STUDENT_OBJECT_CLASS);
			default:
				return getSingleValuedList(STAFF_OBJECT_CLASS);
			}
		}

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

		private static final String parseState(String state) {
			if (!state.isEmpty()) return state;
			return DEFAULT_PROVINCE;
		}

		private static final List<String> parseMail(Account account) {
			final List<String> mail = new ArrayList<String>();
			if (!account.getMail().isEmpty()) {
				boolean hasUidAccount = false;
				for (String m : account.getMail()) {
					if (!hasUidAccount && m.split("@")[0].equals(account.getUid())) {
						hasUidAccount = true;
					}
					mail.add(m);
				}
				if (!hasUidAccount) {
					mail.add(account.getUid() + "@" + DEFAULT_DOMAIN);
				}
			} else {
				mail.add(account.getUid() + "@" + DEFAULT_DOMAIN);
			}
			return mail;
		}

		private static boolean isMember(final CheckedGroups membershipType, final String department, final String title,
				final Map<String, LdapGroup> ldapGroupsMap, final Set<String> existingMembershipGroupNames) {

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