package org.gnenc.yams.model;

/**
 * Based on the AccountStatus enumeration
 * written by Jeshurun Daniel.
 * 
 * @author Drew A. Blessing
 *
 */
public enum AccountStatus {
		ACTIVE, //Active and able to login
		INACTIVE, //Inactive and user cannot login
		CLOSED, //Closed and will not be opened again (deleted essentially)
}
