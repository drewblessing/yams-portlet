package org.gnenc.yams.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Based on original EntityGroup model written
 * by Jeshurun Daniel
 *
 * @author Drew A. Blessing
 *
 */
public class Group {
	public static final Comparator<Group> NAME_COMPARATOR_ASC =
			new Comparator<Group>() {
		public int compare(Group g1, Group g2) {
			int value = g1.getCn().toLowerCase().compareTo(
					g2.getCn().toLowerCase());

				return value;
		}
	};

	public static final Comparator<Group> NAME_COMPARATOR_DESC =
			new Comparator<Group>() {
		public int compare(Group g1, Group g2) {
			int value = Group.NAME_COMPARATOR_ASC.compare(g1, g2);

				return -value;
		}
	};

	private String cn;

	private String displayName;

	private String description;
	
	private String esuccEntity;

	private List<Account> members;
	
	private List<String> seeAlso;

	private Map<String, String> additionalAttributes;

	public Group() {}

	public Group(String cn) {
		this.cn = cn;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
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
	
	public String getEsuccEntity() {
		return esuccEntity;
	}

	public void setEsuccEntity(String esuccEntity) {
		this.esuccEntity = esuccEntity;
	}

	public List<Account> getMembers() {
		if (members == null)
			members = new ArrayList<Account>();
		return members;
	}
	
	public List<String> getSeeAlso() {
		if (seeAlso == null) {
			seeAlso = new ArrayList<String>();
		}
		return seeAlso;
	}

	public String getAttribute(String key) {
		if (additionalAttributes == null)
			return null;
		return additionalAttributes.get(key);
	}

	public void addAttribute(String key, String value) {
		if (additionalAttributes == null)
			additionalAttributes = Collections.synchronizedMap(new HashMap<String, String>());
		additionalAttributes.put(key, value);
	}
}