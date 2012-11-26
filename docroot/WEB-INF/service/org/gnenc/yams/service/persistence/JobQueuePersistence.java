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

import org.gnenc.yams.model.JobQueue;

/**
 * The persistence interface for the job queue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Drew A. Blessing
 * @see JobQueuePersistenceImpl
 * @see JobQueueUtil
 * @generated
 */
public interface JobQueuePersistence extends BasePersistence<JobQueue> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JobQueueUtil} to access the job queue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the job queue in the entity cache if it is enabled.
	*
	* @param jobQueue the job queue
	*/
	public void cacheResult(org.gnenc.yams.model.JobQueue jobQueue);

	/**
	* Caches the job queues in the entity cache if it is enabled.
	*
	* @param jobQueues the job queues
	*/
	public void cacheResult(
		java.util.List<org.gnenc.yams.model.JobQueue> jobQueues);

	/**
	* Creates a new job queue with the primary key. Does not add the job queue to the database.
	*
	* @param id the primary key for the new job queue
	* @return the new job queue
	*/
	public org.gnenc.yams.model.JobQueue create(long id);

	/**
	* Removes the job queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the job queue
	* @return the job queue that was removed
	* @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue remove(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	public org.gnenc.yams.model.JobQueue updateImpl(
		org.gnenc.yams.model.JobQueue jobQueue, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the job queue with the primary key or throws a {@link org.gnenc.yams.NoSuchJobQueueException} if it could not be found.
	*
	* @param id the primary key of the job queue
	* @return the job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue findByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	/**
	* Returns the job queue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the job queue
	* @return the job queue, or <code>null</code> if a job queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue fetchByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the job queues where jobAction = &#63;.
	*
	* @param jobAction the job action
	* @return the matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findByJobAction(
		java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the job queues where jobAction = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jobAction the job action
	* @param start the lower bound of the range of job queues
	* @param end the upper bound of the range of job queues (not inclusive)
	* @return the range of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findByJobAction(
		java.lang.String jobAction, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the job queues where jobAction = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jobAction the job action
	* @param start the lower bound of the range of job queues
	* @param end the upper bound of the range of job queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findByJobAction(
		java.lang.String jobAction, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first job queue in the ordered set where jobAction = &#63;.
	*
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue findByJobAction_First(
		java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	/**
	* Returns the first job queue in the ordered set where jobAction = &#63;.
	*
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue fetchByJobAction_First(
		java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last job queue in the ordered set where jobAction = &#63;.
	*
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue findByJobAction_Last(
		java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	/**
	* Returns the last job queue in the ordered set where jobAction = &#63;.
	*
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue fetchByJobAction_Last(
		java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the job queues before and after the current job queue in the ordered set where jobAction = &#63;.
	*
	* @param id the primary key of the current job queue
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue[] findByJobAction_PrevAndNext(
		long id, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	/**
	* Returns all the job queues where jobDate = &#63; and jobAction = &#63;.
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @return the matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findByJobDateAndJobAction(
		java.util.Date jobDate, java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the job queues where jobDate = &#63; and jobAction = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @param start the lower bound of the range of job queues
	* @param end the upper bound of the range of job queues (not inclusive)
	* @return the range of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findByJobDateAndJobAction(
		java.util.Date jobDate, java.lang.String jobAction, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the job queues where jobDate = &#63; and jobAction = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @param start the lower bound of the range of job queues
	* @param end the upper bound of the range of job queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findByJobDateAndJobAction(
		java.util.Date jobDate, java.lang.String jobAction, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first job queue in the ordered set where jobDate = &#63; and jobAction = &#63;.
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue findByJobDateAndJobAction_First(
		java.util.Date jobDate, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	/**
	* Returns the first job queue in the ordered set where jobDate = &#63; and jobAction = &#63;.
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue fetchByJobDateAndJobAction_First(
		java.util.Date jobDate, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last job queue in the ordered set where jobDate = &#63; and jobAction = &#63;.
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue findByJobDateAndJobAction_Last(
		java.util.Date jobDate, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	/**
	* Returns the last job queue in the ordered set where jobDate = &#63; and jobAction = &#63;.
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue fetchByJobDateAndJobAction_Last(
		java.util.Date jobDate, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the job queues before and after the current job queue in the ordered set where jobDate = &#63; and jobAction = &#63;.
	*
	* @param id the primary key of the current job queue
	* @param jobDate the job date
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue[] findByJobDateAndJobAction_PrevAndNext(
		long id, java.util.Date jobDate, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	/**
	* Returns all the job queues where jobUserEmailAddress = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @return the matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findByJobUserEmailAddress(
		java.lang.String jobUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the job queues where jobUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jobUserEmailAddress the job user email address
	* @param start the lower bound of the range of job queues
	* @param end the upper bound of the range of job queues (not inclusive)
	* @return the range of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findByJobUserEmailAddress(
		java.lang.String jobUserEmailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the job queues where jobUserEmailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jobUserEmailAddress the job user email address
	* @param start the lower bound of the range of job queues
	* @param end the upper bound of the range of job queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findByJobUserEmailAddress(
		java.lang.String jobUserEmailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first job queue in the ordered set where jobUserEmailAddress = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue findByJobUserEmailAddress_First(
		java.lang.String jobUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	/**
	* Returns the first job queue in the ordered set where jobUserEmailAddress = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue fetchByJobUserEmailAddress_First(
		java.lang.String jobUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last job queue in the ordered set where jobUserEmailAddress = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue findByJobUserEmailAddress_Last(
		java.lang.String jobUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	/**
	* Returns the last job queue in the ordered set where jobUserEmailAddress = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue fetchByJobUserEmailAddress_Last(
		java.lang.String jobUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the job queues before and after the current job queue in the ordered set where jobUserEmailAddress = &#63;.
	*
	* @param id the primary key of the current job queue
	* @param jobUserEmailAddress the job user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue[] findByJobUserEmailAddress_PrevAndNext(
		long id, java.lang.String jobUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	/**
	* Returns all the job queues where jobUserEmailAddress = &#63; and jobAction = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @return the matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findByJobUserEmailAddressAndJobAction(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the job queues where jobUserEmailAddress = &#63; and jobAction = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @param start the lower bound of the range of job queues
	* @param end the upper bound of the range of job queues (not inclusive)
	* @return the range of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findByJobUserEmailAddressAndJobAction(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the job queues where jobUserEmailAddress = &#63; and jobAction = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @param start the lower bound of the range of job queues
	* @param end the upper bound of the range of job queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findByJobUserEmailAddressAndJobAction(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first job queue in the ordered set where jobUserEmailAddress = &#63; and jobAction = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue findByJobUserEmailAddressAndJobAction_First(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	/**
	* Returns the first job queue in the ordered set where jobUserEmailAddress = &#63; and jobAction = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue fetchByJobUserEmailAddressAndJobAction_First(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last job queue in the ordered set where jobUserEmailAddress = &#63; and jobAction = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue findByJobUserEmailAddressAndJobAction_Last(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	/**
	* Returns the last job queue in the ordered set where jobUserEmailAddress = &#63; and jobAction = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue fetchByJobUserEmailAddressAndJobAction_Last(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the job queues before and after the current job queue in the ordered set where jobUserEmailAddress = &#63; and jobAction = &#63;.
	*
	* @param id the primary key of the current job queue
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.gnenc.yams.model.JobQueue[] findByJobUserEmailAddressAndJobAction_PrevAndNext(
		long id, java.lang.String jobUserEmailAddress,
		java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException;

	/**
	* Returns all the job queues.
	*
	* @return the job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the job queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of job queues
	* @param end the upper bound of the range of job queues (not inclusive)
	* @return the range of job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the job queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of job queues
	* @param end the upper bound of the range of job queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of job queues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.gnenc.yams.model.JobQueue> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the job queues where jobAction = &#63; from the database.
	*
	* @param jobAction the job action
	* @throws SystemException if a system exception occurred
	*/
	public void removeByJobAction(java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the job queues where jobDate = &#63; and jobAction = &#63; from the database.
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @throws SystemException if a system exception occurred
	*/
	public void removeByJobDateAndJobAction(java.util.Date jobDate,
		java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the job queues where jobUserEmailAddress = &#63; from the database.
	*
	* @param jobUserEmailAddress the job user email address
	* @throws SystemException if a system exception occurred
	*/
	public void removeByJobUserEmailAddress(
		java.lang.String jobUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the job queues where jobUserEmailAddress = &#63; and jobAction = &#63; from the database.
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @throws SystemException if a system exception occurred
	*/
	public void removeByJobUserEmailAddressAndJobAction(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the job queues from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of job queues where jobAction = &#63;.
	*
	* @param jobAction the job action
	* @return the number of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public int countByJobAction(java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of job queues where jobDate = &#63; and jobAction = &#63;.
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @return the number of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public int countByJobDateAndJobAction(java.util.Date jobDate,
		java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of job queues where jobUserEmailAddress = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @return the number of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public int countByJobUserEmailAddress(java.lang.String jobUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of job queues where jobUserEmailAddress = &#63; and jobAction = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @return the number of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public int countByJobUserEmailAddressAndJobAction(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of job queues.
	*
	* @return the number of job queues
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}