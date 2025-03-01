package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.StudentFacade;
import com.exammanager.core.mapper.StudentMapper;
import com.exammanager.core.model.dto.request.CreateStudentRequestDto;
import com.exammanager.core.model.dto.request.StudentRequestFilter;
import com.exammanager.core.model.dto.response.StudentDto;
import com.exammanager.core.model.entity.Student;
import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.service.core.StudentService;
import com.exammanager.core.service.core.SubgroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;
    private final SubgroupService subgroupService;
    private final StudentMapper studentMapper;

    @Override
    @Transactional
    public StudentDto createStudent(UserInfo userInfo, CreateStudentRequestDto dto) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(dto, "dto should not be null");
        log.debug("Creating student for provided request - {}, user - {}", dto, userInfo.id());

        final Subgroup subgroup = subgroupService.findById(dto.getSubgroupId()).orElseThrow(() ->
                new NotFoundException(
                        "Not found subgroup with id - " + dto.getSubgroupId(),
                        "Not found subgroup with id - " + dto.getSubgroupId()
                ));

        final StudentDto responseDto = studentMapper.map(studentService.create(studentMapper.map(dto, subgroup)));

        log.debug("Successfully created student for provided request - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public PagedModel<StudentDto> getAllStudents(UserInfo userInfo, StudentRequestFilter filter, int page, int size) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(filter, "filter should not be null");
        log.trace("Getting students page for provided request, user - {}", userInfo.id());

        final Page<Student> students = studentService.findAll(filter, page, size);
        final Page<StudentDto> p = students
                .map(studentMapper::map);
        PagedModel<StudentDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got students page for provided request, response - {}", responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDto> getAllStudents(UserInfo userInfo, StudentRequestFilter filter) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(filter, "filter should not be null");
        log.trace("Getting students for provided request, user - {}", userInfo.id());

        final List<StudentDto> responseDto = studentService.findAll(filter)
                .stream()
                .map(studentMapper::map).toList();

        log.trace("Successfully got students for provided request, response - {}", responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDto getStudent(UserInfo userInfo, String studentId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(studentId, "studentId should not be null");
        log.trace("Getting student with id - {}", studentId);

        final Student student = studentService.findById(studentId).orElseThrow(() -> new NotFoundException(
                "Not found student with id - " + studentId,
                "Not found student with id - " + studentId
        ));
        final StudentDto responseDto = studentMapper.map(student);

        log.trace("Successfully got student with id - {}, response - {}", studentId, responseDto);
        return responseDto;
    }
}
