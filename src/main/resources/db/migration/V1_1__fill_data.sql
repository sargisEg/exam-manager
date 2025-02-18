-- Final SQL script to populate the database with sample data

-- 1. Enable the uuid-ossp extension (run this ONCE per database)
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 2. Inserting data into "users" table (Teachers and Admin - MUST BE FIRST USERS)
INSERT INTO public.users (created_at, updated_at, user_type_discriminator, email, full_name, id, password, phone) VALUES
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Teacher', 'teacher1@example.com', 'Teacher One', uuid_generate_v4(), 'password123', '+1234567890'), -- teacher1_id (UUID)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Teacher', 'teacher2@example.com', 'Teacher Two', uuid_generate_v4(), 'password123', '+1098765432'), -- teacher2_id (UUID)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Teacher', 'teacher3@example.com', 'Teacher Three', uuid_generate_v4(), 'password123', '+4444444444'), -- teacher3_id (UUID)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Teacher', 'teacher4@example.com', 'Teacher Four', uuid_generate_v4(), 'password123', '+5555555555'), -- teacher4_id (UUID)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Teacher', 'teacher5@example.com', 'Teacher Five', uuid_generate_v4(), 'password123', '+6666666666'), -- teacher5_id (UUID)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Teacher', 'teacher6@example.com', 'Teacher Six', uuid_generate_v4(), 'password123', '+7777777777'), -- teacher6_id (UUID)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Admin', 'admin1@example.com', 'Admin User One', uuid_generate_v4(), 'adminpass123', '+0000000000'); -- admin1_id (UUID)


-- 3. Inserting data into "departments" table (Referencing Teachers for head_of_department)
INSERT INTO public.departments (created_at, updated_at, head_of_department, id, name, name_short) VALUES
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.users WHERE email = 'teacher1@example.com'), uuid_generate_v4(), 'Department of Computer Science', 'CS'), -- department1_id (UUID)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.users WHERE email = 'teacher2@example.com'), uuid_generate_v4(), 'Department of Mathematics', 'Math'), -- department2_id (UUID)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.users WHERE email = 'teacher3@example.com'), uuid_generate_v4(), 'Department of Biology', 'Bio'),   -- department3_id (UUID)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.users WHERE email = 'teacher4@example.com'), uuid_generate_v4(), 'Department of Engineering', 'Eng'), -- department4_id (UUID)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.users WHERE email = 'teacher5@example.com'), uuid_generate_v4(), 'Department of History', 'Hist');  -- department5_id (UUID)


-- 4. Inserting data into "groups" table (5 groups, referencing departments)
INSERT INTO public.groups (end_year, start_year, created_at, updated_at, department_id, id, name) VALUES
(2025, 2021, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.departments WHERE name_short = 'CS'), uuid_generate_v4(), 'CS Group 1'),    -- group1_id (UUID)
(2026, 2022, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.departments WHERE name_short = 'Math'), uuid_generate_v4(), 'Math Group 1'),  -- group2_id (UUID)
(2027, 2023, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.departments WHERE name_short = 'Bio'), uuid_generate_v4(), 'Bio Group 1'),     -- group3_id (UUID)
(2028, 2024, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.departments WHERE name_short = 'Eng'), uuid_generate_v4(), 'Eng Group 1'),    -- group4_id (UUID)
(2026, 2022, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.departments WHERE name_short = 'Hist'), uuid_generate_v4(), 'Hist Group 1');   -- group5_id (UUID)


