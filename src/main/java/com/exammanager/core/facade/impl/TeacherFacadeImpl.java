package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.TeacherFacade;
import com.exammanager.core.mapper.CourseMapper;
import com.exammanager.core.mapper.GroupMapper;
import com.exammanager.core.mapper.TeacherMapper;
import com.exammanager.core.model.dto.request.CreateTeacherRequestDto;
import com.exammanager.core.model.dto.response.CourseDto;
import com.exammanager.core.model.dto.response.TeacherDto;
import com.exammanager.core.model.entity.Teacher;
import com.exammanager.core.service.core.CourseService;
import com.exammanager.core.service.core.SubgroupService;
import com.exammanager.core.service.core.TeacherService;
import com.exammanager.user.mapper.UserMapper;
import com.exammanager.user.model.dto.response.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeacherFacadeImpl implements TeacherFacade {

    private final TeacherService teacherService;
    private final CourseService courseService;
    private final TeacherMapper teacherMapper;
    private final UserMapper userMapper;
    private final SubgroupService subgroupService;
    private final GroupMapper groupMapper;
    private final CourseMapper courseMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public PagedModel<UserDto> getAllTeachers(UserInfo userInfo, int page, int size) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.trace("Getting teachers page for provided request, user - {}", userInfo.id());

        final Page<Teacher> teachers = teacherService.findAll(page, size);
        final Page<UserDto> p = teachers
                .map(userMapper::map);
        PagedModel<UserDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got teachers page for provided request, response - {}", responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public UserDto createTeacher(UserInfo userInfo, CreateTeacherRequestDto dto) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(dto, "dto should not be null");
        log.debug("Creating teacher for provided request - {}, user - {}", dto, userInfo.id());

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        final UserDto responseDto = userMapper.map(teacherService.create(teacherMapper.map(dto)));

        log.debug("Successfully created teacher for provided request, response - {}", responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllTeachers(UserInfo userInfo) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.trace("Getting all teacher for provided request, user - {}", userInfo.id());

        final List<UserDto> responseDto = teacherService.findAll().stream()
                .map(userMapper::map)
                .toList();

        log.trace("Successfully got all teachers for provided request, response - {}", responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherDto getTeacherById(UserInfo userInfo, String teacherId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(teacherId, "teacherId should not be null");
        log.trace("Finding teacher with id - {}, user - {}", teacherId, userInfo.id());

        final Teacher teacher = teacherService.findById(teacherId).orElseThrow(() ->
            new NotFoundException(
                    "Not found teacher with id - " + teacherId,
                    "Not found teacher with id - " + teacherId
            ));

        final List<CourseDto> courses = courseService.findByTeacherId(teacherId).stream()
                .map(courseMapper::map)
                .toList();

        final TeacherDto responseDto = new TeacherDto(
                userMapper.map(teacher),
                courses
        );


        log.trace("Successfully found teacher with id - {}, response - {}", teacherId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public void deleteTeacherById(UserInfo userInfo, String teacherId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(teacherId, "teacherId should not be null");
        log.trace("Deleting teacher with id - {}, user - {}", teacherId, userInfo.id());

        courseService.deleteByTeacherId(teacherId);
        teacherService.deleteById(teacherId);

        log.trace("Successfully deleted teacher with id - {}", teacherId);
    }
}
