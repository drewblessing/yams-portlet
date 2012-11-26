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

package org.gnenc.yams.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.gnenc.yams.model.JobQueue;
import org.gnenc.yams.service.base.JobQueueLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the job queue local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.gnenc.yams.service.JobQueueLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Drew A. Blessing
 * @see org.gnenc.yams.service.base.JobQueueLocalServiceBaseImpl
 * @see org.gnenc.yams.service.JobQueueLocalServiceUtil
 */
public class JobQueueLocalServiceImpl extends JobQueueLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.gnenc.yams.service.JobQueueLocalServiceUtil} to access the job queue local service.
	 */
	public JobQueue addJob(long callingUserId, String email, String fullName, 
			String jobDescription, String jobAction, Date jobDate) throws SystemException {
		
		User user = UserLocalServiceUtil.fetchUser(callingUserId);
//		User modifiedUser = UserLocalServiceUtil.fetchUser(modifiedUserId);
		
		Date now = new Date();
		
		long jobId = counterLocalService.increment();
		
		JobQueue job = jobQueuePersistence.create(jobId);
		
		job.setCompanyId(user.getCompanyId());
		job.setUserId(callingUserId);
		job.setUserName(user.getFullName());
		job.setUserEmailAddress(user.getEmailAddress());
		job.setModifiedDate(now);
		job.setJobUserId(1234);
		job.setJobUserName(fullName);
		job.setJobUserEmailAddress(email);
		job.setJobDescription(jobDescription);
		job.setJobAction(jobAction);
		job.setJobDate(getDateAtMidnight(jobDate));

		jobQueuePersistence.update(job, false);
		
		return job;
	}
	
	public List<JobQueue> getByJobDateAndJobAction(Date date, String jobAction) 
			throws SystemException {		
		return jobQueuePersistence.findByJobDateAndJobAction(getDateAtMidnight(date), jobAction);
	}
	
	public List<JobQueue> getByEmailAddressAndJobAction(String email, String jobAction) 
			throws SystemException {
		return jobQueuePersistence.findByJobUserEmailAddressAndJobAction(email, jobAction);
	}
	
	public void deleteJobByEmailAddressAndJobAction(String email, String jobAction) 
			throws SystemException {
		jobQueuePersistence.removeByJobUserEmailAddressAndJobAction(email, jobAction);
	}
	
	/*
	 * Trickery to make sure that we always reference the date at midnight. 
	 * 
	 * Hint: Medium format doesn't include time. Format it that way, 
	 * then parse it back into a full date object.
	 */
	private Date getDateAtMidnight(Date date) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String todayAtMidnight = df.format(date);
		try {
			date = df.parse(todayAtMidnight);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
			// It just won't happen.
		}
		return date;
	}
}