-- 5. Inserting data into "subgroups" table (3-5 subgroups per group, referencing groups)
INSERT INTO public.subgroups (created_at, updated_at, group_id, id, name) VALUES
-- CS Group 1 Subgroups (5)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), uuid_generate_v4(), 'CS Subgroup 1A'), -- subgroup1_id (UUID)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), uuid_generate_v4(), 'CS Subgroup 1B'), -- subgroup2_id (UUID)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), uuid_generate_v4(), 'CS Subgroup 1C'), -- subgroup3_id (UUID)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), uuid_generate_v4(), 'CS Subgroup 1D'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), uuid_generate_v4(), 'CS Subgroup 1E'),
-- Math Group 1 Subgroups (4)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Math Group 1'), uuid_generate_v4(), 'Math Subgroup 1A'), -- subgroup_math1a_id (UUID - used from previous scripts)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Math Group 1'), uuid_generate_v4(), 'Math Subgroup 1B'), -- subgroup_math1b_id (UUID - used from previous scripts)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Math Group 1'), uuid_generate_v4(), 'Math Subgroup 1C'), -- subgroup_math1c_id (UUID - used from previous scripts)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Math Group 1'), uuid_generate_v4(), 'Math Subgroup 1D'),
-- Bio Group 1 Subgroups (3)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Bio Group 1'), uuid_generate_v4(), 'Bio Subgroup 1A'),  -- subgroup_bio1a_id (UUID - used from previous scripts)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Bio Group 1'), uuid_generate_v4(), 'Bio Subgroup 1B'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Bio Group 1'), uuid_generate_v4(), 'Bio Subgroup 1C'),
-- Eng Group 1 Subgroups (4)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Eng Group 1'), uuid_generate_v4(), 'Eng Subgroup 1A'),  -- subgroup_eng1a_id (UUID - used from previous scripts)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Eng Group 1'), uuid_generate_v4(), 'Eng Subgroup 1B'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Eng Group 1'), uuid_generate_v4(), 'Eng Subgroup 1C'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Eng Group 1'), uuid_generate_v4(), 'Eng Subgroup 1D'),
-- Hist Group 1 Subgroups (3)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Hist Group 1'), uuid_generate_v4(), 'Hist Subgroup 1A'), -- subgroup_hist1a_id (UUID - used from previous scripts)
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Hist Group 1'), uuid_generate_v4(), 'Hist Subgroup 1B'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Hist Group 1'), uuid_generate_v4(), 'Hist Subgroup 1C');


-- 6. Inserting data into "users" table (Students, referencing subgroups)
INSERT INTO public.users (created_at, updated_at, user_type_discriminator, email, full_name, id, password, phone, subgroup_id) VALUES
-- CS Group 1 Students
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student1@example.com', 'Student One', uuid_generate_v4(), 'password123', '+1111111111', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1A')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student2@example.com', 'Student Two', uuid_generate_v4(), 'password123', '+2222222222', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1B')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student3@example.com', 'Student Three', uuid_generate_v4(), 'password123', '+3333333333', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1C')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student4@example.com', 'Student Four', uuid_generate_v4(), 'password123', '+8888888888', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1A')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student5@example.com', 'Student Five', uuid_generate_v4(), 'password123', '+9999999999', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1B')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student6@example.com', 'Student Six', uuid_generate_v4(), 'password123', '+1010101010', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1D')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student7@example.com', 'Student Seven', uuid_generate_v4(), 'password123', '+1212121212', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1E')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student8@example.com', 'Student Eight', uuid_generate_v4(), 'password123', '+1313131313', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1A')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student9@example.com', 'Student Nine', uuid_generate_v4(), 'password123', '+1414141414', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1B')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student10@example.com', 'Student Ten', uuid_generate_v4(), 'password123', '+1515151515', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1C')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student11@example.com', 'Student Eleven', uuid_generate_v4(), 'password123', '+1616161616', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1A')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student12@example.com', 'Student Twelve', uuid_generate_v4(), 'password123', '+1717171717', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1B')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student13@example.com', 'Student Thirteen', uuid_generate_v4(), 'password123', '+1818181818', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1C')),
-- Math Group 1 Students
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student14@example.com', 'Student Fourteen', uuid_generate_v4(), 'password123', '+1919191919', (SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1A')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student15@example.com', 'Student Fifteen', uuid_generate_v4(), 'password123', '+2020202020', (SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1B')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student16@example.com', 'Student Sixteen', uuid_generate_v4(), 'password123', '+2121212121', (SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1C')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student17@example.com', 'Student Seventeen', uuid_generate_v4(), 'password123', '+2222222223', (SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1D')),
-- Bio Group 1 Students
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student18@example.com', 'Student Eighteen', uuid_generate_v4(), 'password123', '+2323232323', (SELECT id FROM public.subgroups WHERE name = 'Bio Subgroup 1A')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student19@example.com', 'Student Nineteen', uuid_generate_v4(), 'password123', '+2424242424', (SELECT id FROM public.subgroups WHERE name = 'Bio Subgroup 1B')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student20@example.com', 'Student Twenty', uuid_generate_v4(), 'password123', '+2525252525', (SELECT id FROM public.subgroups WHERE name = 'Bio Subgroup 1C')),
-- Eng Group 1 Students
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student21@example.com', 'Student TwentyOne', uuid_generate_v4(), 'password123', '+2626262626', (SELECT id FROM public.subgroups WHERE name = 'Eng Subgroup 1A')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student22@example.com', 'Student TwentyTwo', uuid_generate_v4(), 'password123', '+2727272727', (SELECT id FROM public.subgroups WHERE name = 'Eng Subgroup 1B')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student23@example.com', 'Student TwentyThree', uuid_generate_v4(), 'password123', '+2828282828', (SELECT id FROM public.subgroups WHERE name = 'Eng Subgroup 1C')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student24@example.com', 'Student TwentyFour', uuid_generate_v4(), 'password123', '+2929292929', (SELECT id FROM public.subgroups WHERE name = 'Eng Subgroup 1D')),
-- Hist Group 1 Students
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student25@example.com', 'Student TwentyFive', uuid_generate_v4(), 'password123', '+3030303030', (SELECT id FROM public.subgroups WHERE name = 'Hist Subgroup 1A')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student26@example.com', 'Student TwentySix', uuid_generate_v4(), 'password123', '+3131313131', (SELECT id FROM public.subgroups WHERE name = 'Hist Subgroup 1B')),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, 'Student', 'student27@example.com', 'Student TwentySeven', uuid_generate_v4(), 'password123', '+3232323232', (SELECT id FROM public.subgroups WHERE name = 'Hist Subgroup 1C'));


