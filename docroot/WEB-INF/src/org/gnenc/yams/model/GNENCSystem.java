package org.gnenc.yams.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Domain model 
 *
 * @author Drew A. Blessing
 *
 */
final public class GNENCSystem {
	
	private String dn;

	private List<String> objectClass;

	private String organization;
	
	private String esuccSystemPadding;
	
	private String esuccSystemUIDNext;

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
	 * @return the esuccSystemUIDNext
	 */
	public String getEsuccSystemUIDNext() {
		return esuccSystemUIDNext;
	}

	/**
	 * @param esuccSystemUIDNext the esuccSystemUIDNext to set
	 */
	public void setEsuccSystemUIDNext(String esuccSystemUIDNext) {
		this.esuccSystemUIDNext = esuccSystemUIDNext;
	}

	private Set<SubSystem> subsystems;

	/**
	 * @return the dn
	 */
	public String getDn() {
		return dn;
	}

	/**
	 * @param dn the dn to set
	 */
	public void setDn(String dn) {
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

	public Set<SubSystem> getSubsystems() {
		if (subsystems == null)
			 subsystems = Collections.synchronizedSet(new HashSet<SubSystem>());
		return subsystems;
	}

	
}