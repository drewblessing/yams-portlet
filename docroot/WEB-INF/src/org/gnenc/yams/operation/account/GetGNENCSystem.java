package org.gnenc.yams.operation.account;

import java.util.Map;

import org.gnenc.yams.model.Domain;
import org.gnenc.yams.model.GNENCSystem;
import org.gnenc.yams.operation.Operation;

/**
 *
 * @author Drew A. Blessing
 *
 */
public interface GetGNENCSystem extends Operation {

	public void getGNENCSystem(final Map<String, GNENCSystem> systems, final String filter);

}