-- 7. Inserting data into "courses" table (Referencing Groups)
INSERT INTO public.courses (created_at, updated_at, group_id, id, name) VALUES
-- CS Courses
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), uuid_generate_v4(), 'Introduction to Computer Science'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), uuid_generate_v4(), 'Data Structures and Algorithms'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), uuid_generate_v4(), 'Operating Systems'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'CS Group 1'), uuid_generate_v4(), 'Database Management Systems'),
-- Math Courses
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Math Group 1'), uuid_generate_v4(), 'Calculus 1'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Math Group 1'), uuid_generate_v4(), 'Calculus 2'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Math Group 1'), uuid_generate_v4(), 'Linear Algebra'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Math Group 1'), uuid_generate_v4(), 'Differential Equations'),
-- Bio Courses
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Bio Group 1'), uuid_generate_v4(), 'Introduction to Biology'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Bio Group 1'), uuid_generate_v4(), 'Genetics'),
-- Eng Courses
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Eng Group 1'), uuid_generate_v4(), 'Engineering Mechanics'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Eng Group 1'), uuid_generate_v4(), 'Thermodynamics'),
-- Hist Courses
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Hist Group 1'), uuid_generate_v4(), 'World History 101'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.groups WHERE name = 'Hist Group 1'), uuid_generate_v4(), 'European History 19th Century');


