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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the job queue local service. This utility wraps {@link org.gnenc.yams.service.impl.JobQueueLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Drew A. Blessing
 * @see JobQueueLocalService
 * @see org.gnenc.yams.service.base.JobQueueLocalServiceBaseImpl
 * @see org.gnenc.yams.service.impl.JobQueueLocalServiceImpl
 * @generated
 */
public class JobQueueLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.gnenc.yams.service.impl.JobQueueLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the job queue to the database. Also notifies the appropriate model listeners.
	*
	* @param jobQueue the job queue
	* @return the job queue that was added
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue addJobQueue(
		org.gnenc.yams.model.JobQueue jobQueue)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addJobQueue(jobQueue);
	}

	/**
	* Creates a new job queue with the primary key. Does not add the job queue to the database.
	*
	* @param id the primary key for the new job queue
	* @return the new job queue
	*/
	public static org.gnenc.yams.model.JobQueue createJobQueue(long id) {
		return getService().createJobQueue(id);
	}

	/**
	* Deletes the job queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the job queue
	* @return the job queue that was removed
	* @throws PortalException if a job queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue deleteJobQueue(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteJobQueue(id);
	}

	/**
	* Deletes the job queue from the database. Also notifies the appropriate model listeners.
	*
	* @param jobQueue the job queue
	* @return the job queue that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue deleteJobQueue(
		org.gnenc.yams.model.JobQueue jobQueue)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteJobQueue(jobQueue);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static org.gnenc.yams.model.JobQueue fetchJobQueue(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchJobQueue(id);
	}

	/**
	* Returns the job queue with the primary key.
	*
	* @param id the primary key of the job queue
	* @return the job queue
	* @throws PortalException if a job queue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue getJobQueue(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getJobQueue(id);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<org.gnenc.yams.model.JobQueue> getJobQueues(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJobQueues(start, end);
	}

	/**
	* Returns the number of job queues.
	*
	* @return the number of job queues
	* @throws SystemException if a system exception occurred
	*/
	public static int getJobQueuesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJobQueuesCount();
	}

	/**
	* Updates the job queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jobQueue the job queue
	* @return the job queue that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue updateJobQueue(
		org.gnenc.yams.model.JobQueue jobQueue)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateJobQueue(jobQueue);
	}

	/**
	* Updates the job queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jobQueue the job queue
	* @param merge whether to merge the job queue with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the job queue that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.gnenc.yams.model.JobQueue updateJobQueue(
		org.gnenc.yams.model.JobQueue jobQueue, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateJobQueue(jobQueue, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static org.gnenc.yams.model.JobQueue addJob(long callingUserId,
		java.lang.String email, java.lang.String fullName,
		java.lang.String jobDescription, java.lang.String jobAction,
		java.util.Date jobDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addJob(callingUserId, email, fullName, jobDescription,
			jobAction, jobDate);
	}

	public static java.util.List<org.gnenc.yams.model.JobQueue> getByJobDateAndJobAction(
		java.util.Date date, java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getByJobDateAndJobAction(date, jobAction);
	}

	public static java.util.List<org.gnenc.yams.model.JobQueue> getByEmailAddressAndJobAction(
		java.lang.String email, java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getByEmailAddressAndJobAction(email, jobAction);
	}

	public static void deleteJobByEmailAddressAndJobAction(
		java.lang.String email, java.lang.String jobAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteJobByEmailAddressAndJobAction(email, jobAction);
	}

	public static void clearService() {
		_service = null;
	}

	public static JobQueueLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					JobQueueLocalService.class.getName());

			if (invokableLocalService instanceof JobQueueLocalService) {
				_service = (JobQueueLocalService)invokableLocalService;
			}
			else {
				_service = new JobQueueLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(JobQueueLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(JobQueueLocalService service) {
	}

	private static JobQueueLocalService _service;
}