package org.gnenc.yams.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.ValidationException;

import org.gnenc.yams.model.GNENCSystem;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Filter;
import org.gnenc.yams.model.SubSystem;
import org.gnenc.yams.operation.account.GetGNENCSystem;
import org.gnenc.yams.operation.account.ModifyGNENCSystem;
import org.gnenc.yams.service.SystemManagementService;
import org.gnenc.yams.service.internal.ExecutionCallback;
import org.gnenc.yams.service.internal.ExecutionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Based on the original AccountManagementService created by Jeshurun Daniel.
 *
 * @author Drew A. Blessing
 *
 */
@Service("gnencSystemManagementService")
public class SystemManagementServiceImpl implements SystemManagementService {

	@Autowired
	private ExecutionManager executor;

	private static SystemManagementService instance;

//	@Autowired
//	private PasswordManager passwordManager;

//	@Autowired
//	private MessageService messages;

	public static SystemManagementService getInstance() {
		if (instance == null) {
			instance = new SystemManagementServiceImpl();
		}
		return instance;
	}

	protected SystemManagementServiceImpl() {
		instance = this;
	}
	
	@Override
	public List<GNENCSystem> getGNENCSystem() {
		final Map<String, GNENCSystem> systems =
				Collections.synchronizedMap(new HashMap<String, GNENCSystem>());
		List<SubSystem> subsystems = new ArrayList<SubSystem>();
		
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(
				Filter.o, "GNENC" , false));
		
		final String searchFilter = SearchFilter.buildFilterString(filters, null, false);
		
		System.out.println("GNENC System search: " + searchFilter);
		try {
			executor.execute(
					GetGNENCSystem.class, SubSystem.ALL_SUBSYSTEMS,
					new ExecutionCallback<GetGNENCSystem>() {
						@Override
						public void executeAction(GetGNENCSystem operation) {
							operation.getGNENCSystem(systems, searchFilter);
						}
					}, true);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		
		System.out.println("Yep!: " + systems.values().size());

		final List<GNENCSystem> results = new ArrayList<GNENCSystem>(systems.values());

		return results;
	}
	
	@Override
	public GNENCSystem modifyGNENCSystem(final GNENCSystem system) {
		List<SubSystem> subsystems = new ArrayList<SubSystem>();
		
//		List<SearchFilter> filters = new ArrayList<SearchFilter>();
//		filters.add(new SearchFilter(
//				Filter.o, "GNENC" , false));
		
//		final String searchFilter = SearchFilter.buildFilterString(filters, null, false);
		
//		System.out.println("GNENC System search: " + searchFilter);
		try {
			executor.execute(
					ModifyGNENCSystem.class, SubSystem.ALL_SUBSYSTEMS,
					new ExecutionCallback<ModifyGNENCSystem>() {
						@Override
						public void executeAction(ModifyGNENCSystem operation) {
							operation.modifyGNENCSystem(system);
						}
					}, true);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		
//		System.out.println("Yep!: " + systems.values().size());

//		final List<GNENCSystem> results = new ArrayList<GNENCSystem>(systems.values());

		return system;
	}

}