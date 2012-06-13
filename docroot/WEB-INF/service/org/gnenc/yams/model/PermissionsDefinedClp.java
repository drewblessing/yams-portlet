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

package org.gnenc.yams.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Drew A. Blessing
 */
public class PermissionsDefinedClp extends BaseModelImpl<PermissionsDefined>
	implements PermissionsDefined {
	public PermissionsDefinedClp() {
	}

	public Class<?> getModelClass() {
		return PermissionsDefined.class;
	}

	public String getModelClassName() {
		return PermissionsDefined.class.getName();
	}

	public String getPrimaryKey() {
		return _permissionKey;
	}

	public void setPrimaryKey(String primaryKey) {
		setPermissionKey(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return _permissionKey;
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((String)primaryKeyObj);
	}

	public String getPermissionKey() {
		return _permissionKey;
	}

	public void setPermissionKey(String permissionKey) {
		_permissionKey = permissionKey;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getBitLocation() {
		return _bitLocation;
	}

	public void setBitLocation(int bitLocation) {
		_bitLocation = bitLocation;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getProperName() {
		return _properName;
	}

	public void setProperName(String properName) {
		_properName = properName;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			PermissionsDefinedLocalServiceUtil.addPermissionsDefined(this);
		}
		else {
			PermissionsDefinedLocalServiceUtil.updatePermissionsDefined(this);
		}
	}

	@Override
	public PermissionsDefined toEscapedModel() {
		return (PermissionsDefined)Proxy.newProxyInstance(PermissionsDefined.class.getClassLoader(),
			new Class[] { PermissionsDefined.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		PermissionsDefinedClp clone = new PermissionsDefinedClp();

		clone.setPermissionKey(getPermissionKey());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setBitLocation(getBitLocation());
		clone.setDescription(getDescription());
		clone.setProperName(getProperName());

		return clone;
	}

	public int compareTo(PermissionsDefined permissionsDefined) {
		int value = 0;

		if (getBitLocation() < permissionsDefined.getBitLocation()) {
			value = -1;
		}
		else if (getBitLocation() > permissionsDefined.getBitLocation()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		PermissionsDefinedClp permissionsDefined = null;

		try {
			permissionsDefined = (PermissionsDefinedClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		String primaryKey = permissionsDefined.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{permissionKey=");
		sb.append(getPermissionKey());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", bitLocation=");
		sb.append(getBitLocation());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", properName=");
		sb.append(getProperName());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("org.gnenc.yams.model.PermissionsDefined");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>permissionKey</column-name><column-value><![CDATA[");
		sb.append(getPermissionKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>bitLocation</column-name><column-value><![CDATA[");
		sb.append(getBitLocation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>properName</column-name><column-value><![CDATA[");
		sb.append(getProperName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _permissionKey;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _bitLocation;
	private String _description;
	private String _properName;
}