package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.ExamFacade;
import com.exammanager.core.mapper.ExamMapper;
import com.exammanager.core.model.dto.request.CreateExamRequestDto;
import com.exammanager.core.model.dto.request.UpdateExamRequestDto;
import com.exammanager.core.model.dto.response.ExamDto;
import com.exammanager.core.model.entity.*;
import com.exammanager.core.model.enums.ExamStatus;
import com.exammanager.core.service.core.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExamFacadeImpl implements ExamFacade {

    private final ExamService examService;
    private final DepartmentService departmentService;
    private final GroupService groupService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final ExamMapper examMapper;

    @Override
    public ExamDto createExam(UserInfo userInfo, String departmentId, String groupId, CreateExamRequestDto dto) {
        return null;
    }

    @Override
    public ExamDto updateExam(UserInfo userInfo, String departmentId, String groupId, String examId, UpdateExamRequestDto dto) {
        return null;
    }

    @Override
    public ExamDto getExam(UserInfo userInfo, String departmentId, String groupId, String examId) {
        return null;
    }

    @Override
    public PagedModel<ExamDto> getExams(UserInfo userInfo, String departmentId, String groupId, int page, int size) {
        return null;
    }

    @Override
    public void deleteExam(UserInfo userInfo, String departmentId, String groupId, String examId) {

    }

    @Override
    @Transactional(readOnly = true)
    public PagedModel<ExamDto> getExamsByStudentId(UserInfo userInfo, String departmentId, String groupId, String studentId, int page, int size) {
        log.trace("Getting exams for student - {}, for provided request, user - {}", studentId, userInfo.id());

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);
        final Student student = getStudentById(studentId);

        final Page<ExamDto> p = examService.findBySubgroupIdAndStatus(student.getSubgroup().getId(), ExamStatus.UPCOMING, page, size)
                .map(examMapper::map);
        final PagedModel<ExamDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got exams for student - {}, for provided request, response - {}", studentId, responseDto);
        return responseDto;
    }

    @Override
    public PagedModel<ExamDto> getExamsByCourseId(UserInfo userInfo, String departmentId, String groupId, String courseId, ExamStatus status, int page, int size) {
        log.trace("Getting exams for course - {}, for provided request, user - {}", courseId, userInfo.id());

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);

        final Page<ExamDto> p = examService.findByCourseIdAndStatus(courseId, status, page, size)
                .map(examMapper::map);
        final PagedModel<ExamDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got exams for course - {}, for provided request, response - {}", courseId, responseDto);
        return responseDto;
    }

    @Override
    public PagedModel<ExamDto> getExamsBySubgroupId(UserInfo userInfo, String departmentId, String groupId, String subgroupId, ExamStatus status, int page, int size) {
        log.trace("Getting exams for subgroup - {}, for provided request, user - {}", subgroupId, userInfo.id());

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);

        final Page<ExamDto> p = examService.findBySubgroupIdAndStatus(subgroupId, status, page, size)
                .map(examMapper::map);
        final PagedModel<ExamDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got exams for subgroup - {}, for provided request, response - {}", subgroupId, responseDto);
        return responseDto;
    }

    @Override
    public List<ExamDto> getExamsMe(UserInfo userInfo) {
        log.trace("Getting exams for user - {}, for provided request", userInfo.id());

        final Optional<Teacher> optionalTeacher = teacherService.findById(userInfo.id());
        if (optionalTeacher.isPresent()) {
            final List<Course> courses = courseService.findByTeacherId(userInfo.id());
            if (!courses.isEmpty()) {
                final List<ExamDto> responseDto = courses.stream()
                        .map(Course::getId)
                        .flatMap((Function<String, Stream<Exam>>) s -> examService.findByCourseId(s).stream())
                        .map(examMapper::map)
                        .toList();
                log.trace("Successfully got exams for user - {}, for provided request, response - {}", userInfo.id(), responseDto);
                return responseDto;
            }
        }
        final Student student = getStudentById(userInfo.id());
        final String subgroupId = student.getSubgroup().getId();


        final List<ExamDto> responseDto = examService.findBySubgroupId(subgroupId).stream()
                .map(examMapper::map)
                .toList();

        log.trace("Successfully got exams for user - {}, for provided request, response - {}", userInfo.id(), responseDto);
        return responseDto;
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

    private Student getStudentById(String studentId) {
        return studentService.findById(studentId).orElseThrow(() ->
                new NotFoundException(
                        "Not found student with id - " + studentId,
                        "Not found student with id - " + studentId
                ));
    }
}
