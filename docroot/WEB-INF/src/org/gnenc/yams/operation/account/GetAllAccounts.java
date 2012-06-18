package org.gnenc.yams.operation.account;

import java.util.Map;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.operation.Operation;

/**
 *
 * @author Jeshurun Daniel
 *
 */
public interface GetAllAccounts extends Operation {

	public void getAllAccounts(final Map<String, Account> accounts, final String filter);

}