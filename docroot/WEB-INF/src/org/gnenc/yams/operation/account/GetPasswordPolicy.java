package org.gnenc.yams.operation.account;

import org.gnenc.yams.model.PasswordPolicy;
import org.gnenc.yams.operation.Operation;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
public interface GetPasswordPolicy extends Operation {

	public void getPasswordPolicy(String accountId, PasswordPolicy policy);

}
