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
@Entry(objectClasses={"top","groupOfURLs","esuccGroup","esuccEntityGroup","dgIdentityAux"})
final public class LdapEntityGroup {

	@Id
	private Name dn;

	@Attribute(type=Type.STRING)
	private String cn;

	@Attribute(type=Type.STRING)
	private String description;

	@Attribute(type=Type.STRING, name="member")
	private List<String> members;

	@Attribute(type=Type.STRING)
	private List<String> objectClass;

	@Attribute(type=Type.STRING, name="ou")
	private String organizationalUnit;

	@Attribute(type=Type.STRING, name="o")
	private String organization;

	@Attribute(type=Type.STRING)
	private String owner;
	
	@Attribute(type=Type.STRING)
	private String seeAlso;
	
	@Attribute(type=Type.STRING, name="esucc-entity")
	private String esuccEntity;
	
	@Attribute(type=Type.STRING, name="esucc-groupType")
	private String esuccGroupType;
	
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
	 * @return the cn
	 */
	public String getCn() {
		return cn;
	}

	/**
	 * @param cn the cn to set
	 */
	public void setCn(String cn) {
		this.cn = cn;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the members
	 */
	public List<String> getMembers() {
		return members;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(List<String> members) {
		this.members = members;
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
	 * @return the organizationalUnit
	 */
	public String getOrganizationalUnit() {
		return organizationalUnit;
	}

	/**
	 * @param organizationalUnit the organizationalUnit to set
	 */
	public void setOrganizationalUnit(String organizationalUnit) {
		this.organizationalUnit = organizationalUnit;
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
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSeeAlso() {
		return seeAlso;
	}
	
	public void setSeeAlso(String seeAlso) {
		this.seeAlso = seeAlso;
	}
	
	/**
	 * @return the esucc entity
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
	 * @return the esucc group type
	 */
	public String getEsuccGroupType() {
		return esuccGroupType;
	}

	/**
	 * @param esuccEntity the esuccEntity to set
	 */
	public void setGroupType(String esuccGroupType) {
		this.esuccGroupType = esuccGroupType;
	}
	
	/**
	 * @return the esucc entity
	 */
	public String getEsuccProvider() {
		return esuccProvider;
	}

	/**
	 * @param esuccEntity the esuccEntity to set
	 */
	public void setEsuccProvider(String esuccProvider) {
		this.esuccProvider = esuccProvider;
	}
	
	/**
	 * @return the esucc entity
	 */
	public String getEsuccSystem() {
		return esuccSystem;
	}

	/**
	 * @param esuccEntity the esuccEntity to set
	 */
	public void setEsuccSystem(String esuccSystem) {
		this.esuccSystem = esuccSystem;
	}
	
	

}