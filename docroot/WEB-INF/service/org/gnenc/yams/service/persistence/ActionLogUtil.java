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

package org.gnenc.yams.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.gnenc.yams.model.ActionLog;

import java.util.List;

/**
 * The persistence utility for the action log service. This utility wraps {@link ActionLogPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Drew A. Blessing
 * @see ActionLogPersistence
 * @see ActionLogPersistenceImpl
 * @generated
 */
public class ActionLogUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(ActionLog actionLog) {
		getPersistence().clearCache(actionLog);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ActionLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ActionLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ActionLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static ActionLog update(ActionLog actionLog, boolean merge)
		throws SystemException {
		return getPersistence().update(actionLog, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static ActionLog update(ActionLog actionLog, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(actionLog, merge, serviceContext);
	}

	/**
	* Caches the action log in the entity cache if it is enabled.
	*
	* @param actionLog the action log
	*/
	public static void cacheResult(org.gnenc.yams.model.ActionLog actionLog) {
		getPersistence().cacheResult(actionLog);
	}

	/**
	* Caches the action logs in the entity cache if it is enabled.
	*
	* @param actionLogs the action logs
	*/
	public static void cacheResult(
		java.util.List<org.gnenc.yams.model.ActionLog> actionLogs) {
		getPersistence().cacheResult(actionLogs);
	}

	/**
	* Creates a new action log with the primary key. Does not add the action log to the database.
	*
	* @param id the primary key for the new action log
	* @return the new action log
	*/
	public static org.gnenc.yams.model.ActionLog create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the action log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the action log
	* @return the action log that was removed
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog remove(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence().remove(id);
	}

	public static org.gnenc.yams.model.ActionLog updateImpl(
		org.gnenc.yams.model.ActionLog actionLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(actionLog, merge);
	}

	/**
	* Returns the action log with the primary key or throws a {@link org.gnenc.yams.NoSuchActionLogException} if it could not be found.
	*
	* @param id the primary key of the action log
	* @return the action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the action log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the action log
	* @return the action log, or <code>null</code> if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog fetchByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	* Returns all the action logs where userEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findByuserEmailAddress(
		java.lang.String userEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByuserEmailAddress(userEmailAddress);
	}

	/**
	* Returns a range of all the action logs where userEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userEmailAddress the user email address
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @return the range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findByuserEmailAddress(
		java.lang.String userEmailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByuserEmailAddress(userEmailAddress, start, end);
	}

	/**
	* Returns an ordered range of all the action logs where userEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userEmailAddress the user email address
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findByuserEmailAddress(
		java.lang.String userEmailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByuserEmailAddress(userEmailAddress, start, end,
			orderByComparator);
	}

	/**
	* Returns the first action log in the ordered set where userEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userEmailAddress the user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findByuserEmailAddress_First(
		java.lang.String userEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findByuserEmailAddress_First(userEmailAddress,
			orderByComparator);
	}

	/**
	* Returns the last action log in the ordered set where userEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userEmailAddress the user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findByuserEmailAddress_Last(
		java.lang.String userEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findByuserEmailAddress_Last(userEmailAddress,
			orderByComparator);
	}

	/**
	* Returns the action logs before and after the current action log in the ordered set where userEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current action log
	* @param userEmailAddress the user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog[] findByuserEmailAddress_PrevAndNext(
		long id, java.lang.String userEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findByuserEmailAddress_PrevAndNext(id, userEmailAddress,
			orderByComparator);
	}

	/**
	* Returns all the action logs where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDate(
		java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBymodifiedDate(modifiedDate);
	}

	/**
	* Returns a range of all the action logs where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @return the range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDate(
		java.util.Date modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBymodifiedDate(modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the action logs where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDate(
		java.util.Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedDate(modifiedDate, start, end,
			orderByComparator);
	}

	/**
	* Returns the first action log in the ordered set where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedDate_First(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last action log in the ordered set where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedDate_Last(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current action log
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog[] findBymodifiedDate_PrevAndNext(
		long id, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedDate_PrevAndNext(id, modifiedDate,
			orderByComparator);
	}

	/**
	* Returns all the action logs where modifiedDescription = &#63;.
	*
	* @param modifiedDescription the modified description
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDescription(
		java.lang.String modifiedDescription)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBymodifiedDescription(modifiedDescription);
	}

	/**
	* Returns a range of all the action logs where modifiedDescription = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDescription the modified description
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @return the range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDescription(
		java.lang.String modifiedDescription, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedDescription(modifiedDescription, start, end);
	}

	/**
	* Returns an ordered range of all the action logs where modifiedDescription = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDescription the modified description
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDescription(
		java.lang.String modifiedDescription, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedDescription(modifiedDescription, start, end,
			orderByComparator);
	}

	/**
	* Returns the first action log in the ordered set where modifiedDescription = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDescription the modified description
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedDescription_First(
		java.lang.String modifiedDescription,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedDescription_First(modifiedDescription,
			orderByComparator);
	}

	/**
	* Returns the last action log in the ordered set where modifiedDescription = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDescription the modified description
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedDescription_Last(
		java.lang.String modifiedDescription,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedDescription_Last(modifiedDescription,
			orderByComparator);
	}

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedDescription = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current action log
	* @param modifiedDescription the modified description
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog[] findBymodifiedDescription_PrevAndNext(
		long id, java.lang.String modifiedDescription,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedDescription_PrevAndNext(id,
			modifiedDescription, orderByComparator);
	}

	/**
	* Returns all the action logs where modifiedFqgn = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedFqgn(
		java.lang.String modifiedFqgn)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBymodifiedFqgn(modifiedFqgn);
	}

	/**
	* Returns a range of all the action logs where modifiedFqgn = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedFqgn the modified fqgn
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @return the range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedFqgn(
		java.lang.String modifiedFqgn, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBymodifiedFqgn(modifiedFqgn, start, end);
	}

	/**
	* Returns an ordered range of all the action logs where modifiedFqgn = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedFqgn the modified fqgn
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedFqgn(
		java.lang.String modifiedFqgn, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedFqgn(modifiedFqgn, start, end,
			orderByComparator);
	}

	/**
	* Returns the first action log in the ordered set where modifiedFqgn = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedFqgn the modified fqgn
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedFqgn_First(
		java.lang.String modifiedFqgn,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedFqgn_First(modifiedFqgn, orderByComparator);
	}

	/**
	* Returns the last action log in the ordered set where modifiedFqgn = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedFqgn the modified fqgn
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedFqgn_Last(
		java.lang.String modifiedFqgn,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedFqgn_Last(modifiedFqgn, orderByComparator);
	}

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedFqgn = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current action log
	* @param modifiedFqgn the modified fqgn
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog[] findBymodifiedFqgn_PrevAndNext(
		long id, java.lang.String modifiedFqgn,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedFqgn_PrevAndNext(id, modifiedFqgn,
			orderByComparator);
	}

	/**
	* Returns all the action logs where modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedUserEmailAddress(
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedUserEmailAddress(modifiedUserEmailAddress);
	}

	/**
	* Returns a range of all the action logs where modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @return the range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedUserEmailAddress(
		java.lang.String modifiedUserEmailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedUserEmailAddress(modifiedUserEmailAddress,
			start, end);
	}

	/**
	* Returns an ordered range of all the action logs where modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedUserEmailAddress(
		java.lang.String modifiedUserEmailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedUserEmailAddress(modifiedUserEmailAddress,
			start, end, orderByComparator);
	}

	/**
	* Returns the first action log in the ordered set where modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedUserEmailAddress_First(
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedUserEmailAddress_First(modifiedUserEmailAddress,
			orderByComparator);
	}

	/**
	* Returns the last action log in the ordered set where modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedUserEmailAddress_Last(
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedUserEmailAddress_Last(modifiedUserEmailAddress,
			orderByComparator);
	}

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current action log
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog[] findBymodifiedUserEmailAddress_PrevAndNext(
		long id, java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedUserEmailAddress_PrevAndNext(id,
			modifiedUserEmailAddress, orderByComparator);
	}

	/**
	* Returns all the action logs where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedFqgnAndModifiedDate(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedFqgnAndModifiedDate(modifiedFqgn, modifiedDate);
	}

	/**
	* Returns a range of all the action logs where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @return the range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedFqgnAndModifiedDate(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedFqgnAndModifiedDate(modifiedFqgn,
			modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the action logs where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedFqgnAndModifiedDate(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedFqgnAndModifiedDate(modifiedFqgn,
			modifiedDate, start, end, orderByComparator);
	}

	/**
	* Returns the first action log in the ordered set where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedFqgnAndModifiedDate_First(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedFqgnAndModifiedDate_First(modifiedFqgn,
			modifiedDate, orderByComparator);
	}

	/**
	* Returns the last action log in the ordered set where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedFqgnAndModifiedDate_Last(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedFqgnAndModifiedDate_Last(modifiedFqgn,
			modifiedDate, orderByComparator);
	}

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current action log
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog[] findBymodifiedFqgnAndModifiedDate_PrevAndNext(
		long id, java.lang.String modifiedFqgn, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedFqgnAndModifiedDate_PrevAndNext(id,
			modifiedFqgn, modifiedDate, orderByComparator);
	}

	/**
	* Returns all the action logs where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findByuserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByuserEmailAddressAndModifiedUserEmailAddress(userEmailAddress,
			modifiedUserEmailAddress);
	}

	/**
	* Returns a range of all the action logs where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @return the range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findByuserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByuserEmailAddressAndModifiedUserEmailAddress(userEmailAddress,
			modifiedUserEmailAddress, start, end);
	}

	/**
	* Returns an ordered range of all the action logs where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findByuserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByuserEmailAddressAndModifiedUserEmailAddress(userEmailAddress,
			modifiedUserEmailAddress, start, end, orderByComparator);
	}

	/**
	* Returns the first action log in the ordered set where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findByuserEmailAddressAndModifiedUserEmailAddress_First(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findByuserEmailAddressAndModifiedUserEmailAddress_First(userEmailAddress,
			modifiedUserEmailAddress, orderByComparator);
	}

	/**
	* Returns the last action log in the ordered set where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findByuserEmailAddressAndModifiedUserEmailAddress_Last(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findByuserEmailAddressAndModifiedUserEmailAddress_Last(userEmailAddress,
			modifiedUserEmailAddress, orderByComparator);
	}

	/**
	* Returns the action logs before and after the current action log in the ordered set where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current action log
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog[] findByuserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(
		long id, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findByuserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(id,
			userEmailAddress, modifiedUserEmailAddress, orderByComparator);
	}

	/**
	* Returns all the action logs where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDate,
			userEmailAddress, modifiedUserEmailAddress);
	}

	/**
	* Returns a range of all the action logs where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @return the range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDate,
			userEmailAddress, modifiedUserEmailAddress, start, end);
	}

	/**
	* Returns an ordered range of all the action logs where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDate,
			userEmailAddress, modifiedUserEmailAddress, start, end,
			orderByComparator);
	}

	/**
	* Returns the first action log in the ordered set where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_First(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_First(modifiedDate,
			userEmailAddress, modifiedUserEmailAddress, orderByComparator);
	}

	/**
	* Returns the last action log in the ordered set where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_Last(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_Last(modifiedDate,
			userEmailAddress, modifiedUserEmailAddress, orderByComparator);
	}

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current action log
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog[] findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(
		long id, java.util.Date modifiedDate,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(id,
			modifiedDate, userEmailAddress, modifiedUserEmailAddress,
			orderByComparator);
	}

	/**
	* Returns all the action logs where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDescription,
			userEmailAddress, modifiedUserEmailAddress);
	}

	/**
	* Returns a range of all the action logs where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @return the range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDescription,
			userEmailAddress, modifiedUserEmailAddress, start, end);
	}

	/**
	* Returns an ordered range of all the action logs where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDescription,
			userEmailAddress, modifiedUserEmailAddress, start, end,
			orderByComparator);
	}

	/**
	* Returns the first action log in the ordered set where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_First(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_First(modifiedDescription,
			userEmailAddress, modifiedUserEmailAddress, orderByComparator);
	}

	/**
	* Returns the last action log in the ordered set where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_Last(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_Last(modifiedDescription,
			userEmailAddress, modifiedUserEmailAddress, orderByComparator);
	}

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current action log
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.ActionLog[] findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(
		long id, java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException {
		return getPersistence()
				   .findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(id,
			modifiedDescription, userEmailAddress, modifiedUserEmailAddress,
			orderByComparator);
	}

	/**
	* Returns all the action logs.
	*
	* @return the action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<org.gnenc.yams.model.ActionLog> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the action logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of action logs
	* @param end the upper bound of the range of action logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of action logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.ActionLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the action logs where userEmailAddress = &#63; from the database.
	*
	* @param userEmailAddress the user email address
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByuserEmailAddress(
		java.lang.String userEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByuserEmailAddress(userEmailAddress);
	}

	/**
	* Removes all the action logs where modifiedDate = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBymodifiedDate(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBymodifiedDate(modifiedDate);
	}

	/**
	* Removes all the action logs where modifiedDescription = &#63; from the database.
	*
	* @param modifiedDescription the modified description
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBymodifiedDescription(
		java.lang.String modifiedDescription)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBymodifiedDescription(modifiedDescription);
	}

	/**
	* Removes all the action logs where modifiedFqgn = &#63; from the database.
	*
	* @param modifiedFqgn the modified fqgn
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBymodifiedFqgn(java.lang.String modifiedFqgn)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBymodifiedFqgn(modifiedFqgn);
	}

	/**
	* Removes all the action logs where modifiedUserEmailAddress = &#63; from the database.
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBymodifiedUserEmailAddress(
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeBymodifiedUserEmailAddress(modifiedUserEmailAddress);
	}

	/**
	* Removes all the action logs where modifiedFqgn = &#63; and modifiedDate = &#63; from the database.
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBymodifiedFqgnAndModifiedDate(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeBymodifiedFqgnAndModifiedDate(modifiedFqgn, modifiedDate);
	}

	/**
	* Removes all the action logs where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63; from the database.
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByuserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByuserEmailAddressAndModifiedUserEmailAddress(userEmailAddress,
			modifiedUserEmailAddress);
	}

	/**
	* Removes all the action logs where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDate,
			userEmailAddress, modifiedUserEmailAddress);
	}

	/**
	* Removes all the action logs where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63; from the database.
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDescription,
			userEmailAddress, modifiedUserEmailAddress);
	}

	/**
	* Removes all the action logs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of action logs where userEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByuserEmailAddress(java.lang.String userEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByuserEmailAddress(userEmailAddress);
	}

	/**
	* Returns the number of action logs where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBymodifiedDate(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBymodifiedDate(modifiedDate);
	}

	/**
	* Returns the number of action logs where modifiedDescription = &#63;.
	*
	* @param modifiedDescription the modified description
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBymodifiedDescription(
		java.lang.String modifiedDescription)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBymodifiedDescription(modifiedDescription);
	}

	/**
	* Returns the number of action logs where modifiedFqgn = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBymodifiedFqgn(java.lang.String modifiedFqgn)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBymodifiedFqgn(modifiedFqgn);
	}

	/**
	* Returns the number of action logs where modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBymodifiedUserEmailAddress(
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countBymodifiedUserEmailAddress(modifiedUserEmailAddress);
	}

	/**
	* Returns the number of action logs where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBymodifiedFqgnAndModifiedDate(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countBymodifiedFqgnAndModifiedDate(modifiedFqgn,
			modifiedDate);
	}

	/**
	* Returns the number of action logs where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByuserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByuserEmailAddressAndModifiedUserEmailAddress(userEmailAddress,
			modifiedUserEmailAddress);
	}

	/**
	* Returns the number of action logs where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDate,
			userEmailAddress, modifiedUserEmailAddress);
	}

	/**
	* Returns the number of action logs where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(modifiedDescription,
			userEmailAddress, modifiedUserEmailAddress);
	}

	/**
	* Returns the number of action logs.
	*
	* @return the number of action logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ActionLogPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ActionLogPersistence)PortletBeanLocatorUtil.locate(org.gnenc.yams.service.ClpSerializer.getServletContextName(),
					ActionLogPersistence.class.getName());

			ReferenceRegistry.registerReference(ActionLogUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(ActionLogPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(ActionLogUtil.class, "_persistence");
	}

	private static ActionLogPersistence _persistence;
}