create index IX_79587E25 on task_Task (groupId);
create index IX_1B4AF2D9 on task_Task (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_A0745B9B on task_Task (uuid_[$COLUMN_LENGTH:75$], groupId);