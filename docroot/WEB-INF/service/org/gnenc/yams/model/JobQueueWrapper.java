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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link JobQueue}.
 * </p>
 *
 * @author    Drew A. Blessing
 * @see       JobQueue
 * @generated
 */
public class JobQueueWrapper implements JobQueue, ModelWrapper<JobQueue> {
	public JobQueueWrapper(JobQueue jobQueue) {
		_jobQueue = jobQueue;
	}

	public Class<?> getModelClass() {
		return JobQueue.class;
	}

	public String getModelClassName() {
		return JobQueue.class.getName();
	}

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

	/**
	* Returns the primary key of this job queue.
	*
	* @return the primary key of this job queue
	*/
	public long getPrimaryKey() {
		return _jobQueue.getPrimaryKey();
	}

	/**
	* Sets the primary key of this job queue.
	*
	* @param primaryKey the primary key of this job queue
	*/
	public void setPrimaryKey(long primaryKey) {
		_jobQueue.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ID of this job queue.
	*
	* @return the ID of this job queue
	*/
	public long getId() {
		return _jobQueue.getId();
	}

	/**
	* Sets the ID of this job queue.
	*
	* @param id the ID of this job queue
	*/
	public void setId(long id) {
		_jobQueue.setId(id);
	}

	/**
	* Returns the company ID of this job queue.
	*
	* @return the company ID of this job queue
	*/
	public long getCompanyId() {
		return _jobQueue.getCompanyId();
	}

	/**
	* Sets the company ID of this job queue.
	*
	* @param companyId the company ID of this job queue
	*/
	public void setCompanyId(long companyId) {
		_jobQueue.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this job queue.
	*
	* @return the user ID of this job queue
	*/
	public long getUserId() {
		return _jobQueue.getUserId();
	}

	/**
	* Sets the user ID of this job queue.
	*
	* @param userId the user ID of this job queue
	*/
	public void setUserId(long userId) {
		_jobQueue.setUserId(userId);
	}

	/**
	* Returns the user uuid of this job queue.
	*
	* @return the user uuid of this job queue
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jobQueue.getUserUuid();
	}

	/**
	* Sets the user uuid of this job queue.
	*
	* @param userUuid the user uuid of this job queue
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_jobQueue.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this job queue.
	*
	* @return the user name of this job queue
	*/
	public java.lang.String getUserName() {
		return _jobQueue.getUserName();
	}

	/**
	* Sets the user name of this job queue.
	*
	* @param userName the user name of this job queue
	*/
	public void setUserName(java.lang.String userName) {
		_jobQueue.setUserName(userName);
	}

	/**
	* Returns the modified date of this job queue.
	*
	* @return the modified date of this job queue
	*/
	public java.util.Date getModifiedDate() {
		return _jobQueue.getModifiedDate();
	}

	/**
	* Sets the modified date of this job queue.
	*
	* @param modifiedDate the modified date of this job queue
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_jobQueue.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the user email address of this job queue.
	*
	* @return the user email address of this job queue
	*/
	public java.lang.String getUserEmailAddress() {
		return _jobQueue.getUserEmailAddress();
	}

	/**
	* Sets the user email address of this job queue.
	*
	* @param userEmailAddress the user email address of this job queue
	*/
	public void setUserEmailAddress(java.lang.String userEmailAddress) {
		_jobQueue.setUserEmailAddress(userEmailAddress);
	}

	/**
	* Returns the job user ID of this job queue.
	*
	* @return the job user ID of this job queue
	*/
	public long getJobUserId() {
		return _jobQueue.getJobUserId();
	}

	/**
	* Sets the job user ID of this job queue.
	*
	* @param jobUserId the job user ID of this job queue
	*/
	public void setJobUserId(long jobUserId) {
		_jobQueue.setJobUserId(jobUserId);
	}

	/**
	* Returns the job user uuid of this job queue.
	*
	* @return the job user uuid of this job queue
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getJobUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jobQueue.getJobUserUuid();
	}

	/**
	* Sets the job user uuid of this job queue.
	*
	* @param jobUserUuid the job user uuid of this job queue
	*/
	public void setJobUserUuid(java.lang.String jobUserUuid) {
		_jobQueue.setJobUserUuid(jobUserUuid);
	}

	/**
	* Returns the job user name of this job queue.
	*
	* @return the job user name of this job queue
	*/
	public java.lang.String getJobUserName() {
		return _jobQueue.getJobUserName();
	}

	/**
	* Sets the job user name of this job queue.
	*
	* @param jobUserName the job user name of this job queue
	*/
	public void setJobUserName(java.lang.String jobUserName) {
		_jobQueue.setJobUserName(jobUserName);
	}

	/**
	* Returns the job user email address of this job queue.
	*
	* @return the job user email address of this job queue
	*/
	public java.lang.String getJobUserEmailAddress() {
		return _jobQueue.getJobUserEmailAddress();
	}

	/**
	* Sets the job user email address of this job queue.
	*
	* @param jobUserEmailAddress the job user email address of this job queue
	*/
	public void setJobUserEmailAddress(java.lang.String jobUserEmailAddress) {
		_jobQueue.setJobUserEmailAddress(jobUserEmailAddress);
	}

	/**
	* Returns the job description of this job queue.
	*
	* @return the job description of this job queue
	*/
	public java.lang.String getJobDescription() {
		return _jobQueue.getJobDescription();
	}

	/**
	* Sets the job description of this job queue.
	*
	* @param jobDescription the job description of this job queue
	*/
	public void setJobDescription(java.lang.String jobDescription) {
		_jobQueue.setJobDescription(jobDescription);
	}

	/**
	* Returns the job action of this job queue.
	*
	* @return the job action of this job queue
	*/
	public java.lang.String getJobAction() {
		return _jobQueue.getJobAction();
	}

	/**
	* Sets the job action of this job queue.
	*
	* @param jobAction the job action of this job queue
	*/
	public void setJobAction(java.lang.String jobAction) {
		_jobQueue.setJobAction(jobAction);
	}

	/**
	* Returns the job date of this job queue.
	*
	* @return the job date of this job queue
	*/
	public java.util.Date getJobDate() {
		return _jobQueue.getJobDate();
	}

	/**
	* Sets the job date of this job queue.
	*
	* @param jobDate the job date of this job queue
	*/
	public void setJobDate(java.util.Date jobDate) {
		_jobQueue.setJobDate(jobDate);
	}

	public boolean isNew() {
		return _jobQueue.isNew();
	}

	public void setNew(boolean n) {
		_jobQueue.setNew(n);
	}

	public boolean isCachedModel() {
		return _jobQueue.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_jobQueue.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _jobQueue.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _jobQueue.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_jobQueue.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jobQueue.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jobQueue.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new JobQueueWrapper((JobQueue)_jobQueue.clone());
	}

	public int compareTo(org.gnenc.yams.model.JobQueue jobQueue) {
		return _jobQueue.compareTo(jobQueue);
	}

	@Override
	public int hashCode() {
		return _jobQueue.hashCode();
	}

	public com.liferay.portal.model.CacheModel<org.gnenc.yams.model.JobQueue> toCacheModel() {
		return _jobQueue.toCacheModel();
	}

	public org.gnenc.yams.model.JobQueue toEscapedModel() {
		return new JobQueueWrapper(_jobQueue.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _jobQueue.toString();
	}

	public java.lang.String toXmlString() {
		return _jobQueue.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_jobQueue.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public JobQueue getWrappedJobQueue() {
		return _jobQueue;
	}

	public JobQueue getWrappedModel() {
		return _jobQueue;
	}

	public void resetOriginalValues() {
		_jobQueue.resetOriginalValues();
	}

	private JobQueue _jobQueue;
}