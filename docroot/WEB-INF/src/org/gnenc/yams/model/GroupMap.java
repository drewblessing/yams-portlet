package org.gnenc.yams.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupMap {

	private String groupContainer;
	
	private List<Group> groups;

	public String getGroupContainer() {
		return groupContainer;
	}

	public void setGroupContainer(String groupContainer) {
		this.groupContainer = groupContainer;
	}

	public List<Group> getGroups() {
		return groups == null ? Collections.<Group>emptyList() : groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	public static Map<String, List<Group>> toMap(final List<GroupMap> groupMaps) {
		final Map<String, List<Group>> groupsMap = new HashMap<String, List<Group>>();
		if(groupMaps == null) {
			return groupsMap;
		}
		for(final GroupMap gm : groupMaps) {
			groupsMap.put(gm.groupContainer, gm.getGroups());
		}
		return groupsMap;
	}
	
	public static List<GroupMap> toGroupMap(final Map<String, List<Group>> groupsMap) {
		final List<GroupMap> groupMaps = new ArrayList<GroupMap>();
		for(final String container : groupsMap.keySet()) {
			GroupMap gm = new GroupMap();
			gm.setGroupContainer(container);
			gm.setGroups(groupsMap.get(container));
			groupMaps.add(gm);
		}
		
		return groupMaps;
	}
}
