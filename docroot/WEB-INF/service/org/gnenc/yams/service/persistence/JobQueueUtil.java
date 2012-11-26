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

import org.gnenc.yams.model.JobQueue;

import java.util.List;

/**
 * The persistence utility for the job queue service. This utility wraps {@link JobQueuePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Drew A. Blessing
 * @see JobQueuePersistence
 * @see JobQueuePersistenceImpl
 * @generated
 */
public class JobQueueUtil {
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
	public static void clearCache(JobQueue jobQueue) {
		getPersistence().clearCache(jobQueue);
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
	public static List<JobQueue> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JobQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JobQueue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static JobQueue update(JobQueue jobQueue, boolean merge)
		throws SystemException {
		return getPersistence().update(jobQueue, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static JobQueue update(JobQueue jobQueue, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(jobQueue, merge, serviceContext);
	}

	/**
	* Caches the job queue in the entity cache if it is enabled.
	*
	* @param jobQueue the job queue
	*/
	public static void cacheResult(org.gnenc.yams.model.JobQueue jobQueue) {
		getPersistence().cacheResult(jobQueue);
	}

	/**
	* Caches the job queues in the entity cache if it is enabled.
	*
	* @param jobQueues the job queues
	*/
	public static void cacheResult(
		java.util.List<org.gnenc.yams.model.JobQueue> jobQueues) {
		getPersistence().cacheResult(jobQueues);
	}

	/**
	* Creates a new job queue with the primary key. Does not add the job queue to the database.
	*
	* @param id the primary key for the new job queue
	* @return the new job queue
	*/
	public static org.gnenc.yams.model.JobQueue create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the job queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the job queue
	* @return the job queue that was removed
	* @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue remove(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence().remove(id);
	}

	public static org.gnenc.yams.model.JobQueue updateImpl(
		org.gnenc.yams.model.JobQueue jobQueue, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(jobQueue, merge);
	}

	/**
	* Returns the job queue with the primary key or throws a {@link org.gnenc.yams.NoSuchJobQueueException} if it could not be found.
	*
	* @param id the primary key of the job queue
	* @return the job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a job queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue findByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the job queue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the job queue
	* @return the job queue, or <code>null</code> if a job queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue fetchByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	* Returns all the job queues where jobAction = &#63;.
	*
	* @param jobAction the job action
	* @return the matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.JobQueue> findByJobAction(
		java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJobAction(jobAction);
	}

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
	public static java.util.List<org.gnenc.yams.model.JobQueue> findByJobAction(
		java.lang.String jobAction, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJobAction(jobAction, start, end);
	}

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
	public static java.util.List<org.gnenc.yams.model.JobQueue> findByJobAction(
		java.lang.String jobAction, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJobAction(jobAction, start, end, orderByComparator);
	}

	/**
	* Returns the first job queue in the ordered set where jobAction = &#63;.
	*
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue findByJobAction_First(
		java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence()
				   .findByJobAction_First(jobAction, orderByComparator);
	}

	/**
	* Returns the first job queue in the ordered set where jobAction = &#63;.
	*
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue fetchByJobAction_First(
		java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJobAction_First(jobAction, orderByComparator);
	}

	/**
	* Returns the last job queue in the ordered set where jobAction = &#63;.
	*
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue findByJobAction_Last(
		java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence()
				   .findByJobAction_Last(jobAction, orderByComparator);
	}

	/**
	* Returns the last job queue in the ordered set where jobAction = &#63;.
	*
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue fetchByJobAction_Last(
		java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJobAction_Last(jobAction, orderByComparator);
	}

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
	public static org.gnenc.yams.model.JobQueue[] findByJobAction_PrevAndNext(
		long id, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence()
				   .findByJobAction_PrevAndNext(id, jobAction, orderByComparator);
	}

	/**
	* Returns all the job queues where jobDate = &#63; and jobAction = &#63;.
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @return the matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.JobQueue> findByJobDateAndJobAction(
		java.util.Date jobDate, java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJobDateAndJobAction(jobDate, jobAction);
	}

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
	public static java.util.List<org.gnenc.yams.model.JobQueue> findByJobDateAndJobAction(
		java.util.Date jobDate, java.lang.String jobAction, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJobDateAndJobAction(jobDate, jobAction, start, end);
	}

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
	public static java.util.List<org.gnenc.yams.model.JobQueue> findByJobDateAndJobAction(
		java.util.Date jobDate, java.lang.String jobAction, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJobDateAndJobAction(jobDate, jobAction, start, end,
			orderByComparator);
	}

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
	public static org.gnenc.yams.model.JobQueue findByJobDateAndJobAction_First(
		java.util.Date jobDate, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence()
				   .findByJobDateAndJobAction_First(jobDate, jobAction,
			orderByComparator);
	}

	/**
	* Returns the first job queue in the ordered set where jobDate = &#63; and jobAction = &#63;.
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue fetchByJobDateAndJobAction_First(
		java.util.Date jobDate, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJobDateAndJobAction_First(jobDate, jobAction,
			orderByComparator);
	}

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
	public static org.gnenc.yams.model.JobQueue findByJobDateAndJobAction_Last(
		java.util.Date jobDate, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence()
				   .findByJobDateAndJobAction_Last(jobDate, jobAction,
			orderByComparator);
	}

	/**
	* Returns the last job queue in the ordered set where jobDate = &#63; and jobAction = &#63;.
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue fetchByJobDateAndJobAction_Last(
		java.util.Date jobDate, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJobDateAndJobAction_Last(jobDate, jobAction,
			orderByComparator);
	}

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
	public static org.gnenc.yams.model.JobQueue[] findByJobDateAndJobAction_PrevAndNext(
		long id, java.util.Date jobDate, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence()
				   .findByJobDateAndJobAction_PrevAndNext(id, jobDate,
			jobAction, orderByComparator);
	}

	/**
	* Returns all the job queues where jobUserEmailAddress = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @return the matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.JobQueue> findByJobUserEmailAddress(
		java.lang.String jobUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJobUserEmailAddress(jobUserEmailAddress);
	}

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
	public static java.util.List<org.gnenc.yams.model.JobQueue> findByJobUserEmailAddress(
		java.lang.String jobUserEmailAddress, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJobUserEmailAddress(jobUserEmailAddress, start, end);
	}

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
	public static java.util.List<org.gnenc.yams.model.JobQueue> findByJobUserEmailAddress(
		java.lang.String jobUserEmailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJobUserEmailAddress(jobUserEmailAddress, start, end,
			orderByComparator);
	}

	/**
	* Returns the first job queue in the ordered set where jobUserEmailAddress = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue findByJobUserEmailAddress_First(
		java.lang.String jobUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence()
				   .findByJobUserEmailAddress_First(jobUserEmailAddress,
			orderByComparator);
	}

	/**
	* Returns the first job queue in the ordered set where jobUserEmailAddress = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue fetchByJobUserEmailAddress_First(
		java.lang.String jobUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJobUserEmailAddress_First(jobUserEmailAddress,
			orderByComparator);
	}

	/**
	* Returns the last job queue in the ordered set where jobUserEmailAddress = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue
	* @throws org.gnenc.yams.NoSuchJobQueueException if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue findByJobUserEmailAddress_Last(
		java.lang.String jobUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence()
				   .findByJobUserEmailAddress_Last(jobUserEmailAddress,
			orderByComparator);
	}

	/**
	* Returns the last job queue in the ordered set where jobUserEmailAddress = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue fetchByJobUserEmailAddress_Last(
		java.lang.String jobUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJobUserEmailAddress_Last(jobUserEmailAddress,
			orderByComparator);
	}

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
	public static org.gnenc.yams.model.JobQueue[] findByJobUserEmailAddress_PrevAndNext(
		long id, java.lang.String jobUserEmailAddress,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence()
				   .findByJobUserEmailAddress_PrevAndNext(id,
			jobUserEmailAddress, orderByComparator);
	}

	/**
	* Returns all the job queues where jobUserEmailAddress = &#63; and jobAction = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @return the matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.JobQueue> findByJobUserEmailAddressAndJobAction(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJobUserEmailAddressAndJobAction(jobUserEmailAddress,
			jobAction);
	}

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
	public static java.util.List<org.gnenc.yams.model.JobQueue> findByJobUserEmailAddressAndJobAction(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJobUserEmailAddressAndJobAction(jobUserEmailAddress,
			jobAction, start, end);
	}

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
	public static java.util.List<org.gnenc.yams.model.JobQueue> findByJobUserEmailAddressAndJobAction(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJobUserEmailAddressAndJobAction(jobUserEmailAddress,
			jobAction, start, end, orderByComparator);
	}

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
	public static org.gnenc.yams.model.JobQueue findByJobUserEmailAddressAndJobAction_First(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence()
				   .findByJobUserEmailAddressAndJobAction_First(jobUserEmailAddress,
			jobAction, orderByComparator);
	}

	/**
	* Returns the first job queue in the ordered set where jobUserEmailAddress = &#63; and jobAction = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue fetchByJobUserEmailAddressAndJobAction_First(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJobUserEmailAddressAndJobAction_First(jobUserEmailAddress,
			jobAction, orderByComparator);
	}

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
	public static org.gnenc.yams.model.JobQueue findByJobUserEmailAddressAndJobAction_Last(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence()
				   .findByJobUserEmailAddressAndJobAction_Last(jobUserEmailAddress,
			jobAction, orderByComparator);
	}

	/**
	* Returns the last job queue in the ordered set where jobUserEmailAddress = &#63; and jobAction = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job queue, or <code>null</code> if a matching job queue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue fetchByJobUserEmailAddressAndJobAction_Last(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJobUserEmailAddressAndJobAction_Last(jobUserEmailAddress,
			jobAction, orderByComparator);
	}

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
	public static org.gnenc.yams.model.JobQueue[] findByJobUserEmailAddressAndJobAction_PrevAndNext(
		long id, java.lang.String jobUserEmailAddress,
		java.lang.String jobAction,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.gnenc.yams.NoSuchJobQueueException {
		return getPersistence()
				   .findByJobUserEmailAddressAndJobAction_PrevAndNext(id,
			jobUserEmailAddress, jobAction, orderByComparator);
	}

	/**
	* Returns all the job queues.
	*
	* @return the job queues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.gnenc.yams.model.JobQueue> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<org.gnenc.yams.model.JobQueue> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<org.gnenc.yams.model.JobQueue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the job queues where jobAction = &#63; from the database.
	*
	* @param jobAction the job action
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByJobAction(java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByJobAction(jobAction);
	}

	/**
	* Removes all the job queues where jobDate = &#63; and jobAction = &#63; from the database.
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByJobDateAndJobAction(java.util.Date jobDate,
		java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByJobDateAndJobAction(jobDate, jobAction);
	}

	/**
	* Removes all the job queues where jobUserEmailAddress = &#63; from the database.
	*
	* @param jobUserEmailAddress the job user email address
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByJobUserEmailAddress(
		java.lang.String jobUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByJobUserEmailAddress(jobUserEmailAddress);
	}

	/**
	* Removes all the job queues where jobUserEmailAddress = &#63; and jobAction = &#63; from the database.
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByJobUserEmailAddressAndJobAction(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByJobUserEmailAddressAndJobAction(jobUserEmailAddress,
			jobAction);
	}

	/**
	* Removes all the job queues from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of job queues where jobAction = &#63;.
	*
	* @param jobAction the job action
	* @return the number of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByJobAction(java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByJobAction(jobAction);
	}

	/**
	* Returns the number of job queues where jobDate = &#63; and jobAction = &#63;.
	*
	* @param jobDate the job date
	* @param jobAction the job action
	* @return the number of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByJobDateAndJobAction(java.util.Date jobDate,
		java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByJobDateAndJobAction(jobDate, jobAction);
	}

	/**
	* Returns the number of job queues where jobUserEmailAddress = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @return the number of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByJobUserEmailAddress(
		java.lang.String jobUserEmailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByJobUserEmailAddress(jobUserEmailAddress);
	}

	/**
	* Returns the number of job queues where jobUserEmailAddress = &#63; and jobAction = &#63;.
	*
	* @param jobUserEmailAddress the job user email address
	* @param jobAction the job action
	* @return the number of matching job queues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByJobUserEmailAddressAndJobAction(
		java.lang.String jobUserEmailAddress, java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByJobUserEmailAddressAndJobAction(jobUserEmailAddress,
			jobAction);
	}

	/**
	* Returns the number of job queues.
	*
	* @return the number of job queues
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static JobQueuePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (JobQueuePersistence)PortletBeanLocatorUtil.locate(org.gnenc.yams.service.ClpSerializer.getServletContextName(),
					JobQueuePersistence.class.getName());

			ReferenceRegistry.registerReference(JobQueueUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(JobQueuePersistence persistence) {
	}

	private static JobQueuePersistence _persistence;
}