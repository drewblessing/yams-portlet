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
 * This class is a wrapper for {@link Permissions}.
 * </p>
 *
 * @author    Drew A. Blessing
 * @see       Permissions
 * @generated
 */
public class PermissionsWrapper implements Permissions,
	ModelWrapper<Permissions> {
	public PermissionsWrapper(Permissions permissions) {
		_permissions = permissions;
	}

	public Class<?> getModelClass() {
		return Permissions.class;
	}

	public String getModelClassName() {
		return Permissions.class.getName();
	}

	/**
	* Returns the primary key of this permissions.
	*
	* @return the primary key of this permissions
	*/
	public long getPrimaryKey() {
		return _permissions.getPrimaryKey();
	}

	/**
	* Sets the primary key of this permissions.
	*
	* @param primaryKey the primary key of this permissions
	*/
	public void setPrimaryKey(long primaryKey) {
		_permissions.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ID of this permissions.
	*
	* @return the ID of this permissions
	*/
	public long getId() {
		return _permissions.getId();
	}

	/**
	* Sets the ID of this permissions.
	*
	* @param id the ID of this permissions
	*/
	public void setId(long id) {
		_permissions.setId(id);
	}

	/**
	* Returns the company ID of this permissions.
	*
	* @return the company ID of this permissions
	*/
	public long getCompanyId() {
		return _permissions.getCompanyId();
	}

	/**
	* Sets the company ID of this permissions.
	*
	* @param companyId the company ID of this permissions
	*/
	public void setCompanyId(long companyId) {
		_permissions.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this permissions.
	*
	* @return the user ID of this permissions
	*/
	public long getUserId() {
		return _permissions.getUserId();
	}

	/**
	* Sets the user ID of this permissions.
	*
	* @param userId the user ID of this permissions
	*/
	public void setUserId(long userId) {
		_permissions.setUserId(userId);
	}

	/**
	* Returns the user uuid of this permissions.
	*
	* @return the user uuid of this permissions
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissions.getUserUuid();
	}

	/**
	* Sets the user uuid of this permissions.
	*
	* @param userUuid the user uuid of this permissions
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_permissions.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this permissions.
	*
	* @return the user name of this permissions
	*/
	public java.lang.String getUserName() {
		return _permissions.getUserName();
	}

	/**
	* Sets the user name of this permissions.
	*
	* @param userName the user name of this permissions
	*/
	public void setUserName(java.lang.String userName) {
		_permissions.setUserName(userName);
	}

	/**
	* Returns the create date of this permissions.
	*
	* @return the create date of this permissions
	*/
	public java.util.Date getCreateDate() {
		return _permissions.getCreateDate();
	}

	/**
	* Sets the create date of this permissions.
	*
	* @param createDate the create date of this permissions
	*/
	public void setCreateDate(java.util.Date createDate) {
		_permissions.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this permissions.
	*
	* @return the modified date of this permissions
	*/
	public java.util.Date getModifiedDate() {
		return _permissions.getModifiedDate();
	}

	/**
	* Sets the modified date of this permissions.
	*
	* @param modifiedDate the modified date of this permissions
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_permissions.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the email address of this permissions.
	*
	* @return the email address of this permissions
	*/
	public java.lang.String getEmailAddress() {
		return _permissions.getEmailAddress();
	}

	/**
	* Sets the email address of this permissions.
	*
	* @param emailAddress the email address of this permissions
	*/
	public void setEmailAddress(java.lang.String emailAddress) {
		_permissions.setEmailAddress(emailAddress);
	}

	/**
	* Returns the fqgn of this permissions.
	*
	* @return the fqgn of this permissions
	*/
	public java.lang.String getFqgn() {
		return _permissions.getFqgn();
	}

	/**
	* Sets the fqgn of this permissions.
	*
	* @param fqgn the fqgn of this permissions
	*/
	public void setFqgn(java.lang.String fqgn) {
		_permissions.setFqgn(fqgn);
	}

	/**
	* Returns the permissions of this permissions.
	*
	* @return the permissions of this permissions
	*/
	public long getPermissions() {
		return _permissions.getPermissions();
	}

	/**
	* Sets the permissions of this permissions.
	*
	* @param permissions the permissions of this permissions
	*/
	public void setPermissions(long permissions) {
		_permissions.setPermissions(permissions);
	}

	/**
	* Returns the permissions grantable of this permissions.
	*
	* @return the permissions grantable of this permissions
	*/
	public long getPermissionsGrantable() {
		return _permissions.getPermissionsGrantable();
	}

	/**
	* Sets the permissions grantable of this permissions.
	*
	* @param permissionsGrantable the permissions grantable of this permissions
	*/
	public void setPermissionsGrantable(long permissionsGrantable) {
		_permissions.setPermissionsGrantable(permissionsGrantable);
	}

	public boolean isNew() {
		return _permissions.isNew();
	}

	public void setNew(boolean n) {
		_permissions.setNew(n);
	}

	public boolean isCachedModel() {
		return _permissions.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_permissions.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _permissions.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _permissions.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_permissions.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _permissions.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_permissions.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new PermissionsWrapper((Permissions)_permissions.clone());
	}

	public int compareTo(Permissions permissions) {
		return _permissions.compareTo(permissions);
	}

	@Override
	public int hashCode() {
		return _permissions.hashCode();
	}

	public com.liferay.portal.model.CacheModel<Permissions> toCacheModel() {
		return _permissions.toCacheModel();
	}

	public Permissions toEscapedModel() {
		return new PermissionsWrapper(_permissions.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _permissions.toString();
	}

	public java.lang.String toXmlString() {
		return _permissions.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_permissions.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Permissions getWrappedPermissions() {
		return _permissions;
	}

	public Permissions getWrappedModel() {
		return _permissions;
	}

	public void resetOriginalValues() {
		_permissions.resetOriginalValues();
	}

	private Permissions _permissions;
}