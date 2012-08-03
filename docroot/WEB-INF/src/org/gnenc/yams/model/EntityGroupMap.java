package org.gnenc.yams.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class EntityGroupMap {

	private String groupContainer;

	private List<EntityGroup> entityGroups;

	public String getGroupContainer() {
		return groupContainer;
	}

	public void setGroupContainer(String groupContainer) {
		this.groupContainer = groupContainer;
	}

	public List<EntityGroup> getGroups() {
		return entityGroups == null ? Collections.<EntityGroup>emptyList() : entityGroups;
	}

	public void setGroups(List<EntityGroup> groups) {
		this.entityGroups = groups;
	}

	public static Map<String, List<EntityGroup>> toMap(final List<EntityGroupMap> groupMaps) {
		final Map<String, List<EntityGroup>> groupsMap = new HashMap<String, List<EntityGroup>>();
		if (groupMaps == null) {
			return groupsMap;
		}
		for (final EntityGroupMap gm : groupMaps) {
			groupsMap.put(gm.groupContainer, gm.getGroups());
		}
		return groupsMap;
	}

	public static List<EntityGroupMap> toGroupMap(final Map<String, List<EntityGroup>> groupsMap) {
		final List<EntityGroupMap> groupMaps = new ArrayList<EntityGroupMap>();
		for (final String container : groupsMap.keySet()) {
			EntityGroupMap gm = new EntityGroupMap();
			gm.setGroupContainer(container);
			gm.setGroups(groupsMap.get(container));
			groupMaps.add(gm);
		}

		return groupMaps;
	}
}