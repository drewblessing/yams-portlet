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
 * This class is a wrapper for {@link PermissionsDefined}.
 * </p>
 *
 * @author    Drew A. Blessing
 * @see       PermissionsDefined
 * @generated
 */
public class PermissionsDefinedWrapper implements PermissionsDefined,
	ModelWrapper<PermissionsDefined> {
	public PermissionsDefinedWrapper(PermissionsDefined permissionsDefined) {
		_permissionsDefined = permissionsDefined;
	}

	public Class<?> getModelClass() {
		return PermissionsDefined.class;
	}

	public String getModelClassName() {
		return PermissionsDefined.class.getName();
	}

	/**
	* Returns the primary key of this permissions defined.
	*
	* @return the primary key of this permissions defined
	*/
	public java.lang.String getPrimaryKey() {
		return _permissionsDefined.getPrimaryKey();
	}

	/**
	* Sets the primary key of this permissions defined.
	*
	* @param primaryKey the primary key of this permissions defined
	*/
	public void setPrimaryKey(java.lang.String primaryKey) {
		_permissionsDefined.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the permission key of this permissions defined.
	*
	* @return the permission key of this permissions defined
	*/
	public java.lang.String getPermissionKey() {
		return _permissionsDefined.getPermissionKey();
	}

	/**
	* Sets the permission key of this permissions defined.
	*
	* @param permissionKey the permission key of this permissions defined
	*/
	public void setPermissionKey(java.lang.String permissionKey) {
		_permissionsDefined.setPermissionKey(permissionKey);
	}

	/**
	* Returns the company ID of this permissions defined.
	*
	* @return the company ID of this permissions defined
	*/
	public long getCompanyId() {
		return _permissionsDefined.getCompanyId();
	}

	/**
	* Sets the company ID of this permissions defined.
	*
	* @param companyId the company ID of this permissions defined
	*/
	public void setCompanyId(long companyId) {
		_permissionsDefined.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this permissions defined.
	*
	* @return the user ID of this permissions defined
	*/
	public long getUserId() {
		return _permissionsDefined.getUserId();
	}

	/**
	* Sets the user ID of this permissions defined.
	*
	* @param userId the user ID of this permissions defined
	*/
	public void setUserId(long userId) {
		_permissionsDefined.setUserId(userId);
	}

	/**
	* Returns the user uuid of this permissions defined.
	*
	* @return the user uuid of this permissions defined
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _permissionsDefined.getUserUuid();
	}

	/**
	* Sets the user uuid of this permissions defined.
	*
	* @param userUuid the user uuid of this permissions defined
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_permissionsDefined.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this permissions defined.
	*
	* @return the user name of this permissions defined
	*/
	public java.lang.String getUserName() {
		return _permissionsDefined.getUserName();
	}

	/**
	* Sets the user name of this permissions defined.
	*
	* @param userName the user name of this permissions defined
	*/
	public void setUserName(java.lang.String userName) {
		_permissionsDefined.setUserName(userName);
	}

	/**
	* Returns the create date of this permissions defined.
	*
	* @return the create date of this permissions defined
	*/
	public java.util.Date getCreateDate() {
		return _permissionsDefined.getCreateDate();
	}

	/**
	* Sets the create date of this permissions defined.
	*
	* @param createDate the create date of this permissions defined
	*/
	public void setCreateDate(java.util.Date createDate) {
		_permissionsDefined.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this permissions defined.
	*
	* @return the modified date of this permissions defined
	*/
	public java.util.Date getModifiedDate() {
		return _permissionsDefined.getModifiedDate();
	}

	/**
	* Sets the modified date of this permissions defined.
	*
	* @param modifiedDate the modified date of this permissions defined
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_permissionsDefined.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the bit location of this permissions defined.
	*
	* @return the bit location of this permissions defined
	*/
	public int getBitLocation() {
		return _permissionsDefined.getBitLocation();
	}

	/**
	* Sets the bit location of this permissions defined.
	*
	* @param bitLocation the bit location of this permissions defined
	*/
	public void setBitLocation(int bitLocation) {
		_permissionsDefined.setBitLocation(bitLocation);
	}

	/**
	* Returns the description of this permissions defined.
	*
	* @return the description of this permissions defined
	*/
	public java.lang.String getDescription() {
		return _permissionsDefined.getDescription();
	}

	/**
	* Sets the description of this permissions defined.
	*
	* @param description the description of this permissions defined
	*/
	public void setDescription(java.lang.String description) {
		_permissionsDefined.setDescription(description);
	}

	/**
	* Returns the proper name of this permissions defined.
	*
	* @return the proper name of this permissions defined
	*/
	public java.lang.String getProperName() {
		return _permissionsDefined.getProperName();
	}

	/**
	* Sets the proper name of this permissions defined.
	*
	* @param properName the proper name of this permissions defined
	*/
	public void setProperName(java.lang.String properName) {
		_permissionsDefined.setProperName(properName);
	}

	public boolean isNew() {
		return _permissionsDefined.isNew();
	}

	public void setNew(boolean n) {
		_permissionsDefined.setNew(n);
	}

	public boolean isCachedModel() {
		return _permissionsDefined.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_permissionsDefined.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _permissionsDefined.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _permissionsDefined.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_permissionsDefined.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _permissionsDefined.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_permissionsDefined.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new PermissionsDefinedWrapper((PermissionsDefined)_permissionsDefined.clone());
	}

	public int compareTo(PermissionsDefined permissionsDefined) {
		return _permissionsDefined.compareTo(permissionsDefined);
	}

	@Override
	public int hashCode() {
		return _permissionsDefined.hashCode();
	}

	public com.liferay.portal.model.CacheModel<PermissionsDefined> toCacheModel() {
		return _permissionsDefined.toCacheModel();
	}

	public PermissionsDefined toEscapedModel() {
		return new PermissionsDefinedWrapper(_permissionsDefined.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _permissionsDefined.toString();
	}

	public java.lang.String toXmlString() {
		return _permissionsDefined.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_permissionsDefined.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public PermissionsDefined getWrappedPermissionsDefined() {
		return _permissionsDefined;
	}

	public PermissionsDefined getWrappedModel() {
		return _permissionsDefined;
	}

	public void resetOriginalValues() {
		_permissionsDefined.resetOriginalValues();
	}

	private PermissionsDefined _permissionsDefined;
}