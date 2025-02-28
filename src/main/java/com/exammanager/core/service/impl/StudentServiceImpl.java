package com.exammanager.core.service.impl;

import com.exammanager.common.service.UuidProvider;
import com.exammanager.core.mapper.StudentMapper;
import com.exammanager.core.model.dto.request.StudentRequestFilter;
import com.exammanager.core.model.entity.Student;
import com.exammanager.core.model.params.CreateStudentParams;
import com.exammanager.core.repository.StudentRepository;
import com.exammanager.core.service.core.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final UuidProvider uuidProvider;

    @Override
    public Student create(CreateStudentParams params) {
        log.trace("Creating student with params - {}", params);

        Student studentFromParams = studentMapper.map(params);
        studentFromParams.setId(uuidProvider.getUuid());
        final Student student = studentRepository.save(studentFromParams);
        log.trace("Successfully created student with params - {}, result - {}", params, student);
        return student;
    }

    @Override
    public Page<Student> findAll(StudentRequestFilter filter, int page, int size) {
        log.trace("Finding all students page with filter - {}", filter);

        final Page<Student> students = filter.getFilterType().findByFilter(studentRepository, PageRequest.of(page, size));

        log.trace("Successfully found all students page with filter - {}, result - {}", filter, students);
        return students;
    }

    @Override
    public List<Student> findAll(StudentRequestFilter filter) {
        log.trace("Finding all students with filter - {}", filter);

        final List<Student> students = filter.getFilterType().findByFilter(studentRepository);

        log.trace("Successfully found all students with filter - {}, result - {}", filter, students);
        return students;
    }
}
