-- More SQL scripts to fill data into the tables

-- 1. Inserting more data into "departments" table
INSERT INTO public.departments (created_at, updated_at, head_of_department, id, name, name_short) VALUES
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'teacher3_id', uuid_generate_v4(), 'Department of Biology', 'Bio'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'teacher4_id', uuid_generate_v4(), 'Department of Engineering', 'Eng'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'teacher5_id', uuid_generate_v4(), 'Department of History', 'Hist');

-- 2. Inserting more data into "users" table
-- More Teachers (including heads of new departments and one more teacher)
INSERT INTO public.users (created_at, updated_at, user_type_discriminator, email, full_name, id, password, phone) VALUES
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Teacher', 'teacher3@example.com', 'Teacher Three', 'teacher3_id', 'password123', '+4444444444'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Teacher', 'teacher4@example.com', 'Teacher Four', 'teacher4_id', 'password123', '+5555555555'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Teacher', 'teacher5@example.com', 'Teacher Five', 'teacher5_id', 'password123', '+6666666666'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Teacher', 'teacher6@example.com', 'Teacher Six', uuid_generate_v4(), 'password123', '+7777777777');

-- More Students
INSERT INTO public.users (created_at, updated_at, user_type_discriminator, email, full_name, id, password, phone, subgroup_id) VALUES
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student4@example.com', 'Student Four', 'student4_id', 'password123', '+8888888888', 'subgroup1_id'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student5@example.com', 'Student Five', 'student5_id', 'password123', '+9999999999', 'subgroup2_id'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student6@example.com', 'Student Six', uuid_generate_v4(), 'password123', '+1010101010', uuid_generate_v4()), -- New subgroup will be created below
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student7@example.com', 'Student Seven', uuid_generate_v4(), 'password123', '+1212121212', uuid_generate_v4()); -- New subgroup will be created below

-- Admin User
INSERT INTO public.users (created_at, updated_at, user_type_discriminator, email, full_name, id, password, phone) VALUES
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Admin', 'admin1@example.com', 'Admin User One', 'admin1_id', 'adminpass123', '+0000000000');

-- 3. Inserting more data into "groups" table
INSERT INTO public.groups (end_year, start_year, created_at, updated_at, department_id, id, name) VALUES
(2027, 2023, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.departments WHERE name_short = 'Bio'), uuid_generate_v4(), 'Bio Group 1'),
(2028, 2024, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.departments WHERE name_short = 'Eng'), uuid_generate_v4(), 'Eng Group 1'),
(2026, 2022, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.departments WHERE name_short = 'Hist'), uuid_generate_v4(), 'Hist Group 1');

-- 4. Inserting more data into "subgroups" table
INSERT INTO public.subgroups (created_at, updated_at, group_id, id, name) VALUES
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), 'subgroup3_id', 'CS Subgroup 1C'), -- More for CS Group 1
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Math Group 1'), 'subgroup3_math_id', 'Math Subgroup 1B'), -- More for Math Group 1
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Bio Group 1'), uuid_generate_v4(), 'Bio Subgroup 1A'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Eng Group 1'), uuid_generate_v4(), 'Eng Subgroup 1A'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Hist Group 1'), uuid_generate_v4(), 'Hist Subgroup 1A'),
-- Subgroups for students student6 and student7 (using generated UUIDs for subgroup_id from student inserts)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), (SELECT subgroup_id FROM public.users WHERE email = 'student6@example.com'), 'CS Subgroup 1D'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Math Group 1'), (SELECT subgroup_id FROM public.users WHERE email = 'student7@example.com'), 'Math Subgroup 1C');


-- Updating student6 and student7 subgroup_id since subgroups are created now
UPDATE public.users SET subgroup_id = (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1D') WHERE email = 'student6@example.com';
UPDATE public.users SET subgroup_id = (SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1C') WHERE email = 'student7@example.com';


-- 5. Inserting more data into "courses" table
INSERT INTO public.courses (created_at, updated_at, group_id, id, name) VALUES
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), uuid_generate_v4(), 'Data Structures and Algorithms'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Bio Group 1'), uuid_generate_v4(), 'Introduction to Biology'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Eng Group 1'), uuid_generate_v4(), 'Engineering Mechanics'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Hist Group 1'), uuid_generate_v4(), 'World History 101'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Math Group 1'), uuid_generate_v4(), 'Calculus 2');

-- 6. Inserting more data into "exams" table
INSERT INTO public.exams (created_at, end_date, max_points, start_date, updated_at, course_id, id, location, status, subgroup_id, title, type) VALUES
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*3, 120, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Data Structures and Algorithms'), uuid_generate_v4(), 'Room 102', 'UPCOMING', 'subgroup2_id', 'DSA Midterm', 'MIDTERM'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*10, 100, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Introduction to Biology'), uuid_generate_v4(), 'Lab 1', 'UPCOMING', (SELECT id FROM public.subgroups WHERE name = 'Bio Subgroup 1A'), 'Biology General Exam', 'GENERAL'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint - 86400000, 100, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint - 86400000*7, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Calculus 1'), uuid_generate_v4(), 'Room 202', 'FINISHED', (SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1A'), 'Calculus 1 Repeat Exam', 'REPEAT'), -- Example of a finished exam
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*5, 130, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Engineering Mechanics'), uuid_generate_v4(), 'Hall A', 'IN_PROGRESS', (SELECT id FROM public.subgroups WHERE name = 'Eng Subgroup 1A'), 'Engineering Midterm', 'MIDTERM'), -- Example of in progress exam
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*14, 110, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'World History 101'), uuid_generate_v4(), 'Library', 'UPCOMING', (SELECT id FROM public.subgroups WHERE name = 'Hist Subgroup 1A'), 'History General Exam', 'GENERAL');

