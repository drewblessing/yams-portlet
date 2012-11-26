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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import org.gnenc.yams.model.ActionLogClp;
import org.gnenc.yams.model.JobQueueClp;
import org.gnenc.yams.model.PermissionsClp;
import org.gnenc.yams.model.PermissionsDefinedClp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpSerializer {
	public static String getServletContextName() {
		if (Validator.isNotNull(_servletContextName)) {
			return _servletContextName;
		}

		synchronized (ClpSerializer.class) {
			if (Validator.isNotNull(_servletContextName)) {
				return _servletContextName;
			}

			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Class<?> portletPropsClass = classLoader.loadClass(
						"com.liferay.util.portlet.PortletProps");

				Method getMethod = portletPropsClass.getMethod("get",
						new Class<?>[] { String.class });

				String portletPropsServletContextName = (String)getMethod.invoke(null,
						"yams-portlet-deployment-context");

				if (Validator.isNotNull(portletPropsServletContextName)) {
					_servletContextName = portletPropsServletContextName;
				}
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to locate deployment context from portlet properties");
				}
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"yams-portlet-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Throwable t) {
					if (_log.isInfoEnabled()) {
						_log.info(
							"Unable to locate deployment context from portal properties");
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "yams-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(ActionLogClp.class.getName())) {
			return translateInputActionLog(oldModel);
		}

		if (oldModelClassName.equals(JobQueueClp.class.getName())) {
			return translateInputJobQueue(oldModel);
		}

		if (oldModelClassName.equals(PermissionsClp.class.getName())) {
			return translateInputPermissions(oldModel);
		}

		if (oldModelClassName.equals(PermissionsDefinedClp.class.getName())) {
			return translateInputPermissionsDefined(oldModel);
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInputActionLog(BaseModel<?> oldModel) {
		ActionLogClp oldClpModel = (ActionLogClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getActionLogRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputJobQueue(BaseModel<?> oldModel) {
		JobQueueClp oldClpModel = (JobQueueClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getJobQueueRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputPermissions(BaseModel<?> oldModel) {
		PermissionsClp oldClpModel = (PermissionsClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getPermissionsRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputPermissionsDefined(BaseModel<?> oldModel) {
		PermissionsDefinedClp oldClpModel = (PermissionsDefinedClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getPermissionsDefinedRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals("org.gnenc.yams.model.impl.ActionLogImpl")) {
			return translateOutputActionLog(oldModel);
		}

		if (oldModelClassName.equals("org.gnenc.yams.model.impl.JobQueueImpl")) {
			return translateOutputJobQueue(oldModel);
		}

		if (oldModelClassName.equals(
					"org.gnenc.yams.model.impl.PermissionsImpl")) {
			return translateOutputPermissions(oldModel);
		}

		if (oldModelClassName.equals(
					"org.gnenc.yams.model.impl.PermissionsDefinedImpl")) {
			return translateOutputPermissionsDefined(oldModel);
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Throwable translateThrowable(Throwable throwable) {
		if (_useReflectionToTranslateThrowable) {
			try {
				UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(unsyncByteArrayOutputStream);

				objectOutputStream.writeObject(throwable);

				objectOutputStream.flush();
				objectOutputStream.close();

				UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(unsyncByteArrayOutputStream.unsafeGetByteArray(),
						0, unsyncByteArrayOutputStream.size());

				Thread currentThread = Thread.currentThread();

				ClassLoader contextClassLoader = currentThread.getContextClassLoader();

				ObjectInputStream objectInputStream = new ClassLoaderObjectInputStream(unsyncByteArrayInputStream,
						contextClassLoader);

				throwable = (Throwable)objectInputStream.readObject();

				objectInputStream.close();

				return throwable;
			}
			catch (SecurityException se) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (Throwable throwable2) {
				_log.error(throwable2, throwable2);

				return throwable2;
			}
		}

		Class<?> clazz = throwable.getClass();

		String className = clazz.getName();

		if (className.equals(PortalException.class.getName())) {
			return new PortalException();
		}

		if (className.equals(SystemException.class.getName())) {
			return new SystemException();
		}

		if (className.equals("org.gnenc.yams.NoSuchActionLogException")) {
			return new org.gnenc.yams.NoSuchActionLogException();
		}

		if (className.equals("org.gnenc.yams.NoSuchJobQueueException")) {
			return new org.gnenc.yams.NoSuchJobQueueException();
		}

		if (className.equals("org.gnenc.yams.NoSuchPermissionsException")) {
			return new org.gnenc.yams.NoSuchPermissionsException();
		}

		if (className.equals("org.gnenc.yams.NoSuchPermissionsDefinedException")) {
			return new org.gnenc.yams.NoSuchPermissionsDefinedException();
		}

		return throwable;
	}

	public static Object translateOutputActionLog(BaseModel<?> oldModel) {
		ActionLogClp newModel = new ActionLogClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setActionLogRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputJobQueue(BaseModel<?> oldModel) {
		JobQueueClp newModel = new JobQueueClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setJobQueueRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputPermissions(BaseModel<?> oldModel) {
		PermissionsClp newModel = new PermissionsClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setPermissionsRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputPermissionsDefined(
		BaseModel<?> oldModel) {
		PermissionsDefinedClp newModel = new PermissionsDefinedClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setPermissionsDefinedRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}