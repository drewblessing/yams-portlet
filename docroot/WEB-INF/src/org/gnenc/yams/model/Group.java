package org.gnenc.yams.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Based on original Group model written 
 * by Jeshurun Daniel
 * 
 * @author Drew A. Blessing
 *
 */
public class Group {
	public static final Comparator<Group> NAME_COMPARATOR_ASC = 
			new Comparator<Group>() {
		@Override
		public int compare(Group g1, Group g2) {
			int value = g1.getCn().toLowerCase().compareTo(
					g2.getCn().toLowerCase());
			
				return value;
		}
	};
	
	public static final Comparator<Group> NAME_COMPARATOR_DESC = 
			new Comparator<Group>() {
		@Override
		public int compare(Group g1, Group g2) {
			int value = Group.NAME_COMPARATOR_ASC.compare(g1, g2);
			
				return -value;
		}
	};
	

	private String cn;
	
	private String displayName;

	private String description;

	private List<Account> members;
	
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

	public List<Account> getMembers() {
		if(members == null)
			members = new ArrayList<Account>();
		return members;
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
