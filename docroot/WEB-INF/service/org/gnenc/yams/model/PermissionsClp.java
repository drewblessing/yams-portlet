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

import org.gnenc.yams.service.PermissionsLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Drew A. Blessing
 */
public class PermissionsClp extends BaseModelImpl<Permissions>
	implements Permissions {
	public PermissionsClp() {
	}

	public Class<?> getModelClass() {
		return Permissions.class;
	}

	public String getModelClassName() {
		return Permissions.class.getName();
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long primaryKey) {
		setId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_id);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
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

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public String getFqgn() {
		return _fqgn;
	}

	public void setFqgn(String fqgn) {
		_fqgn = fqgn;
	}

	public long getPermissions() {
		return _permissions;
	}

	public void setPermissions(long permissions) {
		_permissions = permissions;
	}

	public long getPermissionsGrantable() {
		return _permissionsGrantable;
	}

	public void setPermissionsGrantable(long permissionsGrantable) {
		_permissionsGrantable = permissionsGrantable;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			PermissionsLocalServiceUtil.addPermissions(this);
		}
		else {
			PermissionsLocalServiceUtil.updatePermissions(this);
		}
	}

	@Override
	public Permissions toEscapedModel() {
		return (Permissions)Proxy.newProxyInstance(Permissions.class.getClassLoader(),
			new Class[] { Permissions.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		PermissionsClp clone = new PermissionsClp();

		clone.setId(getId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setEmailAddress(getEmailAddress());
		clone.setFqgn(getFqgn());
		clone.setPermissions(getPermissions());
		clone.setPermissionsGrantable(getPermissionsGrantable());

		return clone;
	}

	public int compareTo(Permissions permissions) {
		int value = 0;

		value = getEmailAddress().compareTo(permissions.getEmailAddress());

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

		PermissionsClp permissions = null;

		try {
			permissions = (PermissionsClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = permissions.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{id=");
		sb.append(getId());
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
		sb.append(", emailAddress=");
		sb.append(getEmailAddress());
		sb.append(", fqgn=");
		sb.append(getFqgn());
		sb.append(", permissions=");
		sb.append(getPermissions());
		sb.append(", permissionsGrantable=");
		sb.append(getPermissionsGrantable());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("org.gnenc.yams.model.Permissions");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>id</column-name><column-value><![CDATA[");
		sb.append(getId());
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
			"<column><column-name>emailAddress</column-name><column-value><![CDATA[");
		sb.append(getEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fqgn</column-name><column-value><![CDATA[");
		sb.append(getFqgn());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>permissions</column-name><column-value><![CDATA[");
		sb.append(getPermissions());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>permissionsGrantable</column-name><column-value><![CDATA[");
		sb.append(getPermissionsGrantable());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _id;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _emailAddress;
	private String _fqgn;
	private long _permissions;
	private long _permissionsGrantable;
}