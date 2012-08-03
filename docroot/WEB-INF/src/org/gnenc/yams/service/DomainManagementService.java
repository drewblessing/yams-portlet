package org.gnenc.yams.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.gnenc.yams.model.Account;
import org.gnenc.yams.model.Domain;
import org.gnenc.yams.model.EntityGroupMap;
import org.gnenc.yams.model.SearchFilter;
import org.gnenc.yams.model.SearchFilter.Operand;
import org.gnenc.yams.model.SubSystem;

/**
 *
 * @author Drew A. Blessing
 *
 */
public interface DomainManagementService {
	
	public List<Domain> getAllDomains(List<SearchFilter> filters, Operand operand, 
			List<SubSystem> subsystems, boolean like);


}