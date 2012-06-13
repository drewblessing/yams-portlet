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

package org.gnenc.yams.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.gnenc.yams.model.PermissionsDefined;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PermissionsDefined in entity cache.
 *
 * @author Drew A. Blessing
 * @see PermissionsDefined
 * @generated
 */
public class PermissionsDefinedCacheModel implements CacheModel<PermissionsDefined>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{permissionKey=");
		sb.append(permissionKey);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", bitLocation=");
		sb.append(bitLocation);
		sb.append("}");

		return sb.toString();
	}

	public PermissionsDefined toEntityModel() {
		PermissionsDefinedImpl permissionsDefinedImpl = new PermissionsDefinedImpl();

		if (permissionKey == null) {
			permissionsDefinedImpl.setPermissionKey(StringPool.BLANK);
		}
		else {
			permissionsDefinedImpl.setPermissionKey(permissionKey);
		}

		permissionsDefinedImpl.setCompanyId(companyId);
		permissionsDefinedImpl.setUserId(userId);

		if (userName == null) {
			permissionsDefinedImpl.setUserName(StringPool.BLANK);
		}
		else {
			permissionsDefinedImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			permissionsDefinedImpl.setCreateDate(null);
		}
		else {
			permissionsDefinedImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			permissionsDefinedImpl.setModifiedDate(null);
		}
		else {
			permissionsDefinedImpl.setModifiedDate(new Date(modifiedDate));
		}

		permissionsDefinedImpl.setBitLocation(bitLocation);

		permissionsDefinedImpl.resetOriginalValues();

		return permissionsDefinedImpl;
	}

	public String permissionKey;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int bitLocation;
}