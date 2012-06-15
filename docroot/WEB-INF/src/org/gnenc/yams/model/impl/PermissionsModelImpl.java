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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import org.gnenc.yams.model.Permissions;
import org.gnenc.yams.model.PermissionsModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;

/**
 * The base model implementation for the Permissions service. Represents a row in the &quot;yams_Permissions&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link org.gnenc.yams.model.PermissionsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PermissionsImpl}.
 * </p>
 *
 * @author Drew A. Blessing
 * @see PermissionsImpl
 * @see org.gnenc.yams.model.Permissions
 * @see org.gnenc.yams.model.PermissionsModel
 * @generated
 */
public class PermissionsModelImpl extends BaseModelImpl<Permissions>
	implements PermissionsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a permissions model instance should use the {@link org.gnenc.yams.model.Permissions} interface instead.
	 */
	public static final String TABLE_NAME = "yams_Permissions";
	public static final Object[][] TABLE_COLUMNS = {
			{ "id_", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "emailAddress", Types.VARCHAR },
			{ "fqgn", Types.VARCHAR },
			{ "permissions", Types.BIGINT },
			{ "permissionsGrantable", Types.BIGINT },
			{ "groupPermission", Types.BOOLEAN }
		};
	public static final String TABLE_SQL_CREATE = "create table yams_Permissions (id_ LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,emailAddress VARCHAR(75) null,fqgn VARCHAR(75) null,permissions LONG,permissionsGrantable LONG,groupPermission BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table yams_Permissions";
	public static final String ORDER_BY_JPQL = " ORDER BY permissions.emailAddress ASC";
	public static final String ORDER_BY_SQL = " ORDER BY yams_Permissions.emailAddress ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.org.gnenc.yams.model.Permissions"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.org.gnenc.yams.model.Permissions"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.gnenc.yams.model.Permissions"),
			true);
	public static long EMAILADDRESS_COLUMN_BITMASK = 1L;
	public static long FQGN_COLUMN_BITMASK = 2L;
	public static long GROUPPERMISSION_COLUMN_BITMASK = 4L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.org.gnenc.yams.model.Permissions"));

	public PermissionsModelImpl() {
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

	public Class<?> getModelClass() {
		return Permissions.class;
	}

	public String getModelClassName() {
		return Permissions.class.getName();
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
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
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
		if (_emailAddress == null) {
			return StringPool.BLANK;
		}
		else {
			return _emailAddress;
		}
	}

	public void setEmailAddress(String emailAddress) {
		_columnBitmask = -1L;

		if (_originalEmailAddress == null) {
			_originalEmailAddress = _emailAddress;
		}

		_emailAddress = emailAddress;
	}

	public String getOriginalEmailAddress() {
		return GetterUtil.getString(_originalEmailAddress);
	}

	public String getFqgn() {
		if (_fqgn == null) {
			return StringPool.BLANK;
		}
		else {
			return _fqgn;
		}
	}

	public void setFqgn(String fqgn) {
		_columnBitmask |= FQGN_COLUMN_BITMASK;

		if (_originalFqgn == null) {
			_originalFqgn = _fqgn;
		}

		_fqgn = fqgn;
	}

	public String getOriginalFqgn() {
		return GetterUtil.getString(_originalFqgn);
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

	public boolean getGroupPermission() {
		return _groupPermission;
	}

	public boolean isGroupPermission() {
		return _groupPermission;
	}

	public void setGroupPermission(boolean groupPermission) {
		_columnBitmask |= GROUPPERMISSION_COLUMN_BITMASK;

		if (!_setOriginalGroupPermission) {
			_setOriginalGroupPermission = true;

			_originalGroupPermission = _groupPermission;
		}

		_groupPermission = groupPermission;
	}

	public boolean getOriginalGroupPermission() {
		return _originalGroupPermission;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public Permissions toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (Permissions)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					Permissions.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	@Override
	public Object clone() {
		PermissionsImpl permissionsImpl = new PermissionsImpl();

		permissionsImpl.setId(getId());
		permissionsImpl.setCompanyId(getCompanyId());
		permissionsImpl.setUserId(getUserId());
		permissionsImpl.setUserName(getUserName());
		permissionsImpl.setCreateDate(getCreateDate());
		permissionsImpl.setModifiedDate(getModifiedDate());
		permissionsImpl.setEmailAddress(getEmailAddress());
		permissionsImpl.setFqgn(getFqgn());
		permissionsImpl.setPermissions(getPermissions());
		permissionsImpl.setPermissionsGrantable(getPermissionsGrantable());
		permissionsImpl.setGroupPermission(getGroupPermission());

		permissionsImpl.resetOriginalValues();

		return permissionsImpl;
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

		Permissions permissions = null;

		try {
			permissions = (Permissions)obj;
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
	public void resetOriginalValues() {
		PermissionsModelImpl permissionsModelImpl = this;

		permissionsModelImpl._originalEmailAddress = permissionsModelImpl._emailAddress;

		permissionsModelImpl._originalFqgn = permissionsModelImpl._fqgn;

		permissionsModelImpl._originalGroupPermission = permissionsModelImpl._groupPermission;

		permissionsModelImpl._setOriginalGroupPermission = false;

		permissionsModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Permissions> toCacheModel() {
		PermissionsCacheModel permissionsCacheModel = new PermissionsCacheModel();

		permissionsCacheModel.id = getId();

		permissionsCacheModel.companyId = getCompanyId();

		permissionsCacheModel.userId = getUserId();

		permissionsCacheModel.userName = getUserName();

		String userName = permissionsCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			permissionsCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			permissionsCacheModel.createDate = createDate.getTime();
		}
		else {
			permissionsCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			permissionsCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			permissionsCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		permissionsCacheModel.emailAddress = getEmailAddress();

		String emailAddress = permissionsCacheModel.emailAddress;

		if ((emailAddress != null) && (emailAddress.length() == 0)) {
			permissionsCacheModel.emailAddress = null;
		}

		permissionsCacheModel.fqgn = getFqgn();

		String fqgn = permissionsCacheModel.fqgn;

		if ((fqgn != null) && (fqgn.length() == 0)) {
			permissionsCacheModel.fqgn = null;
		}

		permissionsCacheModel.permissions = getPermissions();

		permissionsCacheModel.permissionsGrantable = getPermissionsGrantable();

		permissionsCacheModel.groupPermission = getGroupPermission();

		return permissionsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

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
		sb.append(", groupPermission=");
		sb.append(getGroupPermission());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

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
		sb.append(
			"<column><column-name>groupPermission</column-name><column-value><![CDATA[");
		sb.append(getGroupPermission());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Permissions.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			Permissions.class
		};
	private long _id;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _emailAddress;
	private String _originalEmailAddress;
	private String _fqgn;
	private String _originalFqgn;
	private long _permissions;
	private long _permissionsGrantable;
	private boolean _groupPermission;
	private boolean _originalGroupPermission;
	private boolean _setOriginalGroupPermission;
	private transient ExpandoBridge _expandoBridge;
	private long _columnBitmask;
	private Permissions _escapedModelProxy;
}