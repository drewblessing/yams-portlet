create index IX_67802AEB on yams_Permissions (emailAddress, fqgn);
create index IX_40D70BD3 on yams_Permissions (emailAddress, fqgn, groupPermission);
create index IX_2C4C823F on yams_Permissions (fqgn, groupPermission);
create index IX_67E5FAB9 on yams_Permissions (userId, fqgn);

create index IX_10D36C96 on yams_PermissionsDefined (bitLocation);