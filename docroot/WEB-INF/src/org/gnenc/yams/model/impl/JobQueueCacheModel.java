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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.gnenc.yams.model.JobQueue;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing JobQueue in entity cache.
 *
 * @author Drew A. Blessing
 * @see JobQueue
 * @generated
 */
public class JobQueueCacheModel implements CacheModel<JobQueue>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{id=");
		sb.append(id);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", userEmailAddress=");
		sb.append(userEmailAddress);
		sb.append(", jobUserId=");
		sb.append(jobUserId);
		sb.append(", jobUserName=");
		sb.append(jobUserName);
		sb.append(", jobUserEmailAddress=");
		sb.append(jobUserEmailAddress);
		sb.append(", jobDescription=");
		sb.append(jobDescription);
		sb.append(", jobAction=");
		sb.append(jobAction);
		sb.append(", jobDate=");
		sb.append(jobDate);
		sb.append("}");

		return sb.toString();
	}

	public JobQueue toEntityModel() {
		JobQueueImpl jobQueueImpl = new JobQueueImpl();

		jobQueueImpl.setId(id);
		jobQueueImpl.setCompanyId(companyId);
		jobQueueImpl.setUserId(userId);

		if (userName == null) {
			jobQueueImpl.setUserName(StringPool.BLANK);
		}
		else {
			jobQueueImpl.setUserName(userName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			jobQueueImpl.setModifiedDate(null);
		}
		else {
			jobQueueImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (userEmailAddress == null) {
			jobQueueImpl.setUserEmailAddress(StringPool.BLANK);
		}
		else {
			jobQueueImpl.setUserEmailAddress(userEmailAddress);
		}

		jobQueueImpl.setJobUserId(jobUserId);

		if (jobUserName == null) {
			jobQueueImpl.setJobUserName(StringPool.BLANK);
		}
		else {
			jobQueueImpl.setJobUserName(jobUserName);
		}

		if (jobUserEmailAddress == null) {
			jobQueueImpl.setJobUserEmailAddress(StringPool.BLANK);
		}
		else {
			jobQueueImpl.setJobUserEmailAddress(jobUserEmailAddress);
		}

		if (jobDescription == null) {
			jobQueueImpl.setJobDescription(StringPool.BLANK);
		}
		else {
			jobQueueImpl.setJobDescription(jobDescription);
		}

		if (jobAction == null) {
			jobQueueImpl.setJobAction(StringPool.BLANK);
		}
		else {
			jobQueueImpl.setJobAction(jobAction);
		}

		if (jobDate == Long.MIN_VALUE) {
			jobQueueImpl.setJobDate(null);
		}
		else {
			jobQueueImpl.setJobDate(new Date(jobDate));
		}

		jobQueueImpl.resetOriginalValues();

		return jobQueueImpl;
	}

	public long id;
	public long companyId;
	public long userId;
	public String userName;
	public long modifiedDate;
	public String userEmailAddress;
	public long jobUserId;
	public String jobUserName;
	public String jobUserEmailAddress;
	public String jobDescription;
	public String jobAction;
	public long jobDate;
}