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
public class PermissionsDefinedSoap implements Serializable {
	public static PermissionsDefinedSoap toSoapModel(PermissionsDefined model) {
		PermissionsDefinedSoap soapModel = new PermissionsDefinedSoap();

		soapModel.setPermissionKey(model.getPermissionKey());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBitLocation(model.getBitLocation());
		soapModel.setDescription(model.getDescription());
		soapModel.setProperName(model.getProperName());

		return soapModel;
	}

	public static PermissionsDefinedSoap[] toSoapModels(
		PermissionsDefined[] models) {
		PermissionsDefinedSoap[] soapModels = new PermissionsDefinedSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PermissionsDefinedSoap[][] toSoapModels(
		PermissionsDefined[][] models) {
		PermissionsDefinedSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PermissionsDefinedSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PermissionsDefinedSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PermissionsDefinedSoap[] toSoapModels(
		List<PermissionsDefined> models) {
		List<PermissionsDefinedSoap> soapModels = new ArrayList<PermissionsDefinedSoap>(models.size());

		for (PermissionsDefined model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PermissionsDefinedSoap[soapModels.size()]);
	}

	public PermissionsDefinedSoap() {
	}

	public String getPrimaryKey() {
		return _permissionKey;
	}

	public void setPrimaryKey(String pk) {
		setPermissionKey(pk);
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

	private String _permissionKey;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _bitLocation;
	private String _description;
	private String _properName;
}