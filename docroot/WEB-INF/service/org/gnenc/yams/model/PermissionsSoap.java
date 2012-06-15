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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Drew A. Blessing
 * @generated
 */
public class PermissionsSoap implements Serializable {
	public static PermissionsSoap toSoapModel(Permissions model) {
		PermissionsSoap soapModel = new PermissionsSoap();

		soapModel.setId(model.getId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEmailAddress(model.getEmailAddress());
		soapModel.setFqgn(model.getFqgn());
		soapModel.setPermissions(model.getPermissions());
		soapModel.setPermissionsGrantable(model.getPermissionsGrantable());
		soapModel.setGroupPermission(model.getGroupPermission());

		return soapModel;
	}

	public static PermissionsSoap[] toSoapModels(Permissions[] models) {
		PermissionsSoap[] soapModels = new PermissionsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PermissionsSoap[][] toSoapModels(Permissions[][] models) {
		PermissionsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PermissionsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PermissionsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PermissionsSoap[] toSoapModels(List<Permissions> models) {
		List<PermissionsSoap> soapModels = new ArrayList<PermissionsSoap>(models.size());

		for (Permissions model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PermissionsSoap[soapModels.size()]);
	}

	public PermissionsSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
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

	public boolean getGroupPermission() {
		return _groupPermission;
	}

	public boolean isGroupPermission() {
		return _groupPermission;
	}

	public void setGroupPermission(boolean groupPermission) {
		_groupPermission = groupPermission;
	}

	private long _id;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _emailAddress;
	private String _fqgn;
	private long _permissions;
	private long _permissionsGrantable;
	private boolean _groupPermission;
}