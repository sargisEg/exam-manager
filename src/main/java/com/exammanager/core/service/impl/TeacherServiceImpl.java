package com.exammanager.core.service.impl;

import com.exammanager.core.model.entity.Teacher;
import com.exammanager.core.repository.TeacherRepository;
import com.exammanager.core.service.core.TeacherService;
import com.exammanager.user.model.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public Page<Teacher> findAll(int page, int size) {
        log.trace("Getting all teachers");

        final Page<Teacher> teachers = teacherRepository.findAllByRoleNot(PageRequest.of(page, size), Role.ADMIN);

        log.trace("Successfully got all teachers, result - {}", teachers);
        return teachers;
    }
}