-- 7. Inserting more data into "exam_results" table
INSERT INTO public.exam_results (point, created_at, updated_at, exam_id, id, student_id) VALUES
(78, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'DSA Midterm'), uuid_generate_v4(), 'student3_id'),
(95, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'DSA Midterm'), uuid_generate_v4(), 'student4_id'),
(65, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'Calculus 1 Repeat Exam'), uuid_generate_v4(), 'student1_id'), -- Result for repeat exam
(88, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'Engineering Midterm'), uuid_generate_v4(), 'student5_id'),
(70, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'History General Exam'), uuid_generate_v4(), 'student6_id'),
(80, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'History General Exam'), uuid_generate_v4(), 'student7_id');

-- 8. Inserting more data into "user_roles" table
INSERT INTO public.user_roles (id, role, user_id) VALUES
(nextval('public.user_roles_id'), 'DEPARTMENT_HEAD', 'teacher3_id'),
(nextval('public.user_roles_id'), 'DEPARTMENT_HEAD', 'teacher4_id'),
(nextval('public.user_roles_id'), 'DEPARTMENT_HEAD', 'teacher5_id'),
(nextval('public.user_roles_id'), 'TEACHER', 'teacher6_id'),
(nextval('public.user_roles_id'), 'STUDENT', 'student4_id'),
(nextval('public.user_roles_id'), 'STUDENT', 'student5_id'),
(nextval('public.user_roles_id'), 'STUDENT', 'student6_id'),
(nextval('public.user_roles_id'), 'STUDENT', 'student7_id'),
(nextval('public.user_roles_id'), 'ADMIN', 'admin1_id'); -- Assign admin role to admin user

-- 9. Inserting more data into "users_subgroups" table
INSERT INTO public.users_subgroups (subgroups_id, teacher_id) VALUES
('subgroup2_id', 'teacher3_id'), -- Teacher 3 to subgroup 2 (CS Subgroup 1B)
('subgroup3_id', 'teacher4_id'), -- Teacher 4 to subgroup 3 (CS Subgroup 1C)
((SELECT id FROM public.subgroups WHERE name = 'Bio Subgroup 1A'), 'teacher5_id'), -- Teacher 5 to Bio Subgroup
((SELECT id FROM public.subgroups WHERE name = 'Eng Subgroup 1A'), 'teacher6_id'), -- Teacher 6 to Eng Subgroup
((SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1B'), 'teacher2_id'); -- Teacher 2 to Math Subgroup 1B (already teaching 1A)

INSERT INTO public.courses (created_at, updated_at, group_id, id, name) VALUES
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), uuid_generate_v4(), 'Operating Systems'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), uuid_generate_v4(), 'Database Management Systems'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Math Group 1'), uuid_generate_v4(), 'Linear Algebra'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Math Group 1'), uuid_generate_v4(), 'Differential Equations'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Bio Group 1'), uuid_generate_v4(), 'Genetics'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Eng Group 1'), uuid_generate_v4(), 'Thermodynamics'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Hist Group 1'), uuid_generate_v4(), 'European History 19th Century');

INSERT INTO public.users (created_at, updated_at, user_type_discriminator, email, full_name, id, password, phone, subgroup_id) VALUES
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student8@example.com', 'Student Eight', uuid_generate_v4(), 'password123', '+1313131313', 'subgroup1_id'), -- CS Subgroup 1A
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student9@example.com', 'Student Nine', uuid_generate_v4(), 'password123', '+1414141414', 'subgroup2_id'), -- CS Subgroup 1B
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student10@example.com', 'Student Ten', uuid_generate_v4(), 'password123', '+1515151515', 'subgroup3_id'), -- CS Subgroup 1C
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student11@example.com', 'Student Eleven', uuid_generate_v4(), 'password123', '+1616161616', 'subgroup1_id'), -- CS Subgroup 1A
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student12@example.com', 'Student Twelve', uuid_generate_v4(), 'password123', '+1717171717', 'subgroup2_id'), -- CS Subgroup 1B
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student13@example.com', 'Student Thirteen', uuid_generate_v4(), 'password123', '+1818181818', 'subgroup3_id'), -- CS Subgroup 1C
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student14@example.com', 'Student Fourteen', uuid_generate_v4(), 'password123', '+1919191919', (SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1A')), -- Math Subgroup 1A
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student15@example.com', 'Student Fifteen', uuid_generate_v4(), 'password123', '+2020202020', (SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1B')), -- Math Subgroup 1B
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student16@example.com', 'Student Sixteen', uuid_generate_v4(), 'password123', '+2121212121', (SELECT id FROM public.subgroups WHERE name = 'Bio Subgroup 1A')),  -- Bio Subgroup 1A
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student17@example.com', 'Student Seventeen', uuid_generate_v4(), 'password123', '+2222222223', (SELECT id FROM public.subgroups WHERE name = 'Eng Subgroup 1A')),  -- Eng Subgroup 1A
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student18@example.com', 'Student Eighteen', uuid_generate_v4(), 'password123', '+2323232323', (SELECT id FROM public.subgroups WHERE name = 'Hist Subgroup 1A')); -- Hist Subgroup 1A


