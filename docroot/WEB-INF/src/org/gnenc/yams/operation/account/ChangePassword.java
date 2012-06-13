package org.gnenc.yams.operation.account;

import java.util.List;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.operation.Operation;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
public interface ChangePassword extends Operation {
	
	public void validateChangePassword(Account account, String oldPassword, String newPassword, List<String> validationErrors);
	public void changePassword(Account account, String newPassword);

}