-- 8. Inserting data into "exams" table (Referencing Courses and Subgroups)
INSERT INTO public.exams (created_at, end_date, max_points, start_date, updated_at, course_id, id, location, status, subgroup_id, title, type) VALUES
-- CS Exams
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000, 100, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Introduction to Computer Science'), uuid_generate_v4(), 'Room 101', 'UPCOMING', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1A'), 'ICS Midterm Exam', 'MIDTERM'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*3, 120, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Data Structures and Algorithms'), uuid_generate_v4(), 'Room 102', 'UPCOMING', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1B'), 'DSA Midterm', 'MIDTERM'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*7, 150, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Operating Systems'), uuid_generate_v4(), 'Room 103', 'UPCOMING', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1C'), 'OS General Exam', 'GENERAL'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*10, 100, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Database Management Systems'), uuid_generate_v4(), 'Room 104', 'UPCOMING', (SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1D'), 'DBMS Midterm', 'MIDTERM'),
-- Math Exams
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*7, 150, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Calculus 1'), uuid_generate_v4(), 'Room 202', 'UPCOMING', (SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1A'), 'Calculus General Exam', 'GENERAL'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*14, 120, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Linear Algebra'), uuid_generate_v4(), 'Room 203', 'UPCOMING', (SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1B'), 'Linear Algebra Midterm', 'MIDTERM'),
-- Bio Exams
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*10, 100, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Introduction to Biology'), uuid_generate_v4(), 'Lab 1', 'UPCOMING', (SELECT id FROM public.subgroups WHERE name = 'Bio Subgroup 1A'), 'Biology General Exam', 'GENERAL'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*14, 110, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Genetics'), uuid_generate_v4(), 'Lab 2', 'UPCOMING', (SELECT id FROM public.subgroups WHERE name = 'Bio Subgroup 1B'), 'Genetics Midterm', 'MIDTERM'),
-- Eng Exams
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*5, 130, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Engineering Mechanics'), uuid_generate_v4(), 'Hall A', 'IN_PROGRESS', (SELECT id FROM public.subgroups WHERE name = 'Eng Subgroup 1A'), 'Engineering Midterm', 'MIDTERM'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*12, 140, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'Thermodynamics'), uuid_generate_v4(), 'Hall B', 'UPCOMING', (SELECT id FROM public.subgroups WHERE name = 'Eng Subgroup 1C'), 'Thermodynamics General Exam', 'GENERAL'),
-- Hist Exams
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint + 86400000*14, 110, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'World History 101'), uuid_generate_v4(), 'Library', 'UPCOMING', (SELECT id FROM public.subgroups WHERE name = 'Hist Subgroup 1A'), 'History General Exam', 'GENERAL'),
((EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint - 86400000, 100, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint - 86400000*7, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.courses WHERE name = 'European History 19th Century'), uuid_generate_v4(), 'Archive Room', 'FINISHED', (SELECT id FROM public.subgroups WHERE name = 'Hist Subgroup 1B'), 'European History Midterm', 'MIDTERM');


-- 9. Inserting data into "exam_results" table (Referencing Exams and Students)
INSERT INTO public.exam_results (point, created_at, updated_at, exam_id, id, student_id) VALUES
(85, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'ICS Midterm Exam'), uuid_generate_v4(), (SELECT id FROM public.users WHERE email = 'student1@example.com')),
(92, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'ICS Midterm Exam'), uuid_generate_v4(), (SELECT id FROM public.users WHERE email = 'student2@example.com')),
(78, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'DSA Midterm'), uuid_generate_v4(), (SELECT id FROM public.users WHERE email = 'student3@example.com')),
(95, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'DSA Midterm'), uuid_generate_v4(), (SELECT id FROM public.users WHERE email = 'student4@example.com')),
(65, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'Calculus 1 Repeat Exam'), uuid_generate_v4(), (SELECT id FROM public.users WHERE email = 'student14@example.com')),
(88, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'Engineering Midterm'), uuid_generate_v4(), (SELECT id FROM public.users WHERE email = 'student21@example.com')),
(70, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'History General Exam'), uuid_generate_v4(), (SELECT id FROM public.users WHERE email = 'student25@example.com')),
(80, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (EXTRACT(EPOCH FROM NOW()) * 1000)::bigint, (SELECT id FROM public.exams WHERE title = 'History General Exam'), uuid_generate_v4(), (SELECT id FROM public.users WHERE email = 'student26@example.com'));


