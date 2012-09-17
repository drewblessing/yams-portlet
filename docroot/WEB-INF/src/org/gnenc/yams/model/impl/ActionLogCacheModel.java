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

import org.gnenc.yams.model.ActionLog;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing ActionLog in entity cache.
 *
 * @author Drew A. Blessing
 * @see ActionLog
 * @generated
 */
public class ActionLogCacheModel implements CacheModel<ActionLog>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

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
		sb.append(", modifiedUserId=");
		sb.append(modifiedUserId);
		sb.append(", modifiedUserName=");
		sb.append(modifiedUserName);
		sb.append(", modifiedUserEmailAddress=");
		sb.append(modifiedUserEmailAddress);
		sb.append(", modifiedDescription=");
		sb.append(modifiedDescription);
		sb.append(", modifiedFqgn=");
		sb.append(modifiedFqgn);
		sb.append("}");

		return sb.toString();
	}

	public ActionLog toEntityModel() {
		ActionLogImpl actionLogImpl = new ActionLogImpl();

		actionLogImpl.setId(id);
		actionLogImpl.setCompanyId(companyId);
		actionLogImpl.setUserId(userId);

		if (userName == null) {
			actionLogImpl.setUserName(StringPool.BLANK);
		}
		else {
			actionLogImpl.setUserName(userName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			actionLogImpl.setModifiedDate(null);
		}
		else {
			actionLogImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (userEmailAddress == null) {
			actionLogImpl.setUserEmailAddress(StringPool.BLANK);
		}
		else {
			actionLogImpl.setUserEmailAddress(userEmailAddress);
		}

		actionLogImpl.setModifiedUserId(modifiedUserId);

		if (modifiedUserName == null) {
			actionLogImpl.setModifiedUserName(StringPool.BLANK);
		}
		else {
			actionLogImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedUserEmailAddress == null) {
			actionLogImpl.setModifiedUserEmailAddress(StringPool.BLANK);
		}
		else {
			actionLogImpl.setModifiedUserEmailAddress(modifiedUserEmailAddress);
		}

		if (modifiedDescription == null) {
			actionLogImpl.setModifiedDescription(StringPool.BLANK);
		}
		else {
			actionLogImpl.setModifiedDescription(modifiedDescription);
		}

		if (modifiedFqgn == null) {
			actionLogImpl.setModifiedFqgn(StringPool.BLANK);
		}
		else {
			actionLogImpl.setModifiedFqgn(modifiedFqgn);
		}

		actionLogImpl.resetOriginalValues();

		return actionLogImpl;
	}

	public long id;
	public long companyId;
	public long userId;
	public String userName;
	public long modifiedDate;
	public String userEmailAddress;
	public long modifiedUserId;
	public String modifiedUserName;
	public String modifiedUserEmailAddress;
	public String modifiedDescription;
	public String modifiedFqgn;
}