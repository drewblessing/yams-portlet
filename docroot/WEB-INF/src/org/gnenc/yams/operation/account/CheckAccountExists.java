package org.gnenc.yams.operation.account;

import org.gnenc.yams.operation.Operation;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
public interface CheckAccountExists extends Operation {
	
	public boolean checkAccountExists(final String accountId);

}