-- 10. Inserting data into "user_roles" table (Referencing Users, using sequence for id)
INSERT INTO public.user_roles (id, role, user_id) VALUES
(nextval('public.user_roles_id'), 'TEACHER', (SELECT id FROM public.users WHERE email = 'teacher1@example.com')),
(nextval('public.user_roles_id'), 'DEPARTMENT_HEAD', (SELECT id FROM public.users WHERE email = 'teacher1@example.com')),
(nextval('public.user_roles_id'), 'TEACHER', (SELECT id FROM public.users WHERE email = 'teacher2@example.com')),
(nextval('public.user_roles_id'), 'DEPARTMENT_HEAD', (SELECT id FROM public.users WHERE email = 'teacher2@example.com')),
(nextval('public.user_roles_id'), 'TEACHER', (SELECT id FROM public.users WHERE email = 'teacher3@example.com')),
(nextval('public.user_roles_id'), 'DEPARTMENT_HEAD', (SELECT id FROM public.users WHERE email = 'teacher3@example.com')),
(nextval('public.user_roles_id'), 'TEACHER', (SELECT id FROM public.users WHERE email = 'teacher4@example.com')),
(nextval('public.user_roles_id'), 'DEPARTMENT_HEAD', (SELECT id FROM public.users WHERE email = 'teacher4@example.com')),
(nextval('public.user_roles_id'), 'TEACHER', (SELECT id FROM public.users WHERE email = 'teacher5@example.com')),
(nextval('public.user_roles_id'), 'DEPARTMENT_HEAD', (SELECT id FROM public.users WHERE email = 'teacher5@example.com')),
(nextval('public.user_roles_id'), 'TEACHER', (SELECT id FROM public.users WHERE email = 'teacher6@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student1@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student2@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student3@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student4@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student5@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student6@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student7@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student8@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student9@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student10@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student11@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student12@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student13@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student14@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student15@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student16@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student17@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student18@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student19@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student20@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student21@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student22@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student23@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student24@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student25@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student26@example.com')),
(nextval('public.user_roles_id'), 'STUDENT', (SELECT id FROM public.users WHERE email = 'student27@example.com')),
(nextval('public.user_roles_id'), 'ADMIN', (SELECT id FROM public.users WHERE email = 'admin1@example.com'));


-- 11. Inserting data into "users_subgroups" table (Teachers teaching subgroups)
INSERT INTO public.users_subgroups (subgroups_id, teacher_id) VALUES
((SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1A'), (SELECT id FROM public.users WHERE email = 'teacher1@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1B'), (SELECT id FROM public.users WHERE email = 'teacher1@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1C'), (SELECT id FROM public.users WHERE email = 'teacher2@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1D'), (SELECT id FROM public.users WHERE email = 'teacher3@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'CS Subgroup 1E'), (SELECT id FROM public.users WHERE email = 'teacher4@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1A'), (SELECT id FROM public.users WHERE email = 'teacher5@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1B'), (SELECT id FROM public.users WHERE email = 'teacher5@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'Math Subgroup 1C'), (SELECT id FROM public.users WHERE email = 'teacher6@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'Bio Subgroup 1A'), (SELECT id FROM public.users WHERE email = 'teacher1@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'Bio Subgroup 1B'), (SELECT id FROM public.users WHERE email = 'teacher2@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'Bio Subgroup 1C'), (SELECT id FROM public.users WHERE email = 'teacher3@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'Eng Subgroup 1A'), (SELECT id FROM public.users WHERE email = 'teacher4@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'Eng Subgroup 1B'), (SELECT id FROM public.users WHERE email = 'teacher5@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'Eng Subgroup 1C'), (SELECT id FROM public.users WHERE email = 'teacher6@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'Eng Subgroup 1D'), (SELECT id FROM public.users WHERE email = 'teacher1@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'Hist Subgroup 1A'), (SELECT id FROM public.users WHERE email = 'teacher2@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'Hist Subgroup 1B'), (SELECT id FROM public.users WHERE email = 'teacher3@example.com')),
((SELECT id FROM public.subgroups WHERE name = 'Hist Subgroup 1C'), (SELECT id FROM public.users WHERE email = 'teacher4@example.com'));

-- End of SQL script --