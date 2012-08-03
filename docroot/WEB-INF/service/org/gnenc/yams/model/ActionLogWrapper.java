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

/**
 * <p>
 * This class is a wrapper for {@link ActionLog}.
 * </p>
 *
 * @author    Drew A. Blessing
 * @see       ActionLog
 * @generated
 */
public class ActionLogWrapper implements ActionLog, ModelWrapper<ActionLog> {
	public ActionLogWrapper(ActionLog actionLog) {
		_actionLog = actionLog;
	}

	public Class<?> getModelClass() {
		return ActionLog.class;
	}

	public String getModelClassName() {
		return ActionLog.class.getName();
	}

	/**
	* Returns the primary key of this action log.
	*
	* @return the primary key of this action log
	*/
	public long getPrimaryKey() {
		return _actionLog.getPrimaryKey();
	}

	/**
	* Sets the primary key of this action log.
	*
	* @param primaryKey the primary key of this action log
	*/
	public void setPrimaryKey(long primaryKey) {
		_actionLog.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ID of this action log.
	*
	* @return the ID of this action log
	*/
	public long getId() {
		return _actionLog.getId();
	}

	/**
	* Sets the ID of this action log.
	*
	* @param id the ID of this action log
	*/
	public void setId(long id) {
		_actionLog.setId(id);
	}

	/**
	* Returns the company ID of this action log.
	*
	* @return the company ID of this action log
	*/
	public long getCompanyId() {
		return _actionLog.getCompanyId();
	}

	/**
	* Sets the company ID of this action log.
	*
	* @param companyId the company ID of this action log
	*/
	public void setCompanyId(long companyId) {
		_actionLog.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this action log.
	*
	* @return the user ID of this action log
	*/
	public long getUserId() {
		return _actionLog.getUserId();
	}

	/**
	* Sets the user ID of this action log.
	*
	* @param userId the user ID of this action log
	*/
	public void setUserId(long userId) {
		_actionLog.setUserId(userId);
	}

	/**
	* Returns the user uuid of this action log.
	*
	* @return the user uuid of this action log
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLog.getUserUuid();
	}

	/**
	* Sets the user uuid of this action log.
	*
	* @param userUuid the user uuid of this action log
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_actionLog.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this action log.
	*
	* @return the user name of this action log
	*/
	public java.lang.String getUserName() {
		return _actionLog.getUserName();
	}

	/**
	* Sets the user name of this action log.
	*
	* @param userName the user name of this action log
	*/
	public void setUserName(java.lang.String userName) {
		_actionLog.setUserName(userName);
	}

	/**
	* Returns the modified date of this action log.
	*
	* @return the modified date of this action log
	*/
	public java.util.Date getModifiedDate() {
		return _actionLog.getModifiedDate();
	}

	/**
	* Sets the modified date of this action log.
	*
	* @param modifiedDate the modified date of this action log
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_actionLog.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the user email address of this action log.
	*
	* @return the user email address of this action log
	*/
	public java.lang.String getUserEmailAddress() {
		return _actionLog.getUserEmailAddress();
	}

	/**
	* Sets the user email address of this action log.
	*
	* @param userEmailAddress the user email address of this action log
	*/
	public void setUserEmailAddress(java.lang.String userEmailAddress) {
		_actionLog.setUserEmailAddress(userEmailAddress);
	}

	/**
	* Returns the modified user ID of this action log.
	*
	* @return the modified user ID of this action log
	*/
	public long getModifiedUserId() {
		return _actionLog.getModifiedUserId();
	}

	/**
	* Sets the modified user ID of this action log.
	*
	* @param modifiedUserId the modified user ID of this action log
	*/
	public void setModifiedUserId(long modifiedUserId) {
		_actionLog.setModifiedUserId(modifiedUserId);
	}

	/**
	* Returns the modified user uuid of this action log.
	*
	* @return the modified user uuid of this action log
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getModifiedUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLog.getModifiedUserUuid();
	}

	/**
	* Sets the modified user uuid of this action log.
	*
	* @param modifiedUserUuid the modified user uuid of this action log
	*/
	public void setModifiedUserUuid(java.lang.String modifiedUserUuid) {
		_actionLog.setModifiedUserUuid(modifiedUserUuid);
	}

	/**
	* Returns the modified user name of this action log.
	*
	* @return the modified user name of this action log
	*/
	public java.lang.String getModifiedUserName() {
		return _actionLog.getModifiedUserName();
	}

	/**
	* Sets the modified user name of this action log.
	*
	* @param modifiedUserName the modified user name of this action log
	*/
	public void setModifiedUserName(java.lang.String modifiedUserName) {
		_actionLog.setModifiedUserName(modifiedUserName);
	}

	/**
	* Returns the modified user email address of this action log.
	*
	* @return the modified user email address of this action log
	*/
	public java.lang.String getModifiedUserEmailAddress() {
		return _actionLog.getModifiedUserEmailAddress();
	}

	/**
	* Sets the modified user email address of this action log.
	*
	* @param modifiedUserEmailAddress the modified user email address of this action log
	*/
	public void setModifiedUserEmailAddress(
		java.lang.String modifiedUserEmailAddress) {
		_actionLog.setModifiedUserEmailAddress(modifiedUserEmailAddress);
	}

	/**
	* Returns the modified description of this action log.
	*
	* @return the modified description of this action log
	*/
	public java.lang.String getModifiedDescription() {
		return _actionLog.getModifiedDescription();
	}

	/**
	* Sets the modified description of this action log.
	*
	* @param modifiedDescription the modified description of this action log
	*/
	public void setModifiedDescription(java.lang.String modifiedDescription) {
		_actionLog.setModifiedDescription(modifiedDescription);
	}

	/**
	* Returns the modified fqgn of this action log.
	*
	* @return the modified fqgn of this action log
	*/
	public java.lang.String getModifiedFqgn() {
		return _actionLog.getModifiedFqgn();
	}

	/**
	* Sets the modified fqgn of this action log.
	*
	* @param modifiedFqgn the modified fqgn of this action log
	*/
	public void setModifiedFqgn(java.lang.String modifiedFqgn) {
		_actionLog.setModifiedFqgn(modifiedFqgn);
	}

	public boolean isNew() {
		return _actionLog.isNew();
	}

	public void setNew(boolean n) {
		_actionLog.setNew(n);
	}

	public boolean isCachedModel() {
		return _actionLog.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_actionLog.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _actionLog.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _actionLog.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_actionLog.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _actionLog.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_actionLog.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ActionLogWrapper((ActionLog)_actionLog.clone());
	}

	public int compareTo(org.gnenc.yams.model.ActionLog actionLog) {
		return _actionLog.compareTo(actionLog);
	}

	@Override
	public int hashCode() {
		return _actionLog.hashCode();
	}

	public com.liferay.portal.model.CacheModel<org.gnenc.yams.model.ActionLog> toCacheModel() {
		return _actionLog.toCacheModel();
	}

	public org.gnenc.yams.model.ActionLog toEscapedModel() {
		return new ActionLogWrapper(_actionLog.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _actionLog.toString();
	}

	public java.lang.String toXmlString() {
		return _actionLog.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_actionLog.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public ActionLog getWrappedActionLog() {
		return _actionLog;
	}

	public ActionLog getWrappedModel() {
		return _actionLog;
	}

	public void resetOriginalValues() {
		_actionLog.resetOriginalValues();
	}

	private ActionLog _actionLog;
}