alter table courses add column start_date bigint;
alter table courses add column end_date bigint;
update courses set start_date = 1756670400000;
update courses set end_date = 1767124800000;
alter table courses alter column start_date set not null;
alter table courses alter column end_date set not null;