package org.gnenc.yams.operation.account;

import java.util.Map;

import org.gnenc.yams.model.Domain;
import org.gnenc.yams.operation.Operation;

/**
 *
 * @author Drew A. Blessing
 *
 */
public interface GetAllDomains extends Operation {

	public void getAllDomains(final Map<String, Domain> domains, final String filter);

}