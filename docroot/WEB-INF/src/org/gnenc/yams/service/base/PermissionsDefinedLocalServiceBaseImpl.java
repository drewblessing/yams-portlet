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

package org.gnenc.yams.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import org.gnenc.yams.model.PermissionsDefined;
import org.gnenc.yams.service.PermissionsDefinedLocalService;
import org.gnenc.yams.service.PermissionsLocalService;
import org.gnenc.yams.service.persistence.PermissionsDefinedPersistence;
import org.gnenc.yams.service.persistence.PermissionsPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the permissions defined local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link org.gnenc.yams.service.impl.PermissionsDefinedLocalServiceImpl}.
 * </p>
 *
 * @author Drew A. Blessing
 * @see org.gnenc.yams.service.impl.PermissionsDefinedLocalServiceImpl
 * @see org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil
 * @generated
 */
public abstract class PermissionsDefinedLocalServiceBaseImpl
	implements PermissionsDefinedLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link org.gnenc.yams.service.PermissionsDefinedLocalServiceUtil} to access the permissions defined local service.
	 */

	/**
	 * Adds the permissions defined to the database. Also notifies the appropriate model listeners.
	 *
	 * @param permissionsDefined the permissions defined
	 * @return the permissions defined that was added
	 * @throws SystemException if a system exception occurred
	 */
	public PermissionsDefined addPermissionsDefined(
		PermissionsDefined permissionsDefined) throws SystemException {
		permissionsDefined.setNew(true);

		permissionsDefined = permissionsDefinedPersistence.update(permissionsDefined,
				false);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.reindex(permissionsDefined);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}

		return permissionsDefined;
	}

	/**
	 * Creates a new permissions defined with the primary key. Does not add the permissions defined to the database.
	 *
	 * @param permissionKey the primary key for the new permissions defined
	 * @return the new permissions defined
	 */
	public PermissionsDefined createPermissionsDefined(String permissionKey) {
		return permissionsDefinedPersistence.create(permissionKey);
	}

	/**
	 * Deletes the permissions defined with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param permissionKey the primary key of the permissions defined
	 * @throws PortalException if a permissions defined with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deletePermissionsDefined(String permissionKey)
		throws PortalException, SystemException {
		PermissionsDefined permissionsDefined = permissionsDefinedPersistence.remove(permissionKey);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.delete(permissionsDefined);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}
	}

	/**
	 * Deletes the permissions defined from the database. Also notifies the appropriate model listeners.
	 *
	 * @param permissionsDefined the permissions defined
	 * @throws SystemException if a system exception occurred
	 */
	public void deletePermissionsDefined(PermissionsDefined permissionsDefined)
		throws SystemException {
		permissionsDefinedPersistence.remove(permissionsDefined);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.delete(permissionsDefined);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return permissionsDefinedPersistence.findWithDynamicQuery(dynamicQuery);
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
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return permissionsDefinedPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
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
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return permissionsDefinedPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return permissionsDefinedPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public PermissionsDefined fetchPermissionsDefined(String permissionKey)
		throws SystemException {
		return permissionsDefinedPersistence.fetchByPrimaryKey(permissionKey);
	}

	/**
	 * Returns the permissions defined with the primary key.
	 *
	 * @param permissionKey the primary key of the permissions defined
	 * @return the permissions defined
	 * @throws PortalException if a permissions defined with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PermissionsDefined getPermissionsDefined(String permissionKey)
		throws PortalException, SystemException {
		return permissionsDefinedPersistence.findByPrimaryKey(permissionKey);
	}

	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return permissionsDefinedPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the permissions defineds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of permissions defineds
	 * @param end the upper bound of the range of permissions defineds (not inclusive)
	 * @return the range of permissions defineds
	 * @throws SystemException if a system exception occurred
	 */
	public List<PermissionsDefined> getPermissionsDefineds(int start, int end)
		throws SystemException {
		return permissionsDefinedPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of permissions defineds.
	 *
	 * @return the number of permissions defineds
	 * @throws SystemException if a system exception occurred
	 */
	public int getPermissionsDefinedsCount() throws SystemException {
		return permissionsDefinedPersistence.countAll();
	}

	/**
	 * Updates the permissions defined in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param permissionsDefined the permissions defined
	 * @return the permissions defined that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public PermissionsDefined updatePermissionsDefined(
		PermissionsDefined permissionsDefined) throws SystemException {
		return updatePermissionsDefined(permissionsDefined, true);
	}

	/**
	 * Updates the permissions defined in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param permissionsDefined the permissions defined
	 * @param merge whether to merge the permissions defined with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the permissions defined that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public PermissionsDefined updatePermissionsDefined(
		PermissionsDefined permissionsDefined, boolean merge)
		throws SystemException {
		permissionsDefined.setNew(false);

		permissionsDefined = permissionsDefinedPersistence.update(permissionsDefined,
				merge);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.reindex(permissionsDefined);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}

		return permissionsDefined;
	}

	/**
	 * Returns the permissions local service.
	 *
	 * @return the permissions local service
	 */
	public PermissionsLocalService getPermissionsLocalService() {
		return permissionsLocalService;
	}

	/**
	 * Sets the permissions local service.
	 *
	 * @param permissionsLocalService the permissions local service
	 */
	public void setPermissionsLocalService(
		PermissionsLocalService permissionsLocalService) {
		this.permissionsLocalService = permissionsLocalService;
	}

	/**
	 * Returns the permissions persistence.
	 *
	 * @return the permissions persistence
	 */
	public PermissionsPersistence getPermissionsPersistence() {
		return permissionsPersistence;
	}

	/**
	 * Sets the permissions persistence.
	 *
	 * @param permissionsPersistence the permissions persistence
	 */
	public void setPermissionsPersistence(
		PermissionsPersistence permissionsPersistence) {
		this.permissionsPersistence = permissionsPersistence;
	}

	/**
	 * Returns the permissions defined local service.
	 *
	 * @return the permissions defined local service
	 */
	public PermissionsDefinedLocalService getPermissionsDefinedLocalService() {
		return permissionsDefinedLocalService;
	}

	/**
	 * Sets the permissions defined local service.
	 *
	 * @param permissionsDefinedLocalService the permissions defined local service
	 */
	public void setPermissionsDefinedLocalService(
		PermissionsDefinedLocalService permissionsDefinedLocalService) {
		this.permissionsDefinedLocalService = permissionsDefinedLocalService;
	}

	/**
	 * Returns the permissions defined persistence.
	 *
	 * @return the permissions defined persistence
	 */
	public PermissionsDefinedPersistence getPermissionsDefinedPersistence() {
		return permissionsDefinedPersistence;
	}

	/**
	 * Sets the permissions defined persistence.
	 *
	 * @param permissionsDefinedPersistence the permissions defined persistence
	 */
	public void setPermissionsDefinedPersistence(
		PermissionsDefinedPersistence permissionsDefinedPersistence) {
		this.permissionsDefinedPersistence = permissionsDefinedPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		PersistedModelLocalServiceRegistryUtil.register("org.gnenc.yams.model.PermissionsDefined",
			permissionsDefinedLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"org.gnenc.yams.model.PermissionsDefined");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	protected Class<?> getModelClass() {
		return PermissionsDefined.class;
	}

	protected String getModelClassName() {
		return PermissionsDefined.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = permissionsDefinedPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = PermissionsLocalService.class)
	protected PermissionsLocalService permissionsLocalService;
	@BeanReference(type = PermissionsPersistence.class)
	protected PermissionsPersistence permissionsPersistence;
	@BeanReference(type = PermissionsDefinedLocalService.class)
	protected PermissionsDefinedLocalService permissionsDefinedLocalService;
	@BeanReference(type = PermissionsDefinedPersistence.class)
	protected PermissionsDefinedPersistence permissionsDefinedPersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static Log _log = LogFactoryUtil.getLog(PermissionsDefinedLocalServiceBaseImpl.class);
	private String _beanIdentifier;
}