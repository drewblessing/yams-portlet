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
public class ActionLogSoap implements Serializable {
	public static ActionLogSoap toSoapModel(ActionLog model) {
		ActionLogSoap soapModel = new ActionLogSoap();

		soapModel.setId(model.getId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setUserEmailAddress(model.getUserEmailAddress());
		soapModel.setModifiedUserId(model.getModifiedUserId());
		soapModel.setModifiedUserName(model.getModifiedUserName());
		soapModel.setModifiedUserEmailAddress(model.getModifiedUserEmailAddress());
		soapModel.setModifiedDescription(model.getModifiedDescription());
		soapModel.setModifiedFqgn(model.getModifiedFqgn());

		return soapModel;
	}

	public static ActionLogSoap[] toSoapModels(ActionLog[] models) {
		ActionLogSoap[] soapModels = new ActionLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ActionLogSoap[][] toSoapModels(ActionLog[][] models) {
		ActionLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ActionLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ActionLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ActionLogSoap[] toSoapModels(List<ActionLog> models) {
		List<ActionLogSoap> soapModels = new ArrayList<ActionLogSoap>(models.size());

		for (ActionLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ActionLogSoap[soapModels.size()]);
	}

	public ActionLogSoap() {
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getUserEmailAddress() {
		return _userEmailAddress;
	}

	public void setUserEmailAddress(String userEmailAddress) {
		_userEmailAddress = userEmailAddress;
	}

	public long getModifiedUserId() {
		return _modifiedUserId;
	}

	public void setModifiedUserId(long modifiedUserId) {
		_modifiedUserId = modifiedUserId;
	}

	public String getModifiedUserName() {
		return _modifiedUserName;
	}

	public void setModifiedUserName(String modifiedUserName) {
		_modifiedUserName = modifiedUserName;
	}

	public String getModifiedUserEmailAddress() {
		return _modifiedUserEmailAddress;
	}

	public void setModifiedUserEmailAddress(String modifiedUserEmailAddress) {
		_modifiedUserEmailAddress = modifiedUserEmailAddress;
	}

	public String getModifiedDescription() {
		return _modifiedDescription;
	}

	public void setModifiedDescription(String modifiedDescription) {
		_modifiedDescription = modifiedDescription;
	}

	public String getModifiedFqgn() {
		return _modifiedFqgn;
	}

	public void setModifiedFqgn(String modifiedFqgn) {
		_modifiedFqgn = modifiedFqgn;
	}

	private long _id;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _modifiedDate;
	private String _userEmailAddress;
	private long _modifiedUserId;
	private String _modifiedUserName;
	private String _modifiedUserEmailAddress;
	private String _modifiedDescription;
	private String _modifiedFqgn;
}