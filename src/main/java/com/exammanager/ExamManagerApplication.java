package com.exammanager;

import com.exammanager.core.model.entity.Department;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.entity.Student;
import com.exammanager.core.model.entity.Teacher;
import com.exammanager.core.repository.DepartmentRepository;
import com.exammanager.core.repository.GroupRepository;
import com.exammanager.core.repository.StudentRepository;
import com.exammanager.core.repository.TeacherRepository;
import com.exammanager.user.model.entity.*;
import com.exammanager.user.model.enums.Role;
import com.exammanager.user.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class ExamManagerApplication {
    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(ExamManagerApplication.class);

        final UserRolesRepository userRolesRepository = context.getBean(UserRolesRepository.class);
        final StudentRepository studentRepository = context.getBean(StudentRepository.class);
        final TeacherRepository teacherRepository = context.getBean(TeacherRepository.class);
        final GroupRepository groupRepository = context.getBean(GroupRepository.class);
        final DepartmentRepository departmentRepository = context.getBean(DepartmentRepository.class);
        final BCryptPasswordEncoder encoder = context.getBean(BCryptPasswordEncoder.class);

        final String password = encoder.encode("password");

        final Teacher headTeacher = teacherRepository.save(new Teacher("headTeachId", System.currentTimeMillis(), System.currentTimeMillis(), "headTeach", "head@email.com", "123245879", password, null));
        final Department department = departmentRepository.save(new Department("depId", System.currentTimeMillis(), System.currentTimeMillis(), "depName", "short", headTeacher));
        final Group group = groupRepository.save(new Group("groupId", System.currentTimeMillis(), System.currentTimeMillis(), 2024, 2055, department));

        final Teacher teacher = teacherRepository.save(new Teacher("teachId", System.currentTimeMillis(), System.currentTimeMillis(), "teach", "teacher@email.com", "1234545879", password, Set.of(group)));
        final Student student = studentRepository.save(new Student("studId", System.currentTimeMillis(), System.currentTimeMillis(), "student", "student@email.com", "123445879", password, group));

        userRolesRepository.save(new UserRoles(teacher, Role.TEACHER));
        userRolesRepository.save(new UserRoles(student, Role.STUDENT));
        userRolesRepository.save(new UserRoles(headTeacher, Role.DEPARTMENT_HEAD));
        userRolesRepository.save(new UserRoles(headTeacher, Role.TEACHER));
    }
}