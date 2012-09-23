package org.gnenc.yams.operation.account;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.operation.Operation;

/**
 * 
 * @author Drew A. Blessing
 *
 */
public interface ModifyEmailForward extends Operation {
	
	public void validateEmailForward(final Account account, final String emailForward);
	public void modifyEmailForward(final Account account, final String emailForward);

}