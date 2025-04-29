alter table courses drop column start_date;
alter table courses drop column end_date;
alter table courses add column semester int;
update courses set semester = 3;
alter table courses alter column semester set not null;
