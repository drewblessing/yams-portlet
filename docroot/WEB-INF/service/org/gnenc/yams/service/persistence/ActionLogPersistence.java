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

import com.liferay.portal.service.persistence.BasePersistence;

import org.gnenc.yams.model.ActionLog;

/**
 * The persistence interface for the action log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Drew A. Blessing
 * @see ActionLogPersistenceImpl
 * @see ActionLogUtil
 * @generated
 */
public interface ActionLogPersistence extends BasePersistence<ActionLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ActionLogUtil} to access the action log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the action log in the entity cache if it is enabled.
	*
	* @param actionLog the action log
	*/
	public void cacheResult(org.gnenc.yams.model.ActionLog actionLog);

	/**
	* Caches the action logs in the entity cache if it is enabled.
	*
	* @param actionLogs the action logs
	*/
	public void cacheResult(
		java.util.List<org.gnenc.yams.model.ActionLog> actionLogs);

	/**
	* Creates a new action log with the primary key. Does not add the action log to the database.
	*
	* @param id the primary key for the new action log
	* @return the new action log
	*/
	public org.gnenc.yams.model.ActionLog create(long id);

	/**
	* Removes the action log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the action log
	* @return the action log that was removed
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog remove(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	public org.gnenc.yams.model.ActionLog updateImpl(
		org.gnenc.yams.model.ActionLog actionLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the action log with the primary key or throws a {@link org.gnenc.yams.NoSuchActionLogException} if it could not be found.
	*
	* @param id the primary key of the action log
	* @return the action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the action log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the action log
	* @return the action log, or <code>null</code> if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the action logs where userEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.ActionLog> findByuserEmailAddress(
		java.lang.String userEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findByuserEmailAddress(
		java.lang.String userEmailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findByuserEmailAddress(
		java.lang.String userEmailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first action log in the ordered set where userEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findByuserEmailAddress_First(
		java.lang.String userEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the first action log in the ordered set where userEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchByuserEmailAddress_First(
		java.lang.String userEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last action log in the ordered set where userEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findByuserEmailAddress_Last(
		java.lang.String userEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the last action log in the ordered set where userEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchByuserEmailAddress_Last(
		java.lang.String userEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the action logs before and after the current action log in the ordered set where userEmailAddress = &#63;.
	*
	* @param id the primary key of the current action log
	* @param userEmailAddress the user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog[] findByuserEmailAddress_PrevAndNext(
		long id, java.lang.String userEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns all the action logs where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDate(
		java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDate(
		java.util.Date modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDate(
		java.util.Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first action log in the ordered set where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedDate_First(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the first action log in the ordered set where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedDate_First(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last action log in the ordered set where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedDate_Last(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the last action log in the ordered set where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedDate_Last(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedDate = &#63;.
	*
	* @param id the primary key of the current action log
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog[] findBymodifiedDate_PrevAndNext(
		long id, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns all the action logs where modifiedDescription = &#63;.
	*
	* @param modifiedDescription the modified description
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDescription(
		java.lang.String modifiedDescription)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDescription(
		java.lang.String modifiedDescription, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDescription(
		java.lang.String modifiedDescription, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first action log in the ordered set where modifiedDescription = &#63;.
	*
	* @param modifiedDescription the modified description
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedDescription_First(
		java.lang.String modifiedDescription,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the first action log in the ordered set where modifiedDescription = &#63;.
	*
	* @param modifiedDescription the modified description
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedDescription_First(
		java.lang.String modifiedDescription,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last action log in the ordered set where modifiedDescription = &#63;.
	*
	* @param modifiedDescription the modified description
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedDescription_Last(
		java.lang.String modifiedDescription,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the last action log in the ordered set where modifiedDescription = &#63;.
	*
	* @param modifiedDescription the modified description
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedDescription_Last(
		java.lang.String modifiedDescription,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedDescription = &#63;.
	*
	* @param id the primary key of the current action log
	* @param modifiedDescription the modified description
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog[] findBymodifiedDescription_PrevAndNext(
		long id, java.lang.String modifiedDescription,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns all the action logs where modifiedFqgn = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedFqgn(
		java.lang.String modifiedFqgn)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedFqgn(
		java.lang.String modifiedFqgn, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedFqgn(
		java.lang.String modifiedFqgn, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first action log in the ordered set where modifiedFqgn = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedFqgn_First(
		java.lang.String modifiedFqgn,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the first action log in the ordered set where modifiedFqgn = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedFqgn_First(
		java.lang.String modifiedFqgn,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last action log in the ordered set where modifiedFqgn = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedFqgn_Last(
		java.lang.String modifiedFqgn,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the last action log in the ordered set where modifiedFqgn = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedFqgn_Last(
		java.lang.String modifiedFqgn,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedFqgn = &#63;.
	*
	* @param id the primary key of the current action log
	* @param modifiedFqgn the modified fqgn
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog[] findBymodifiedFqgn_PrevAndNext(
		long id, java.lang.String modifiedFqgn,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns all the action logs where modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedUserEmailAddress(
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedUserEmailAddress(
		java.lang.String modifiedUserEmailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedUserEmailAddress(
		java.lang.String modifiedUserEmailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first action log in the ordered set where modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedUserEmailAddress_First(
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the first action log in the ordered set where modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedUserEmailAddress_First(
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last action log in the ordered set where modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedUserEmailAddress_Last(
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the last action log in the ordered set where modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedUserEmailAddress_Last(
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedUserEmailAddress = &#63;.
	*
	* @param id the primary key of the current action log
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog[] findBymodifiedUserEmailAddress_PrevAndNext(
		long id, java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns all the action logs where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedFqgnAndModifiedDate(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedFqgnAndModifiedDate(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedFqgnAndModifiedDate(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first action log in the ordered set where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedFqgnAndModifiedDate_First(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the first action log in the ordered set where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedFqgnAndModifiedDate_First(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last action log in the ordered set where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedFqgnAndModifiedDate_Last(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the last action log in the ordered set where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedFqgnAndModifiedDate_Last(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* @param id the primary key of the current action log
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog[] findBymodifiedFqgnAndModifiedDate_PrevAndNext(
		long id, java.lang.String modifiedFqgn, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns all the action logs where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.ActionLog> findByuserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findByuserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findByuserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first action log in the ordered set where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findByuserEmailAddressAndModifiedUserEmailAddress_First(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the first action log in the ordered set where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchByuserEmailAddressAndModifiedUserEmailAddress_First(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last action log in the ordered set where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findByuserEmailAddressAndModifiedUserEmailAddress_Last(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the last action log in the ordered set where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchByuserEmailAddressAndModifiedUserEmailAddress_Last(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the action logs before and after the current action log in the ordered set where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param id the primary key of the current action log
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a action log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog[] findByuserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(
		long id, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns all the action logs where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first action log in the ordered set where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_First(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the first action log in the ordered set where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_First(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last action log in the ordered set where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_Last(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the last action log in the ordered set where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_Last(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
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
	public org.gnenc.yams.model.ActionLog[] findBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(
		long id, java.util.Date modifiedDate,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns all the action logs where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @return the matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first action log in the ordered set where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_First(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the first action log in the ordered set where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_First(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last action log in the ordered set where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log
	* @throws org.gnenc.yams.NoSuchActionLogException if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_Last(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns the last action log in the ordered set where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action log, or <code>null</code> if a matching action log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.ActionLog fetchBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_Last(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the action logs before and after the current action log in the ordered set where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
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
	public org.gnenc.yams.model.ActionLog[] findBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress_PrevAndNext(
		long id, java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchActionLogException;

	/**
	* Returns all the action logs.
	*
	* @return the action logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.ActionLog> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.gnenc.yams.model.ActionLog> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the action logs where userEmailAddress = &#63; from the database.
	*
	* @param userEmailAddress the user email address
	* @throws SystemException if a system exception occurred
	*/
	public void removeByuserEmailAddress(java.lang.String userEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the action logs where modifiedDate = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @throws SystemException if a system exception occurred
	*/
	public void removeBymodifiedDate(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the action logs where modifiedDescription = &#63; from the database.
	*
	* @param modifiedDescription the modified description
	* @throws SystemException if a system exception occurred
	*/
	public void removeBymodifiedDescription(
		java.lang.String modifiedDescription)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the action logs where modifiedFqgn = &#63; from the database.
	*
	* @param modifiedFqgn the modified fqgn
	* @throws SystemException if a system exception occurred
	*/
	public void removeBymodifiedFqgn(java.lang.String modifiedFqgn)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the action logs where modifiedUserEmailAddress = &#63; from the database.
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @throws SystemException if a system exception occurred
	*/
	public void removeBymodifiedUserEmailAddress(
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the action logs where modifiedFqgn = &#63; and modifiedDate = &#63; from the database.
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @throws SystemException if a system exception occurred
	*/
	public void removeBymodifiedFqgnAndModifiedDate(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the action logs where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63; from the database.
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @throws SystemException if a system exception occurred
	*/
	public void removeByuserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the action logs where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @throws SystemException if a system exception occurred
	*/
	public void removeBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the action logs where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63; from the database.
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @throws SystemException if a system exception occurred
	*/
	public void removeBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the action logs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of action logs where userEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public int countByuserEmailAddress(java.lang.String userEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of action logs where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public int countBymodifiedDate(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of action logs where modifiedDescription = &#63;.
	*
	* @param modifiedDescription the modified description
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public int countBymodifiedDescription(java.lang.String modifiedDescription)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of action logs where modifiedFqgn = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public int countBymodifiedFqgn(java.lang.String modifiedFqgn)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of action logs where modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedUserEmailAddress the modified user email address
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public int countBymodifiedUserEmailAddress(
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of action logs where modifiedFqgn = &#63; and modifiedDate = &#63;.
	*
	* @param modifiedFqgn the modified fqgn
	* @param modifiedDate the modified date
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public int countBymodifiedFqgnAndModifiedDate(
		java.lang.String modifiedFqgn, java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of action logs where userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public int countByuserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of action logs where modifiedDate = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDate the modified date
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public int countBymodifiedDateAndUserEmailAddressAndModifiedUserEmailAddress(
		java.util.Date modifiedDate, java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of action logs where modifiedDescription = &#63; and userEmailAddress = &#63; and modifiedUserEmailAddress = &#63;.
	*
	* @param modifiedDescription the modified description
	* @param userEmailAddress the user email address
	* @param modifiedUserEmailAddress the modified user email address
	* @return the number of matching action logs
	* @throws SystemException if a system exception occurred
	*/
	public int countBymodifiedDescriptionAndUserEmailAddressAndModifiedUserEmailAddress(
		java.lang.String modifiedDescription,
		java.lang.String userEmailAddress,
		java.lang.String modifiedUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of action logs.
	*
	* @return the number of action logs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}