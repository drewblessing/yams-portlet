create index IX_A7D4FE4E on yams_ActionLog (modifiedDate);
create index IX_F1DF17E5 on yams_ActionLog (modifiedDate, userEmailAddress, modifiedUserEmailAddress);
create index IX_36BD7580 on yams_ActionLog (modifiedDescription);
create index IX_B2970C17 on yams_ActionLog (modifiedDescription, userEmailAddress, modifiedUserEmailAddress);
create index IX_AC1A6092 on yams_ActionLog (modifiedFqgn);
create index IX_1648135D on yams_ActionLog (modifiedFqgn, modifiedDate);
create index IX_48F29883 on yams_ActionLog (modifiedUserEmailAddress);
create index IX_E420AEDA on yams_ActionLog (userEmailAddress);
create index IX_CA89135A on yams_ActionLog (userEmailAddress, modifiedUserEmailAddress);

create index IX_5315C845 on yams_Permissions (emailAddress);
create index IX_67802AEB on yams_Permissions (emailAddress, fqgn);
create index IX_40D70BD3 on yams_Permissions (emailAddress, fqgn, groupPermission);
create index IX_2C4C823F on yams_Permissions (fqgn, groupPermission);

create index IX_10D36C96 on yams_PermissionsDefined (bitLocation);