package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.CourseFacade;
import com.exammanager.core.mapper.CourseMapper;
import com.exammanager.core.model.dto.request.CreateCourseRequestDto;
import com.exammanager.core.model.dto.response.CourseDto;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.entity.Teacher;
import com.exammanager.core.service.core.CourseService;
import com.exammanager.core.service.core.GroupService;
import com.exammanager.core.service.core.StudentService;
import com.exammanager.core.service.core.TeacherService;
import com.exammanager.utils.GroupUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CourseFacadeImpl implements CourseFacade {

    private final CourseService courseService;
    private final GroupService groupService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    private final CourseMapper courseMapper;

    @Override
    @Transactional
    public CourseDto createCourse(UserInfo userInfo, CreateCourseRequestDto dto) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(dto, "dto should not be null");
        log.debug("Crating course for provided request - {}, user - {}, group - {}", dto, userInfo.id(), dto.getGroupId());

        final Group group = getGroupById(dto.getGroupId());
        final Teacher teacher = getTeacherById(dto.getTeacherId());

        final CourseDto responseDto = courseMapper.map(courseService.create(courseMapper.map(group, teacher, dto)));

        log.debug("Successfully created course for provided request - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDto> getCoursesByGroupId(UserInfo userInfo, String groupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.trace("Finding courses by group id {} -  for provided request, user - {}", groupId, userInfo.id());

        final List<CourseDto> responseDto = courseService.findByGroupId(groupId).stream()
                .filter(c -> c.getSemester().equals(GroupUtils.getGroupSemester(c.getGroup())))
                .map(courseMapper::map)
                .toList();

        log.trace("Successfully found courses by group id - {}, response - {}", groupId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public void deleteCourse(UserInfo userInfo, String courseId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(courseId, "courseId should not be null");
        log.debug("Deleting course by id - {}, user - {}", courseId, userInfo.id());

        courseService.deleteById(courseId);

        log.debug("Successfully deleted course by id - {}", courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDto> getCoursesByTeacherId(UserInfo userInfo, String teacherId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.trace("Finding courses by teacher id {} -  for provided request, user - {}", teacherId, userInfo.id());

        final List<CourseDto> responseDto = courseService.findByTeacherId(teacherId).stream()
                .filter(c -> c.getSemester().equals(GroupUtils.getGroupSemester(c.getGroup())))
                .map(courseMapper::map)
                .toList();

        log.trace("Successfully found courses by teacher id - {}, response - {}", teacherId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDto> getCoursesByGroupIdAndTeacherId(UserInfo userInfo, String groupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.trace("Finding courses by teacher id - {} and group id - {} for provided request", userInfo.id(), groupId);

        final List<CourseDto> responseDto = courseService.findByTeacherId(userInfo.id()).stream()
                .filter(c -> groupId == null || c.getGroup().getId().equals(groupId))
                .filter(c -> c.getSemester().equals(GroupUtils.getGroupSemester(c.getGroup())))
                .map(courseMapper::map)
                .toList();

        log.trace("Successfully found courses by teacher id - {} and group id - {} for provided request, response - {}", userInfo.id(), groupId, responseDto);
        return responseDto;
    }

    @Override
    public CourseDto getCourseByIdAndTeacherId(UserInfo userInfo, String courseId) {
        Assert.hasText(courseId, "courseId should not be null");
        Assert.notNull(userInfo, "userInfo should not be null");
        log.trace("Finding course by id - {} and teacher id - {} for provided request", courseId, userInfo.id());

        final CourseDto courseDto = courseService.findByIdAndTeacherId(courseId, userInfo.id())
                .filter(c -> c.getSemester().equals(GroupUtils.getGroupSemester(c.getGroup())))
                .map(courseMapper::map)
                .orElseThrow(() -> new NotFoundException(
                        "Not found course with id - " + courseId,
                        "Not found course with id - " + courseId
                ));

        log.trace("Successfully found course by id - {} and teacher id - {} for provided request", courseId, userInfo.id());
        return courseDto;
    }

    private Group getGroupById(String groupId) {
        return groupService.findById(groupId).orElseThrow(() ->
                new NotFoundException(
                        "Not found group with id - " + groupId,
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
