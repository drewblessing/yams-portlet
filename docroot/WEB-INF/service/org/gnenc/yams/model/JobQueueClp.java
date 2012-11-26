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

import org.gnenc.yams.service.JobQueueLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Drew A. Blessing
 */
public class JobQueueClp extends BaseModelImpl<JobQueue> implements JobQueue {
	public JobQueueClp() {
	}

	public Class<?> getModelClass() {
		return JobQueue.class;
	}

	public String getModelClassName() {
		return JobQueue.class.getName();
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
		attributes.put("jobUserId", getJobUserId());
		attributes.put("jobUserName", getJobUserName());
		attributes.put("jobUserEmailAddress", getJobUserEmailAddress());
		attributes.put("jobDescription", getJobDescription());
		attributes.put("jobAction", getJobAction());
		attributes.put("jobDate", getJobDate());

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

		Long jobUserId = (Long)attributes.get("jobUserId");

		if (jobUserId != null) {
			setJobUserId(jobUserId);
		}

		String jobUserName = (String)attributes.get("jobUserName");

		if (jobUserName != null) {
			setJobUserName(jobUserName);
		}

		String jobUserEmailAddress = (String)attributes.get(
				"jobUserEmailAddress");

		if (jobUserEmailAddress != null) {
			setJobUserEmailAddress(jobUserEmailAddress);
		}

		String jobDescription = (String)attributes.get("jobDescription");

		if (jobDescription != null) {
			setJobDescription(jobDescription);
		}

		String jobAction = (String)attributes.get("jobAction");

		if (jobAction != null) {
			setJobAction(jobAction);
		}

		Date jobDate = (Date)attributes.get("jobDate");

		if (jobDate != null) {
			setJobDate(jobDate);
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

	public long getJobUserId() {
		return _jobUserId;
	}

	public void setJobUserId(long jobUserId) {
		_jobUserId = jobUserId;
	}

	public String getJobUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getJobUserId(), "uuid", _jobUserUuid);
	}

	public void setJobUserUuid(String jobUserUuid) {
		_jobUserUuid = jobUserUuid;
	}

	public String getJobUserName() {
		return _jobUserName;
	}

	public void setJobUserName(String jobUserName) {
		_jobUserName = jobUserName;
	}

	public String getJobUserEmailAddress() {
		return _jobUserEmailAddress;
	}

	public void setJobUserEmailAddress(String jobUserEmailAddress) {
		_jobUserEmailAddress = jobUserEmailAddress;
	}

	public String getJobDescription() {
		return _jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		_jobDescription = jobDescription;
	}

	public String getJobAction() {
		return _jobAction;
	}

	public void setJobAction(String jobAction) {
		_jobAction = jobAction;
	}

	public Date getJobDate() {
		return _jobDate;
	}

	public void setJobDate(Date jobDate) {
		_jobDate = jobDate;
	}

	public BaseModel<?> getJobQueueRemoteModel() {
		return _jobQueueRemoteModel;
	}

	public void setJobQueueRemoteModel(BaseModel<?> jobQueueRemoteModel) {
		_jobQueueRemoteModel = jobQueueRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			JobQueueLocalServiceUtil.addJobQueue(this);
		}
		else {
			JobQueueLocalServiceUtil.updateJobQueue(this);
		}
	}

	@Override
	public JobQueue toEscapedModel() {
		return (JobQueue)Proxy.newProxyInstance(JobQueue.class.getClassLoader(),
			new Class[] { JobQueue.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		JobQueueClp clone = new JobQueueClp();

		clone.setId(getId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setModifiedDate(getModifiedDate());
		clone.setUserEmailAddress(getUserEmailAddress());
		clone.setJobUserId(getJobUserId());
		clone.setJobUserName(getJobUserName());
		clone.setJobUserEmailAddress(getJobUserEmailAddress());
		clone.setJobDescription(getJobDescription());
		clone.setJobAction(getJobAction());
		clone.setJobDate(getJobDate());

		return clone;
	}

	public int compareTo(JobQueue jobQueue) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(), jobQueue.getModifiedDate());

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

		JobQueueClp jobQueue = null;

		try {
			jobQueue = (JobQueueClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = jobQueue.getPrimaryKey();

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
		StringBundler sb = new StringBundler(25);

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
		sb.append(", jobUserId=");
		sb.append(getJobUserId());
		sb.append(", jobUserName=");
		sb.append(getJobUserName());
		sb.append(", jobUserEmailAddress=");
		sb.append(getJobUserEmailAddress());
		sb.append(", jobDescription=");
		sb.append(getJobDescription());
		sb.append(", jobAction=");
		sb.append(getJobAction());
		sb.append(", jobDate=");
		sb.append(getJobDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("org.gnenc.yams.model.JobQueue");
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
			"<column><column-name>jobUserId</column-name><column-value><![CDATA[");
		sb.append(getJobUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobUserName</column-name><column-value><![CDATA[");
		sb.append(getJobUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobUserEmailAddress</column-name><column-value><![CDATA[");
		sb.append(getJobUserEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobDescription</column-name><column-value><![CDATA[");
		sb.append(getJobDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobAction</column-name><column-value><![CDATA[");
		sb.append(getJobAction());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobDate</column-name><column-value><![CDATA[");
		sb.append(getJobDate());
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
	private long _jobUserId;
	private String _jobUserUuid;
	private String _jobUserName;
	private String _jobUserEmailAddress;
	private String _jobDescription;
	private String _jobAction;
	private Date _jobDate;
	private BaseModel<?> _jobQueueRemoteModel;
}