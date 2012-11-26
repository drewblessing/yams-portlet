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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import org.gnenc.yams.service.ActionLogLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Drew A. Blessing
 */
public class ActionLogClp extends BaseModelImpl<ActionLog> implements ActionLog {
	public ActionLogClp() {
	}

	public Class<?> getModelClass() {
		return ActionLog.class;
	}

	public String getModelClassName() {
		return ActionLog.class.getName();
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

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("userEmailAddress", getUserEmailAddress());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedUserEmailAddress", getModifiedUserEmailAddress());
		attributes.put("modifiedDescription", getModifiedDescription());
		attributes.put("modifiedFqgn", getModifiedFqgn());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String userEmailAddress = (String)attributes.get("userEmailAddress");

		if (userEmailAddress != null) {
			setUserEmailAddress(userEmailAddress);
		}

		Long modifiedUserId = (Long)attributes.get("modifiedUserId");

		if (modifiedUserId != null) {
			setModifiedUserId(modifiedUserId);
		}

		String modifiedUserName = (String)attributes.get("modifiedUserName");

		if (modifiedUserName != null) {
			setModifiedUserName(modifiedUserName);
		}

		String modifiedUserEmailAddress = (String)attributes.get(
				"modifiedUserEmailAddress");

		if (modifiedUserEmailAddress != null) {
			setModifiedUserEmailAddress(modifiedUserEmailAddress);
		}

		String modifiedDescription = (String)attributes.get(
				"modifiedDescription");

		if (modifiedDescription != null) {
			setModifiedDescription(modifiedDescription);
		}

		String modifiedFqgn = (String)attributes.get("modifiedFqgn");

		if (modifiedFqgn != null) {
			setModifiedFqgn(modifiedFqgn);
		}
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

	public String getModifiedUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getModifiedUserId(), "uuid",
			_modifiedUserUuid);
	}

	public void setModifiedUserUuid(String modifiedUserUuid) {
		_modifiedUserUuid = modifiedUserUuid;
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

	public BaseModel<?> getActionLogRemoteModel() {
		return _actionLogRemoteModel;
	}

	public void setActionLogRemoteModel(BaseModel<?> actionLogRemoteModel) {
		_actionLogRemoteModel = actionLogRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			ActionLogLocalServiceUtil.addActionLog(this);
		}
		else {
			ActionLogLocalServiceUtil.updateActionLog(this);
		}
	}

	@Override
	public ActionLog toEscapedModel() {
		return (ActionLog)Proxy.newProxyInstance(ActionLog.class.getClassLoader(),
			new Class[] { ActionLog.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ActionLogClp clone = new ActionLogClp();

		clone.setId(getId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setModifiedDate(getModifiedDate());
		clone.setUserEmailAddress(getUserEmailAddress());
		clone.setModifiedUserId(getModifiedUserId());
		clone.setModifiedUserName(getModifiedUserName());
		clone.setModifiedUserEmailAddress(getModifiedUserEmailAddress());
		clone.setModifiedDescription(getModifiedDescription());
		clone.setModifiedFqgn(getModifiedFqgn());

		return clone;
	}

	public int compareTo(ActionLog actionLog) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				actionLog.getModifiedDate());

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

		ActionLogClp actionLog = null;

		try {
			actionLog = (ActionLogClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = actionLog.getPrimaryKey();

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
		StringBundler sb = new StringBundler(23);

		sb.append("{id=");
		sb.append(getId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", userEmailAddress=");
		sb.append(getUserEmailAddress());
		sb.append(", modifiedUserId=");
		sb.append(getModifiedUserId());
		sb.append(", modifiedUserName=");
		sb.append(getModifiedUserName());
		sb.append(", modifiedUserEmailAddress=");
		sb.append(getModifiedUserEmailAddress());
		sb.append(", modifiedDescription=");
		sb.append(getModifiedDescription());
		sb.append(", modifiedFqgn=");
		sb.append(getModifiedFqgn());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("org.gnenc.yams.model.ActionLog");
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
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userEmailAddress</column-name><column-value><![CDATA[");
		sb.append(getUserEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedUserId</column-name><column-value><![CDATA[");
		sb.append(getModifiedUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedUserName</column-name><column-value><![CDATA[");
		sb.append(getModifiedUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedUserEmailAddress</column-name><column-value><![CDATA[");
		sb.append(getModifiedUserEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDescription</column-name><column-value><![CDATA[");
		sb.append(getModifiedDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedFqgn</column-name><column-value><![CDATA[");
		sb.append(getModifiedFqgn());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _id;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _modifiedDate;
	private String _userEmailAddress;
	private long _modifiedUserId;
	private String _modifiedUserUuid;
	private String _modifiedUserName;
	private String _modifiedUserEmailAddress;
	private String _modifiedDescription;
	private String _modifiedFqgn;
	private BaseModel<?> _actionLogRemoteModel;
}