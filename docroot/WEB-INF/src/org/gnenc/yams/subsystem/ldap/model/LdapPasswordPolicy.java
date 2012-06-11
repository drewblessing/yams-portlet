package org.gnenc.yams.subsystem.ldap.model;

import java.util.List;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Attribute.Type;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
@Entry(objectClasses={"pwdPolicy", "person", "top"})
public final class LdapPasswordPolicy {
	
	@Id
	private Name dn;
	
	@Attribute(type=Type.STRING)
	private List<String> objectClass;
	
	@Attribute(type=Type.STRING)
	private Boolean pwdAllowUserChange;
	
	@Attribute(type=Type.STRING)
	private String pwdAttribute;
	
	@Attribute(type=Type.STRING)
	private Integer pwdCheckQuality;
	
	@Attribute(type=Type.STRING)
	private Integer pwdExpireWarning;
	
	@Attribute(type=Type.STRING)
	private Integer pwdFailureCountInterval;
	
	@Attribute(type=Type.STRING)
	private Integer pwdGraceAuthLimit;
	
	@Attribute(type=Type.STRING)
	private Integer pwdInHistory;
	
	@Attribute(type=Type.STRING)
	private Boolean pwdLockout;
	
	@Attribute(type=Type.STRING)
	private Integer pwdLockDuration;
	
	@Attribute(type=Type.STRING)
	private Integer pwdMaxAge;
	
	@Attribute(type=Type.STRING)
	private Integer pwdMaxFailure; 
	
	@Attribute(type=Type.STRING)
	private Integer pwdMinAge;
	
	@Attribute(type=Type.STRING)
	private Integer pwdMinLength;
	
	@Attribute(type=Type.STRING)
	private Boolean pwdMustChange;
	
	@Attribute(type=Type.STRING)
	private Boolean pwdSafeModify;
	
	@Attribute(type=Type.STRING, name="sn")
	private String description;

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	public List<String> getObjectClass() {
		return objectClass;
	}

	public void setObjectClass(List<String> objectClass) {
		this.objectClass = objectClass;
	}

	public Boolean getPwdAllowUserChange() {
		return pwdAllowUserChange;
	}

	public void setPwdAllowUserChange(Boolean pwdAllowUserChange) {
		this.pwdAllowUserChange = pwdAllowUserChange;
	}

	public String getPwdAttribute() {
		return pwdAttribute;
	}

	public void setPwdAttribute(String pwdAttribute) {
		this.pwdAttribute = pwdAttribute;
	}

	public Integer getPwdCheckQuality() {
		return pwdCheckQuality;
	}

	public void setPwdCheckQuality(Integer pwdCheckQuality) {
		this.pwdCheckQuality = pwdCheckQuality;
	}

	public Integer getPwdExpireWarning() {
		return pwdExpireWarning;
	}

	public void setPwdExpireWarning(Integer pwdExpireWarning) {
		this.pwdExpireWarning = pwdExpireWarning;
	}

	public Integer getPwdFailureCountInterval() {
		return pwdFailureCountInterval;
	}

	public void setPwdFailureCountInterval(Integer pwdFailureCountInterval) {
		this.pwdFailureCountInterval = pwdFailureCountInterval;
	}

	public Integer getPwdGraceAuthLimit() {
		return pwdGraceAuthLimit;
	}

	public void setPwdGraceAuthLimit(Integer pwdGraceAuthLimit) {
		this.pwdGraceAuthLimit = pwdGraceAuthLimit;
	}

	public Integer getPwdInHistory() {
		return pwdInHistory;
	}

	public void setPwdInHistory(Integer pwdInHistory) {
		this.pwdInHistory = pwdInHistory;
	}

	public Boolean getPwdLockout() {
		return pwdLockout;
	}

	public void setPwdLockout(Boolean pwdLockout) {
		this.pwdLockout = pwdLockout;
	}

	public Integer getPwdLockDuration() {
		return pwdLockDuration;
	}

	public void setPwdLockDuration(Integer pwdLockDuration) {
		this.pwdLockDuration = pwdLockDuration;
	}

	public Integer getPwdMaxAge() {
		return pwdMaxAge;
	}

	public void setPwdMaxAge(Integer pwdMaxAge) {
		this.pwdMaxAge = pwdMaxAge;
	}

	public Integer getPwdMaxFailure() {
		return pwdMaxFailure;
	}

	public void setPwdMaxFailure(Integer pwdMaxFailure) {
		this.pwdMaxFailure = pwdMaxFailure;
	}

	public Integer getPwdMinAge() {
		return pwdMinAge;
	}

	public void setPwdMinAge(Integer pwdMinAge) {
		this.pwdMinAge = pwdMinAge;
	}

	public Integer getPwdMinLength() {
		return pwdMinLength;
	}

	public void setPwdMinLength(Integer pwdMinLength) {
		this.pwdMinLength = pwdMinLength;
	}

	public Boolean getPwdMustChange() {
		return pwdMustChange;
	}

	public void setPwdMustChange(Boolean pwdMustChange) {
		this.pwdMustChange = pwdMustChange;
	}

	public Boolean getPwdSafeModify() {
		return pwdSafeModify;
	}

	public void setPwdSafeModify(Boolean pwdSafeModify) {
		this.pwdSafeModify = pwdSafeModify;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
