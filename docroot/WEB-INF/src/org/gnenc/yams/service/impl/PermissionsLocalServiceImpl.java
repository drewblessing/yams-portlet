/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.gnenc.yams.service.impl;

import java.util.Date;
import java.util.List;

import org.gnenc.yams.model.Permissions;
import org.gnenc.yams.service.base.PermissionsLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the permissions local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.gnenc.yams.service.PermissionsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Drew A. Blessing
 * @see org.gnenc.yams.service.base.PermissionsLocalServiceBaseImpl
 * @see org.gnenc.yams.service.PermissionsLocalServiceUtil
 */
public class PermissionsLocalServiceImpl extends PermissionsLocalServiceBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.gnenc.yams.service.PermissionsLocalServiceUtil} to access the permissions local service.
	 * @throws SystemException 
	 */
	public long getPermissionsIdByEmailAddressAndFqgn(
			String emailAddress, String fqgn) throws SystemException {
		List<Permissions> permissions = permissionsPersistence.findByEmailAddressAndFqgn(emailAddress, fqgn);
		if (permissions.size() > 1) {
			throw new SystemException("Too many permissions found for email address and fqgn");
		} else if (permissions.size() == 0) {
			throw new SystemException("No permissions found");
		}
		
		return permissions.get(0).getId();
	}
	
	public List<Permissions> getByEmailAddressAndFqgnAndGroupPermission(
			String email, String fqgn, boolean group) throws SystemException {
		return permissionsPersistence.findByEmailAddressAndFqgnAndGroupPermission(email, fqgn, group);
	}

	public List<Permissions> getByFqgnAndGroupPermission(
			String fqgn, boolean group) throws SystemException {
		return permissionsPersistence.findByFqgnAndGroupPermission(fqgn, group);
	}

	public List<Permissions> getByEmailAddress(String email) throws SystemException {
		return permissionsPersistence.findByEmailAddress(email);
	}
	
	public Permissions addPermissions(
				long userId, String emailAddress, String fqgn, boolean groupPermission,
				long permissions, long permissionsGrantable) 
			throws SystemException, PortalException {
		
		User user = UserLocalServiceUtil.fetchUser(userId);
		
		Date now = new Date();
		
		long permissionsId = counterLocalService.increment();
		
		Permissions permissionsEntry = permissionsPersistence.create(
				permissionsId);
		
		permissionsEntry.setCompanyId(user.getCompanyId());
		permissionsEntry.setUserId(user.getUserId());
		permissionsEntry.setUserName(user.getFullName());
		permissionsEntry.setCreateDate(now);
		permissionsEntry.setModifiedDate(now);
		permissionsEntry.setEmailAddress(emailAddress);
		permissionsEntry.setFqgn(fqgn);
		permissionsEntry.setGroupPermission(groupPermission);
		permissionsEntry.setPermissions(permissions);
		permissionsEntry.setPermissionsGrantable(permissionsGrantable);

		permissionsPersistence.update(permissionsEntry, false);
		
		return permissionsEntry;
	}
	
	public Permissions updatePermissions(
				long permissionsId, long userId, 
				long decimalPermissions, long permissionsGrantable) 
			throws PortalException, SystemException {
		User user = userPersistence.findByPrimaryKey(userId);
		
		Date now = new Date();
		
		Permissions permissionsEntry = permissionsPersistence.fetchByPrimaryKey(permissionsId);
		
		permissionsEntry.setModifiedDate(now);
		permissionsEntry.setCompanyId(user.getCompanyId());
		permissionsEntry.setUserId(user.getUserId());
		permissionsEntry.setUserName(user.getFullName());
		permissionsEntry.setPermissions(decimalPermissions);
		permissionsEntry.setPermissionsGrantable(permissionsGrantable);
		
		permissionsPersistence.update(permissionsEntry, false);
		
		return permissionsEntry;
	}
}