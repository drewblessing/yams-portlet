package org.gnenc.yams.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.ValidationException;

import org.gnenc.yams.model.Group;
import org.gnenc.yams.model.GroupMap;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.operation.group.GetAllGroups;
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
//	public Group createGroup(final Group group, final List<String> containers)
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
//	public Group modifyGroup(final String groupContainer, final Group group) {
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
//	public void removeGroup(final String groupContainer, final Group group) {
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
//	public Group renameGroup(final String groupContainer, final Group group, final String newCn)
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
//	public Group initializeGroup(final String groupContainer, final Group group) {
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
	public List<GroupMap> getAllGroups(
			final List<SearchFilter> filters, final Operand operand,
			final List<SubSystem> subsystems, boolean like) {
		final Map<String, List<Group>> results =
				Collections.synchronizedMap(new HashMap<String, List<Group>>());
		final String searchFilter = SearchFilter.buildFilterString(filters, operand, like);
		System.out.println(searchFilter);
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
		for (final List<Group> g : results.values()) {
			Collections.sort(g, Group.NAME_COMPARATOR_ASC);
		}

		return GroupMap.toGroupMap(results);
	}

}