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

import com.liferay.portal.kernel.exception.SystemException;

import org.gnenc.yams.model.JobQueue;
import org.gnenc.yams.service.JobQueueLocalServiceUtil;

/**
 * The extended model base implementation for the JobQueue service. Represents a row in the &quot;yams_JobQueue&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link JobQueueImpl}.
 * </p>
 *
 * @author Drew A. Blessing
 * @see JobQueueImpl
 * @see org.gnenc.yams.model.JobQueue
 * @generated
 */
public abstract class JobQueueBaseImpl extends JobQueueModelImpl
	implements JobQueue {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a job queue model instance should use the {@link JobQueue} interface instead.
	 */
	public void persist() throws SystemException {
		if (this.isNew()) {
			JobQueueLocalServiceUtil.addJobQueue(this);
		}
		else {
			JobQueueLocalServiceUtil.updateJobQueue(this);
		}
	}
}