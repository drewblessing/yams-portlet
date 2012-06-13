create table yams_Permissions (
	id_ LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	emailAddress VARCHAR(75) null,
	fqgn VARCHAR(75) null,
	permissions LONG,
	permissionsGrantable LONG
);

create table yams_PermissionsDefined (
	permissionKey VARCHAR(75) not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	bitLocation INTEGER,
	description VARCHAR(75) null,
	properName VARCHAR(75) null
);