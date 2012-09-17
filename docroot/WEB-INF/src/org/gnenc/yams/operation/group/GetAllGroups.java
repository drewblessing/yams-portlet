package org.gnenc.yams.operation.group;

import java.util.List;
import java.util.Map;

import org.gnenc.yams.model.EntityGroup;
import org.gnenc.yams.operation.Operation;

/**
 *
 * @author Jeshurun Daniel
 *
 */
public interface GetAllGroups extends Operation {

	public void getAllGroups(final Map<String, List<EntityGroup>> groupsMap, final String filter);

}