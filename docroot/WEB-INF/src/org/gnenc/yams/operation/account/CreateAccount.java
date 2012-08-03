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
public interface CreateAccount extends Operation {
	
	public void validateNewAccount(final Account account, final Map<String, List<EntityGroup>> membershipGroups, final List<String> validationErrors);
	public void createNewAccount(final Account account);

}
