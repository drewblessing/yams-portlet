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
final public class Domain {
	
	public static final Comparator<Domain> ORGANIZATION_COMPARATOR_ASC =
			new Comparator<Domain>() {
		public int compare(Domain a1, Domain a2) {
			int value = a1.getOrganization().toLowerCase().compareTo(
					a2.getOrganization().toLowerCase());

				return value;
		}
	};

	public static final Comparator<Domain> ORGANIZATION_COMPARATOR_DESC =
			new Comparator<Domain>() {
		public int compare(Domain a1, Domain a2) {
			int value = a1.getOrganization().toLowerCase().compareTo(
					a2.getOrganization().toLowerCase());

				return -value;
		}
	};

	private String dn;

	private List<String> objectClass;

	private String organization;
	
	private String esuccEntity;
	
	private String esuccMailProvider;
	
	private String esuccProvider;
	
	private String esuccSystem;

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

	public Set<SubSystem> getSubsystems() {
		if (subsystems == null)
			 subsystems = Collections.synchronizedSet(new HashSet<SubSystem>());
		return subsystems;
	}

	
}