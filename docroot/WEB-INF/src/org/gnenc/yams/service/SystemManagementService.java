package org.gnenc.yams.service;

import java.util.List;

import org.gnenc.yams.model.GNENCSystem;

/**
 *
 * @author Drew A. Blessing
 *
 */
public interface SystemManagementService {
	
	public List<GNENCSystem> getGNENCSystem();

	public GNENCSystem modifyGNENCSystem(GNENCSystem system);


}