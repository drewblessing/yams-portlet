package org.gnenc.yams.operation.account;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.operation.Operation;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
public interface ResetPassword extends Operation {

	public void changePassword(Account account, String newPassword);
	
}
