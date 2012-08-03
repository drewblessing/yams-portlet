package org.gnenc.yams.operation.account;

import org.gnenc.yams.model.GNENCSystem;
import org.gnenc.yams.operation.Operation;

/**
 * 
 * @author Jeshurun Daniel
 *
 */
public interface ModifyGNENCSystem extends Operation {
	
	public void modifyGNENCSystem(final GNENCSystem system);

}