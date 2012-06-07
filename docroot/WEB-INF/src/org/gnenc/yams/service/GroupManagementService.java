package org.gnenc.yams.service;

import java.util.List;

import org.gnenc.yams.model.GroupMap;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.model.SearchFilter.Operand;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
public interface GroupManagementService {
	
	/**
	 * Checks if a group with the given name exists under any subsystem.
	 * 
	 * @param groupName The name of the group to check for existence.
	 * @return A list of subsystems where this group already exists or an empty
	 * list if no groups with the given name exist.
	*/
//	public List<SubSystem> checkGroupExists(String groupContainer, String groupName);
	
	/**
	 * Creates a group under the selected container for each subsystem.
	 * 
	 * @param group
	 * @param container
	 * @return
	 * @throws ValidationException
	 */
	
//	public Group createGroup(Group group, List<String> containers)
//			throws ValidationException;
	

	/**
	 * Allows modifying a group's aliases, members and description.
	 * @return the modified group
	 */
//	public Group modifyGroup(String groupContainer, Group group);
//	
//	
//	public void removeGroup(String groupContainer, Group group);
//	
//	public Group renameGroup(String groupContainer, Group group, String newCn) 
//			throws ValidationException;
	
	/**
	 * Given a group and its container, returns the fully initialized group, i.e. with all its members and aliases.
	 * 
	 * @param groupContainer
	 * @return
	 */
//	public Group initializeGroup(String groupContainer, Group group);
	
	/**
	 * Gets all groups in the given subsystems. The key to the resultant map is:
	 * 	a. The name of the subsystem itself if it contains only one group set.
	 * 	b. Prefixed with the name of the subsystem if the subsystem contains more than one group set.
	 * 
	 * The groups returned are skeleton groups i.e. they are partially initialized and do not contain any members.
	 * To fetch the members of a group, use the initializeGroup call.
	 * @return
	 */
	public List<GroupMap> getAllGroups(
			final List<SearchFilter> filters, final Operand operand, 
			final List<SubSystem> subsystems);

}