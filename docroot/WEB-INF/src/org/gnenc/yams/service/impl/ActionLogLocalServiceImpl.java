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

import java.util.Date;

import org.gnenc.yams.model.ActionLog;
import org.gnenc.yams.model.Permissions;
import org.gnenc.yams.service.base.ActionLogLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the action log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.gnenc.yams.service.ActionLogLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Drew A. Blessing
 * @see org.gnenc.yams.service.base.ActionLogLocalServiceBaseImpl
 * @see org.gnenc.yams.service.ActionLogLocalServiceUtil
 */
public class ActionLogLocalServiceImpl extends ActionLogLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.gnenc.yams.service.ActionLogLocalServiceUtil} to access the action log local service.
	 */
	public ActionLog addAction(long userId, String email, String fullName, 
			String modifiedFqgn, String modifiedDescription) throws SystemException {
		
		User user = UserLocalServiceUtil.fetchUser(userId);
//		User modifiedUser = UserLocalServiceUtil.fetchUser(modifiedUserId);
		
		Date now = new Date();
		
		long actionLogId = counterLocalService.increment();
		
		ActionLog logEntry = actionLogPersistence.create(actionLogId);
		
		logEntry.setCompanyId(user.getCompanyId());
		logEntry.setUserId(userId);
		logEntry.setUserName(user.getFullName());
		logEntry.setUserEmailAddress(user.getEmailAddress());
		logEntry.setModifiedDate(now);
		logEntry.setModifiedUserId(1234);
		logEntry.setModifiedUserName(fullName);
		logEntry.setModifiedUserEmailAddress(email);
		logEntry.setModifiedFqgn(modifiedFqgn);
		logEntry.setModifiedDescription(modifiedDescription);

		actionLogPersistence.update(logEntry, false);
		
		return logEntry;
	}
}