create table materials (created_at bigint not null, updated_at bigint not null, id varchar(255) not null, name varchar(255) not null, course_id varchar(255) not null, location varchar(255) not null, extension varchar(255) not null, size bigint not null, primary key (id));
alter table if exists materials add constraint FK_MATERIALS_COURSE_ID_COURSES_ID foreign key (course_id) references courses;
