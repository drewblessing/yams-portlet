package org.gnenc.yams.subsystem.ldap.model;

import java.util.List;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute.Type;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

/**
 * LDAP Account model based on the original LDAP Account model
 * created by Jeshurun Daniel. Class represents commonly used
 * attributes that can be found in the inetorgperson and
 * posixaccount schema.
 *
 * @author Drew A. Blessing
 *
 */
@Entry(objectClasses={"top","inetorgperson","esuccnek12account","esuccnek12student","person","posixAccount","shadowaccount"})
final public class LdapAccountEsuccStudent {

	@Id
	private Name dn;

	@Attribute(type=Type.STRING)
	private List<String> cn;

	@Attribute(type=Type.STRING)
	private String departmentNumber;

	@Attribute(type=Type.STRING)
	private String description;

	@Attribute(type=Type.STRING)
	private String displayName;

	@Attribute(type=Type.STRING)
	private Integer employeeNumber;

	@Attribute(type=Type.STRING)
	private String employeeType;

	@Attribute(type=Type.STRING)
	private String gidNumber;

	@Attribute(type=Type.STRING)
	private String givenName;

	@Attribute(type=Type.STRING)
	private String homeDirectory;

	@Attribute(type=Type.STRING)
	private List<String> homePhone;

	@Attribute(type=Type.STRING)
	private List<String> homePostalAddress;

	@Attribute(type=Type.STRING)
	private String initials;

	@Attribute(type=Type.BINARY)
	private byte[] jpegPhoto;

	@Attribute(type=Type.STRING, name="l")
	private List<String> localityName;

	@Attribute(type=Type.STRING)
	private String loginShell;

	@Attribute(type=Type.STRING)
	private List<String> mail;

	@Attribute(type=Type.STRING, name="memberOf")
	private List<String> memberships;

	@Attribute(type=Type.STRING)
	private List<String> mobile;

	@Attribute(type=Type.STRING)
	private List<String> objectClass;

	@Attribute(type=Type.STRING, name="o")
	private String organizationName;

	@Attribute(type=Type.STRING, name="ou")
	private String organizationalUnitName;

	@Attribute(type=Type.BINARY)
	private byte[] photo;

	@Attribute(type=Type.STRING)
	private List<String> postalAddress;

	@Attribute(type=Type.STRING)
	private String postalCode;

	@Attribute(type=Type.STRING)
	private List<String> preferredLanguage;

	@Attribute(type=Type.STRING)
	private String roomNumber;

	@Attribute(type=Type.STRING)
	private List<String> securityAnswer;

	@Attribute(type=Type.STRING)
	private List<String> securityQuestion;

	@Attribute(type=Type.STRING)
	private String sn;

	@Attribute(type=Type.STRING, name="st")
	private String state;

	@Attribute(type=Type.STRING)
	private String status;

	@Attribute(type=Type.STRING)
	private List<String> street;

	@Attribute(type=Type.STRING)
	private List<String> telephoneNumber;

	@Attribute(type=Type.STRING)
	private String title;

	@Attribute(type=Type.STRING)
	private String uid;

	@Attribute(type=Type.STRING)
	private String uidNumber;

	@Attribute(type=Type.BINARY)
	private byte[] userPassword;
	
	@Attribute(type=Type.STRING, name="esucc-entity")
	private String esuccEntity;
	
	@Attribute(type=Type.STRING, name="esucc-provider")
	private String esuccProvider;
	
	@Attribute(type=Type.STRING, name="esucc-system")
	private String esuccSystem;
	
	@Attribute(type=Type.STRING, name="esucc-mailPrimaryLocalPart")
	private String esuccMailPrimaryLocalPart;
	
	@Attribute(type=Type.STRING, name="esucc-mailPrimaryDomain")
	private String esuccMailPrimaryDomain;
	
	@Attribute(type=Type.STRING, name="esucc-mailForward")
	private String esuccMailForward;

	@Attribute(type=Type.STRING, name="esucc-accountEnabled")
	private String esuccAccountEnabled;
	
	@Attribute(type=Type.STRING, name="esucc-accountDisabledReason")
	private String esuccAccountDisabledReason;
	
	@Attribute(type=Type.STRING, name="esucc-uidNumber")
	private String esuccUidNumber;
	
	@Attribute(type=Type.STRING, name="esucc-screenname")
	private String esuccScreenName;

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	public List<String> getCn() {
		return cn;
	}

	public void setCn(List<String> cn) {
		this.cn = cn;
	}

