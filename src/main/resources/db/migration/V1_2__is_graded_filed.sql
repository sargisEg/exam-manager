alter table exams add column is_graded boolean;
update exams set is_graded = false;
alter table exams alter column is_graded set not null;