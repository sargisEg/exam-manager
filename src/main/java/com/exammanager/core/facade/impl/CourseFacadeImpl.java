package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.CourseFacade;
import com.exammanager.core.mapper.CourseMapper;
import com.exammanager.core.model.dto.request.CreateCourseRequestDto;
import com.exammanager.core.model.dto.response.CourseDto;
import com.exammanager.core.model.entity.Course;
import com.exammanager.core.model.entity.Department;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.entity.Teacher;
import com.exammanager.core.service.core.CourseService;
import com.exammanager.core.service.core.DepartmentService;
import com.exammanager.core.service.core.GroupService;
import com.exammanager.core.service.core.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Slf4j
@Component
@RequiredArgsConstructor
public class CourseFacadeImpl implements CourseFacade {

    private final CourseService courseService;
    private final DepartmentService departmentService;
    private final GroupService groupService;
    private final TeacherService teacherService;

    private final CourseMapper courseMapper;

    @Override
    @Transactional
    public CourseDto createCourse(UserInfo userInfo, String departmentId, String groupId, CreateCourseRequestDto dto) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        Assert.notNull(dto, "dto should not be null");
        log.debug("Crating course for provided request - {}, user - {}, group - {}", dto, userInfo.id(), groupId);

        getDepartmentById(departmentId);
        final Group group = getGroupById(groupId, departmentId);
        final Teacher teacher = getTeacherById(dto.getTeacherId());

        final CourseDto responseDto = courseMapper.map(courseService.create(courseMapper.map(group, teacher, dto)));

        log.debug("Successfully created course for provided request - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDto getCourse(UserInfo userInfo, String departmentId, String groupId, String courseId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        Assert.hasText(courseId, "courseId should not be null");
        log.debug("Getting course with id - {}, user - {}, group - {}", courseId, userInfo.id(), groupId);

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);

        final Course course = courseService.findByIdAndGroupId(courseId, groupId).orElseThrow(() ->
                new NotFoundException(
                        "Not found course with id - " + courseId + " and group id - " + groupId,
                        "Not found course with id - " + courseId
                ));

        final CourseDto responseDto = courseMapper.map(course);

        log.debug("Successfully got course with id - {}, response - {}", courseId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public PagedModel<CourseDto> getCourses(UserInfo userInfo, String departmentId, String groupId, int page, int size) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        log.trace("Getting courses in group - {}, user - {}", groupId, userInfo.id());

        getDepartmentById(departmentId);

        final Page<CourseDto> p = courseService.findByGroupId(groupId, page, size)
                .map(courseMapper::map);
        final PagedModel<CourseDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got courses in group - {}, response - {}", groupId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public void deleteCourse(UserInfo userInfo, String departmentId, String groupId, String courseId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        log.debug("Deleting course by id - {}, in group - {}, user - {}", courseId, groupId, userInfo.id());

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);

        courseService.deleteByIdAndGroupId(courseId, groupId);

        log.debug("Successfully deleted course by id - {}", courseId);
    }

    private Department getDepartmentById(String departmentId) {
        return departmentService.findById(departmentId).orElseThrow(() ->
                new NotFoundException(
                        "Not found department with id - " + departmentId,
                        "Not found department with id - " + departmentId
                ));
    }

    private Group getGroupById(String groupId, String departmentId) {
        return groupService.findByIdAndDepartmentId(groupId, departmentId).orElseThrow(() ->
                new NotFoundException(
                        "Not found group with id - " + groupId + "and department id - " + departmentId,
                        "Not found group with id - " + groupId
                ));
    }

    private Teacher getTeacherById(String teacherId) {
        return teacherService.findById(teacherId).orElseThrow(() ->
                new NotFoundException(
                        "Not found teacher with id - " + teacherId,
                        "Not found teacherId with id - " + teacherId
                ));

    }
}
