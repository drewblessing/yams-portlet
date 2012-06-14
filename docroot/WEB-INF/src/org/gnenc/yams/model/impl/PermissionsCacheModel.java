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

import org.gnenc.yams.model.Permissions;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Permissions in entity cache.
 *
 * @author Drew A. Blessing
 * @see Permissions
 * @generated
 */
public class PermissionsCacheModel implements CacheModel<Permissions>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{id=");
		sb.append(id);
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
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", fqgn=");
		sb.append(fqgn);
		sb.append(", permissions=");
		sb.append(permissions);
		sb.append(", permissionsGrantable=");
		sb.append(permissionsGrantable);
		sb.append(", groupPermission=");
		sb.append(groupPermission);
		sb.append("}");

		return sb.toString();
	}

	public Permissions toEntityModel() {
		PermissionsImpl permissionsImpl = new PermissionsImpl();

		permissionsImpl.setId(id);
		permissionsImpl.setCompanyId(companyId);
		permissionsImpl.setUserId(userId);

		if (userName == null) {
			permissionsImpl.setUserName(StringPool.BLANK);
		}
		else {
			permissionsImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			permissionsImpl.setCreateDate(null);
		}
		else {
			permissionsImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			permissionsImpl.setModifiedDate(null);
		}
		else {
			permissionsImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (emailAddress == null) {
			permissionsImpl.setEmailAddress(StringPool.BLANK);
		}
		else {
			permissionsImpl.setEmailAddress(emailAddress);
		}

		if (fqgn == null) {
			permissionsImpl.setFqgn(StringPool.BLANK);
		}
		else {
			permissionsImpl.setFqgn(fqgn);
		}

		permissionsImpl.setPermissions(permissions);
		permissionsImpl.setPermissionsGrantable(permissionsGrantable);
		permissionsImpl.setGroupPermission(groupPermission);

		permissionsImpl.resetOriginalValues();

		return permissionsImpl;
	}

	public long id;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String emailAddress;
	public String fqgn;
	public long permissions;
	public long permissionsGrantable;
	public boolean groupPermission;
}