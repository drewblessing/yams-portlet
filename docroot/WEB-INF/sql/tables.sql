create table yams_ActionLog (
	id_ LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	modifiedDate DATE null,
	userEmailAddress VARCHAR(75) null,
	modifiedUserId LONG,
	modifiedUserName VARCHAR(75) null,
	modifiedUserEmailAddress VARCHAR(75) null,
	modifiedDescription VARCHAR(75) null,
	modifiedFqgn VARCHAR(75) null
);

create table yams_JobQueue (
	id_ LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	modifiedDate DATE null,
	userEmailAddress VARCHAR(75) null,
	jobUserId LONG,
	jobUserName VARCHAR(75) null,
	jobUserEmailAddress VARCHAR(75) null,
	jobDescription VARCHAR(75) null,
	jobAction VARCHAR(75) null,
	jobDate DATE null
);

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
	permissionsGrantable LONG,
	groupPermission BOOLEAN
);

create table yams_PermissionsDefined (
	permissionKey VARCHAR(75) not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	bitLocation INTEGER
);