	public String getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	
	public String getGidNumber() {
		return gidNumber;
	}

	public void setGidNumber(String gidNumber) {
		this.gidNumber = gidNumber;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public String getHomeDirectory() {
		return homeDirectory;
	}

	public void setHomeDirectory(String homeDirectory) {
		this.homeDirectory = homeDirectory;
	}

	public List<String> getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(List<String> homePhone) {
		this.homePhone = homePhone;
	}

	public List<String> getHomePostalAddress() {
		return homePostalAddress;
	}

	public void setHomePostalAddress(List<String> homePostalAddress) {
		this.homePostalAddress = homePostalAddress;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public byte[] getJpegPhoto() {
		return jpegPhoto;
	}

	public void setJpegPhoto(byte[] jpegPhoto) {
		this.jpegPhoto = jpegPhoto;
	}

	public List<String> getLocalityName() {
		return localityName;
	}

	public void setLocalityName(List<String> localityName) {
		this.localityName = localityName;
	}

	public List<String> getMail() {
		return mail;
	}

	public void setMail(List<String> mail) {
		this.mail = mail;
	}

	public List<String> getMemberships() {
		return memberships;
	}

	public List<String> getMobile() {
		return mobile;
	}

	public void setMobile(List<String> mobile) {
		this.mobile = mobile;
	}

	public List<String> getObjectClass() {
		return objectClass;
	}

	public void setObjectClass(List<String> objectClass) {
		this.objectClass = objectClass;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationalUnitName() {
		return organizationalUnitName;
	}

	public void setOrganizationalUnitName(String organizationalUnitName) {
		this.organizationalUnitName = organizationalUnitName;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public List<String> getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(List<String> postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<String> getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(List<String> preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public List<String> getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(List<String> securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public List<String> getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(List<String> securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getStreet() {
		return street;
	}

	public void setStreet(List<String> street) {
		this.street = street;
	}

	public List<String> getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(List<String> telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUidNumber() {
		return uidNumber;
	}

	public void setUidNumber(String uidNumber) {
		this.uidNumber = uidNumber;
	}

	public String getUserPassword() {
		return new String(userPassword);
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword.getBytes();
	}
	
	public String getEsuccAccountEnabled() {
		return esuccAccountEnabled;
	}
	
	public void setEsuccAccountEnabled(String esuccAccountEnabled) {
		this.esuccAccountEnabled = esuccAccountEnabled;
	}
	
	public String getEsuccAccountDisabledReason() {
		return esuccAccountDisabledReason;
	}
	
	public void setEsuccAccountDisabledReason(String esuccAccountDisabledReason) {
		this.esuccAccountDisabledReason = esuccAccountDisabledReason;
	}

	public void setEsuccEntity(String esuccEntity) {
		this.esuccEntity = esuccEntity;
	}
	
	public String getEsuccEntity() {
		return esuccEntity;
	}
	
	public void setEsuccProvider(String esuccProvider) {
		this.esuccProvider = esuccProvider;
	}
	
	public String getEsuccProvider() {
		return esuccProvider;
	}
	
	public void setEsuccSystem(String esuccSystem) {
		this.esuccSystem = esuccSystem;
	}
	
	public String getEsuccSystem() {
		return esuccSystem;
	}
	
	public void setEsuccMailPrimaryLocalPart(String esuccMailPrimaryLocalPart) {
		this.esuccMailPrimaryLocalPart = esuccMailPrimaryLocalPart;
	}
	
	public String getEsuccMailPrimaryLocalPart() {
		return esuccMailPrimaryLocalPart;
	}
	
	public void setEsuccMailPrimaryDomain(String esuccMailPrimaryDomain) {
		this.esuccMailPrimaryDomain = esuccMailPrimaryDomain;
	}
	
	public String getEsuccMailPrimaryDomain() {
		return esuccMailPrimaryDomain;
	}
	
	public void setEsuccMailForward(String esuccMailForward) {
		this.esuccMailForward = esuccMailForward;
	}
	
	public String getEsuccMailForward() {
		return esuccMailForward;
	}
	
	public void setEsuccUidNumber(String esuccUidNumber) {
		this.esuccUidNumber = esuccUidNumber;
	}
	
	public String getEsuccUidNumber() {
		return esuccUidNumber;
	}
	
	public void setEsuccScreenName(String esuccScreenName) {
		this.esuccScreenName = esuccScreenName;
	}
	
	public String getEsuccScreenName() {
		return esuccScreenName;
	}

}