package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.StudentFacade;
import com.exammanager.core.mapper.StudentMapper;
import com.exammanager.core.model.dto.request.CreateStudentRequestDto;
import com.exammanager.core.model.dto.response.StudentDto;
import com.exammanager.core.model.entity.Student;
import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.service.core.CourseService;
import com.exammanager.core.service.core.StudentService;
import com.exammanager.core.service.core.SubgroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;
    private final SubgroupService subgroupService;
    private final CourseService courseService;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;

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

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        final StudentDto responseDto = studentMapper.map(studentService.create(studentMapper.map(dto, subgroup)));

        log.debug("Successfully created student for provided request - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDto getStudent(UserInfo userInfo, String studentId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(studentId, "studentId should not be null");
        log.trace("Finding student with id - {}", studentId);

        final Student student = studentService.findById(studentId).orElseThrow(() -> new NotFoundException(
                "Not found student with id - " + studentId,
                "Not found student with id - " + studentId
        ));
        final StudentDto responseDto = studentMapper.map(student);

        log.trace("Successfully found student with id - {}, response - {}", studentId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDto> getStudentsByGroupId(UserInfo userInfo, String groupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        log.trace("Finding students with group id - {} for provided request, user - {}", groupId, userInfo.id());

        final List<StudentDto> responseDto = studentService.findByGroupId(groupId).stream()
                .map(studentMapper::map)
                .toList();

        log.trace("Successfully found students with group id - {}, response - {}", groupId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public void removeStudent(UserInfo userInfo, String studentId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(studentId, "studentId should not be null");
        log.trace("Removing student with id - {} for provided request, user - {}", studentId, userInfo.id());
        studentService.deleteById(studentId);
        log.trace("Successfully removed student with id - {} for provided request", studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDto> getStudentsByGroupIdAndTeacherId(UserInfo userInfo, String groupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        log.trace("Finding students with group id - {} for teacher - {} for provided request", groupId, userInfo.id());

        final List<StudentDto> responseDto = courseService.findByTeacherId(userInfo.id()).stream()
                .map(c -> c.getGroup().getId())
                .distinct()
                .map(studentService::findByGroupId)
                .flatMap(Collection::stream)
                .map(studentMapper::map)
                .toList();

        log.trace("Successfully found students with group id - {} for teacher - {}, response - {}", groupId, userInfo.id(), responseDto);
        return responseDto;
    }
}
