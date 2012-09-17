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
@Entry(objectClasses={"top","organization","esuccDomain"})
final public class LdapDomain {

	@Id
	private Name dn;

	@Attribute(type=Type.STRING)
	private List<String> objectClass;

	@Attribute(type=Type.STRING, name="o")
	private String organization;
	
	@Attribute(type=Type.STRING, name="esucc-entity")
	private String esuccEntity;
	
	@Attribute(type=Type.STRING, name="esucc-mailprovider")
	private String esuccMailProvider;
	
	@Attribute(type=Type.STRING, name="esucc-provider")
	private String esuccProvider;
	
	@Attribute(type=Type.STRING, name="esucc-system")
	private String esuccSystem;

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
	 * @return the esuccEntity
	 */
	public String getEsuccEntity() {
		return esuccEntity;
	}

	/**
	 * @param esuccEntity the esuccEntity to set
	 */
	public void setEsuccEntity(String esuccEntity) {
		this.esuccEntity = esuccEntity;
	}

	/**
	 * @return the esuccMailProvider
	 */
	public String getEsuccMailProvider() {
		return esuccMailProvider;
	}

	/**
	 * @param esuccMailProvider the esuccMailProvider to set
	 */
	public void setEsuccMailProvider(String esuccMailProvider) {
		this.esuccMailProvider = esuccMailProvider;
	}

	/**
	 * @return the esuccProvider
	 */
	public String getEsuccProvider() {
		return esuccProvider;
	}

	/**
	 * @param esuccProvider the esuccProvider to set
	 */
	public void setEsuccProvider(String esuccProvider) {
		this.esuccProvider = esuccProvider;
	}

	/**
	 * @return the esuccSystem
	 */
	public String getEsuccSystem() {
		return esuccSystem;
	}

	/**
	 * @param esuccSystem the esuccSystem to set
	 */
	public void setEsuccSystem(String esuccSystem) {
		this.esuccSystem = esuccSystem;
	}

	
}