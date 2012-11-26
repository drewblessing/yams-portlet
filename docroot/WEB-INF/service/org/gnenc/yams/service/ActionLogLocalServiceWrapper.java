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

package org.gnenc.yams.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ActionLogLocalService}.
 * </p>
 *
 * @author    Drew A. Blessing
 * @see       ActionLogLocalService
 * @generated
 */
public class ActionLogLocalServiceWrapper implements ActionLogLocalService,
	ServiceWrapper<ActionLogLocalService> {
	public ActionLogLocalServiceWrapper(
		ActionLogLocalService actionLogLocalService) {
		_actionLogLocalService = actionLogLocalService;
	}

	/**
	* Adds the action log to the database. Also notifies the appropriate model listeners.
	*
	* @param actionLog the action log
	* @return the action log that was added
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog addActionLog(
		org.gnenc.yams.model.ActionLog actionLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.addActionLog(actionLog);
	}

	/**
	* Creates a new action log with the primary key. Does not add the action log to the database.
	*
	* @param id the primary key for the new action log
	* @return the new action log
	*/
	public org.gnenc.yams.model.ActionLog createActionLog(long id) {
		return _actionLogLocalService.createActionLog(id);
	}

	/**
	* Deletes the action log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the action log
	* @return the action log that was removed
	* @throws PortalException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog deleteActionLog(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.deleteActionLog(id);
	}

	/**
	* Deletes the action log from the database. Also notifies the appropriate model listeners.
	*
	* @param actionLog the action log
	* @return the action log that was removed
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog deleteActionLog(
		org.gnenc.yams.model.ActionLog actionLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.deleteActionLog(actionLog);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _actionLogLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.dynamicQueryCount(dynamicQuery);
	}

	public org.gnenc.yams.model.ActionLog fetchActionLog(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.fetchActionLog(id);
	}

	/**
	* Returns the action log with the primary key.
	*
	* @param id the primary key of the action log
	* @return the action log
	* @throws PortalException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog getActionLog(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.getActionLog(id);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the action logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @return the range of action logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.ActionLog> getActionLogs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.getActionLogs(start, end);
	}

	/**
	* Returns the number of action logs.
	*
	* @return the number of action logs
	* @throws SystemException if a system exception occurred
	*/
	public int getActionLogsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.getActionLogsCount();
	}

	/**
	* Updates the action log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param actionLog the action log
	* @return the action log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog updateActionLog(
		org.gnenc.yams.model.ActionLog actionLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.updateActionLog(actionLog);
	}

	/**
	* Updates the action log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param actionLog the action log
	* @param merge whether to merge the action log with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the action log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog updateActionLog(
		org.gnenc.yams.model.ActionLog actionLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.updateActionLog(actionLog, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _actionLogLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_actionLogLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _actionLogLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public org.gnenc.yams.model.ActionLog addAction(long userId,
		long modifiedUserId, java.lang.String email, java.lang.String fullName,
		java.lang.String modifiedFqgn, java.lang.String modifiedDescription)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _actionLogLocalService.addAction(userId, modifiedUserId, email,
			fullName, modifiedFqgn, modifiedDescription);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ActionLogLocalService getWrappedActionLogLocalService() {
		return _actionLogLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedActionLogLocalService(
		ActionLogLocalService actionLogLocalService) {
		_actionLogLocalService = actionLogLocalService;
	}

	public ActionLogLocalService getWrappedService() {
		return _actionLogLocalService;
	}

	public void setWrappedService(ActionLogLocalService actionLogLocalService) {
		_actionLogLocalService = actionLogLocalService;
	}

	private ActionLogLocalService _actionLogLocalService;
}