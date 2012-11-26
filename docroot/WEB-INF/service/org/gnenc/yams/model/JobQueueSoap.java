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
public class JobQueueSoap implements Serializable {
	public static JobQueueSoap toSoapModel(JobQueue model) {
		JobQueueSoap soapModel = new JobQueueSoap();

		soapModel.setId(model.getId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setUserEmailAddress(model.getUserEmailAddress());
		soapModel.setJobUserId(model.getJobUserId());
		soapModel.setJobUserName(model.getJobUserName());
		soapModel.setJobUserEmailAddress(model.getJobUserEmailAddress());
		soapModel.setJobDescription(model.getJobDescription());
		soapModel.setJobAction(model.getJobAction());
		soapModel.setJobDate(model.getJobDate());

		return soapModel;
	}

	public static JobQueueSoap[] toSoapModels(JobQueue[] models) {
		JobQueueSoap[] soapModels = new JobQueueSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static JobQueueSoap[][] toSoapModels(JobQueue[][] models) {
		JobQueueSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new JobQueueSoap[models.length][models[0].length];
		}
		else {
			soapModels = new JobQueueSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static JobQueueSoap[] toSoapModels(List<JobQueue> models) {
		List<JobQueueSoap> soapModels = new ArrayList<JobQueueSoap>(models.size());

		for (JobQueue model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new JobQueueSoap[soapModels.size()]);
	}

	public JobQueueSoap() {
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

	public long getJobUserId() {
		return _jobUserId;
	}

	public void setJobUserId(long jobUserId) {
		_jobUserId = jobUserId;
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

	private long _id;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _modifiedDate;
	private String _userEmailAddress;
	private long _jobUserId;
	private String _jobUserName;
	private String _jobUserEmailAddress;
	private String _jobDescription;
	private String _jobAction;
	private Date _jobDate;
}