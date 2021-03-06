package org.gnenc.yams.operation.account;

import java.util.List;
import java.util.Map;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.EntityGroup;
import org.gnenc.yams.operation.Operation;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
public interface ModifyAccount extends Operation {
	
	public void validateAccount(final Account account, final Map<String, List<EntityGroup>> membershipGroups, final List<String> validationErrors);
	public void modifyAccount(final Account account);

}