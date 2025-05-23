alter table if exists courses drop constraint if exists FK_COURSES_GROUP_ID_GROUPS_ID;
alter table if exists exam_results drop constraint if exists FK_EXAM_RESULTS_EXAM_ID_EXAMS_ID;
alter table if exists exam_results drop constraint if exists FK_EXAM_RESULTS_STUDENT_ID_USERS_ID;
alter table if exists exams drop constraint if exists FK_EXAMS_COURSE_ID_COURSES_ID;
alter table if exists exams drop constraint if exists FK_EXAMS_SUBGROUP_ID_SUBGROUPS_ID;
alter table if exists groups drop constraint if exists FK_GROUPS_DEPARTMENT_ID_DEPARTMENTS_ID;
alter table if exists subgroups drop constraint if exists FK_SUBGROUPS_GROUP_ID_GROUPS_ID;
alter table if exists user_sessions drop constraint if exists FK_USER_SESSIONS_USER_ID_USERS_ID;
alter table if exists users drop constraint if exists FK_STUDENTS_SUBGROUP_ID_SUBGROUPS_ID;
alter table if exists users_subgroups drop constraint if exists FKprhtjk695c1laaxwir0uecq3r;
alter table if exists users_subgroups drop constraint if exists FK50xyja72poo9igitee89t6s9p;

drop table if exists courses cascade;
drop table if exists departments cascade;
drop table if exists exam_results cascade;
drop table if exists exams cascade;
drop table if exists groups cascade;
drop table if exists subgroups cascade;
drop table if exists user_sessions cascade;
drop table if exists users cascade;
drop table if exists users_subgroups cascade;


create table courses (created_at bigint not null, updated_at bigint not null, group_id varchar(255) not null, id varchar(255) not null, name varchar(255) not null, teacher_id varchar(255) not null, primary key (id));
create table departments (created_at bigint not null, updated_at bigint not null, id varchar(255) not null, name varchar(255) not null, name_short varchar(255) not null, primary key (id));
create table exam_results (point integer not null, created_at bigint not null, updated_at bigint not null, exam_id varchar(255), id varchar(255) not null, student_id varchar(255), primary key (id));
create table exams (created_at bigint not null, end_date bigint not null, max_points bigint not null, start_date bigint not null, updated_at bigint not null, course_id varchar(255) not null, id varchar(255) not null, location varchar(255) not null, status varchar(255) not null check (status in ('UPCOMING','IN_PROGRESS','FINISHED','CANCELED')), subgroup_id varchar(255) not null, title varchar(255) not null, type varchar(255) not null check (type in ('MIDTERM','GENERAL','REPEAT')), primary key (id));
create table groups (end_year integer not null, start_year integer not null, created_at bigint not null, updated_at bigint not null, department_id varchar(255) not null, id varchar(255) not null, name varchar(255) not null, primary key (id));
create table subgroups (created_at bigint not null, updated_at bigint not null, group_id varchar(255) not null, id varchar(255) not null, name varchar(255) not null, primary key (id));
create table user_sessions (created_at bigint not null, exp_at bigint not null, updated_at bigint not null, id varchar(255) not null, user_id varchar(255) not null, primary key (id), unique (user_id, id));
create table users (created_at bigint not null, updated_at bigint not null, user_type_discriminator varchar(31) not null, email varchar(255) not null unique, full_name varchar(255) not null, id varchar(255) not null, password varchar(255) not null, phone varchar(255) not null unique, role varchar(255) not null check (role in ('STUDENT','TEACHER','ADMIN')), subgroup_id varchar(255), primary key (id));
create table users_subgroups (subgroups_id varchar(255) not null, teacher_id varchar(255) not null, primary key (subgroups_id, teacher_id));

alter table if exists courses add constraint FK_COURSES_GROUP_ID_GROUPS_ID foreign key (group_id) references groups;
alter table if exists courses add constraint FK_COURSES_TEACHER_ID_TEACHERS_ID foreign key (teacher_id) references users;
alter table if exists exam_results add constraint FK_EXAM_RESULTS_EXAM_ID_EXAMS_ID foreign key (exam_id) references exams;
alter table if exists exam_results add constraint FK_EXAM_RESULTS_STUDENT_ID_USERS_ID foreign key (student_id) references users;
alter table if exists exams add constraint FK_EXAMS_COURSE_ID_COURSES_ID foreign key (course_id) references courses;
alter table if exists exams add constraint FK_EXAMS_SUBGROUP_ID_SUBGROUPS_ID foreign key (subgroup_id) references subgroups;
alter table if exists groups add constraint FK_GROUPS_DEPARTMENT_ID_DEPARTMENTS_ID foreign key (department_id) references departments;
alter table if exists subgroups add constraint FK_SUBGROUPS_GROUP_ID_GROUPS_ID foreign key (group_id) references groups;
alter table if exists user_sessions add constraint FK_USER_SESSIONS_USER_ID_USERS_ID foreign key (user_id) references users;
alter table if exists users add constraint FK_STUDENTS_SUBGROUP_ID_SUBGROUPS_ID foreign key (subgroup_id) references subgroups;
alter table if exists users_subgroups add constraint FKprhtjk695c1laaxwir0uecq3r foreign key (subgroups_id) references subgroups;
alter table if exists users_subgroups add constraint FK50xyja72poo9igitee89t6s9p foreign key (teacher_id) references users;