package com.exammanager.core.repository;

import com.exammanager.core.model.entity.Teacher;
import com.exammanager.user.model.enums.Role;
import com.exammanager.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface TeacherRepository extends UserRepository<Teacher> {

    Page<Teacher> findAllByRoleNot(PageRequest pageRequest, Role role);
}
