package org.gnenc.yams.subsystem.ldap.model;

import java.util.List;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute.Type;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

/**
 * LDAP EntityGroup model based on the original LDAP EntityGroup model
 * created by Jeshurun Daniel. Class represents commonly used
 * attributes that can be found in the inetorgperson schema.
 *
 * @author Drew A. Blessing
 *
 */
@Entry(objectClasses={"top","organization","esuccsystem"})
final public class LdapSystem {

	@Id
	private Name dn;

	@Attribute(type=Type.STRING)
	private List<String> objectClass;

	@Attribute(type=Type.STRING, name="o")
	private String organization;
	
	@Attribute(type=Type.STRING, name="esucc-system")
	private String esuccSystem;
	
	@Attribute(type=Type.STRING, name="esucc-systemPadding")
	private String esuccSystemPadding;
	
	@Attribute(type=Type.STRING, name="esucc-systemUIDNext")
	private String esuccSystemUIDNext;

	/**
	 * @return the dn
	 */
	public Name getDn() {
		return dn;
	}

	/**
	 * @param dn the dn to set
	 */
	public void setDn(Name dn) {
		this.dn = dn;
	}

	/**
	 * @return the objectClass
	 */
	public List<String> getObjectClass() {
		return objectClass;
	}

	/**
	 * @param objectClass the objectClass to set
	 */
	public void setObjectClass(List<String> objectClass) {
		this.objectClass = objectClass;
	}

	/**
	 * @return the organization
	 */
	public String getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}

	/**
	 * @return the esuccSystemPadding
	 */
	public String getEsuccSystemPadding() {
		return esuccSystemPadding;
	}

	/**
	 * @param esuccSystemPadding the esuccSystemPadding to set
	 */
	public void setEsuccSystemPadding(String esuccSystemPadding) {
		this.esuccSystemPadding = esuccSystemPadding;
	}

	/**
	 * @return the esuccsystemUIDNext
	 */
	public String getEsuccSystemUIDNext() {
		return esuccSystemUIDNext;
	}

	/**
	 * @param esuccsystemUIDNext the esuccsystemUIDNext to set
	 */
	public void setEsuccSystemUIDNext(String esuccSystemUIDNext) {
		this.esuccSystemUIDNext = esuccSystemUIDNext;
	}
	
}