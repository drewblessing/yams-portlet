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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Date;

import org.gnenc.yams.model.PermissionsDefined;
import org.gnenc.yams.service.base.PermissionsDefinedLocalServiceBaseImpl;

/**
 * The implementation of the permissions defined local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.gnenc.yams.service.PermissionsDefinedLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Drew A. Blessing
 * @see org.gnenc.yams.service.base.PermissionsDefinedLocalServiceBaseImpl
 * @see org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil
 */
public class PermissionsDefinedLocalServiceImpl
	extends PermissionsDefinedLocalServiceBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil} to access the permissions defined local service.
	 */

	public PermissionsDefined addPermissionsDefined(long userId, String permissionKey)
			throws SystemException {
		User user = UserLocalServiceUtil.fetchUserById(userId);

		Date now = new Date();

		//Get next bit location.  No need to increment total count (starts at zero anyway)
		int bitLocation = permissionsDefinedPersistence.countAll();

		PermissionsDefined permissionsDefined =
				permissionsDefinedPersistence.create(permissionKey);
		permissionsDefined.setBitLocation(bitLocation);
		permissionsDefined.setCompanyId(user.getCompanyId());
		permissionsDefined.setCreateDate(now);
		permissionsDefined.setModifiedDate(now);
		permissionsDefined.setUserId(userId);
		permissionsDefined.setUserName(user.getFullName());

		return permissionsDefinedPersistence.update(permissionsDefined, false);
	}
}