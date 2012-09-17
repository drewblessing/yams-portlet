package org.gnenc.yams.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.ValidationException;

import org.gnenc.yams.model.EntityGroup;
import org.gnenc.yams.model.EntityGroupMap;
import org.gnenc.yams.model.Group;
import org.gnenc.yams.model.GroupMap;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.operation.group.GetAllGroups;
import org.gnenc.yams.operation.group.GetGroups;
import org.gnenc.yams.service.GroupManagementService;
import org.gnenc.yams.service.internal.ExecutionCallback;
import org.gnenc.yams.service.internal.ExecutionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Jeshurun Daniel
 *
 */
@Service("groupManagementService")
public class GroupManagementServiceImpl implements GroupManagementService {

	@Autowired
	private ExecutionManager executor;

	private static GroupManagementService instance;

	public static GroupManagementService getInstance() {
		if (instance == null) {
			instance = new GroupManagementServiceImpl();
		}
		return instance;
	}

	protected GroupManagementServiceImpl() {
		instance = this;
	}

//	@Override
//	public List<SubSystem> checkGroupExists(final String groupContainer, final String groupName) {
//
//		final List<SubSystem> subsystems = new ArrayList<SubSystem>();
//		executor.execute(CheckGroupExists.class,
//				SubSystem.ALL_SUBSYSTEMS, new ExecutionCallback<CheckGroupExists>() {
//					@Override
//					public  void executeAction(CheckGroupExists operation) {
//						if (operation.checkGroupExists(groupContainer, groupName)) {
//							subsystems.add(operation.getSubsystemType());
//						}
//					}
//				}, true);
//
//		return subsystems;
//	}

//	@Override
//	public EntityGroup createGroup(final EntityGroup group, final List<String> containers)
//			throws ValidationException {
//
//		final List<String> validationErrors = Collections.synchronizedList(new ArrayList<String>());
//
//		executor.execute(CreateGroup.class, SubSystem.ALL_SUBSYSTEMS,
//				new ValidatedExecutionCallback<CreateGroup>() {
//					@Override
//					public void validateAction(CreateGroup operation)
//							throws ValidationException {
//						operation.validateNewGroup(containers, group, validationErrors);
//						if (!validationErrors.isEmpty()) {
//							throw new ValidationException(validationErrors.toArray(new String[validationErrors.size()]));
//						}
//					}
//
//					@Override
//					public void executeAction(CreateGroup operation) {
//						operation.createNewGroup(containers, group);
//					}
//				}, false);
//
//		return group;
//
//	}
//
//	@Override
//	public EntityGroup modifyGroup(final String groupContainer, final EntityGroup group) {
//
//		executor.execute(ModifyGroup.class,
//				SubSystem.ALL_SUBSYSTEMS, new ExecutionCallback<ModifyGroup>() {
//					@Override
//					public  void executeAction(ModifyGroup operation) {
//						operation.modifyGroup(groupContainer, group);
//					}
//				}, true);
//
//		return group;
//	}
//
//	@Override
//	public void removeGroup(final String groupContainer, final EntityGroup group) {
//
//		executor.execute(DeleteGroup.class,
//				SubSystem.ALL_SUBSYSTEMS, new ExecutionCallback<DeleteGroup>() {
//					@Override
//					public  void executeAction(DeleteGroup operation) {
//						operation.deleteGroup(groupContainer, group);
//					}
//				}, true);
//
//	}
//
//	@Override
//	public EntityGroup renameGroup(final String groupContainer, final EntityGroup group, final String newCn)
//			throws ValidationException {
//
//		final List<String> validationErrors = Collections.synchronizedList(new ArrayList<String>());
//
//		executor.execute(RenameGroup.class, SubSystem.ALL_SUBSYSTEMS,
//				new ValidatedExecutionCallback<RenameGroup>() {
//					@Override
//					public void validateAction(RenameGroup operation)
//							throws ValidationException {
//						operation.validateRenameGroup(groupContainer, group, newCn, validationErrors);
//						if (!validationErrors.isEmpty()) {
//							throw new ValidationException(validationErrors.toArray(new String[validationErrors.size()]));
//						}
//					}
//
//					@Override
//					public void executeAction(RenameGroup operation) {
//						operation.renameGroup(groupContainer, group, newCn);
//					}
//				}, false);
//
//		return group;
//	}
//
//	@Override
//	public EntityGroup initializeGroup(final String groupContainer, final EntityGroup group) {
//
//		executor.execute(InitializeGroup.class,
//				SubSystem.ALL_SUBSYSTEMS, new ExecutionCallback<InitializeGroup>() {
//					@Override
//					public  void executeAction(InitializeGroup operation) {
//						operation.initializeGroup(groupContainer, group);
//					}
//				}, true);
//
//		return group;
//	}

	@Override
	public List<EntityGroupMap> getAllGroups(
			final List<SearchFilter> filters, final Operand operand,
			final List<SubSystem> subsystems, boolean like) {
		final Map<String, List<EntityGroup>> results =
				Collections.synchronizedMap(new HashMap<String, List<EntityGroup>>());
		final String searchFilter = SearchFilter.buildFilterString(filters, operand, like);
		
		try {
			executor.execute(GetAllGroups.class,
					subsystems, new ExecutionCallback<GetAllGroups>() {
						@Override
						public void executeAction(GetAllGroups operation) {
							operation.getAllGroups(results, searchFilter);
						}
					}, true);
		} catch (ValidationException e) {
			e.printStackTrace();
		}

		// Sorting
		for (final List<EntityGroup> g : results.values()) {
			Collections.sort(g, EntityGroup.NAME_COMPARATOR_ASC);
		}

		return EntityGroupMap.toGroupMap(results);
	}
	
	@Override
	public List<GroupMap> getGroups(
			final List<SearchFilter> filters, final Operand operand,
			final List<SubSystem> subsystems, boolean like) {
		final Map<String, List<Group>> results =
				Collections.synchronizedMap(new HashMap<String, List<Group>>());
		final String searchFilter = SearchFilter.buildFilterString(filters, operand, like);
		
		try {
			executor.execute(GetGroups.class,
					subsystems, new ExecutionCallback<GetGroups>() {
						@Override
						public void executeAction(GetGroups operation) {
							operation.getGroups(results, searchFilter);
						}
					}, true);
		} catch (ValidationException e) {
			e.printStackTrace();
		}

		// Sorting
//		for (final List<Group> g : results.values()) {
//			Collections.sort(g, EntityGroup.NAME_COMPARATOR_ASC);
//		}

		return GroupMap.toGroupMap(results);
	}

}