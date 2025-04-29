package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.ConflictException;
import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.ExamFacade;
import com.exammanager.core.mapper.ExamMapper;
import com.exammanager.core.model.dto.request.CreateExamRequestDto;
import com.exammanager.core.model.dto.request.GradeExamRequestDto;
import com.exammanager.core.model.dto.request.UpdateExamRequestDto;
import com.exammanager.core.model.dto.response.ExamDto;
import com.exammanager.core.model.entity.*;
import com.exammanager.core.model.enums.ExamStatus;
import com.exammanager.core.service.core.*;
import com.exammanager.user.model.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExamFacadeImpl implements ExamFacade {

    private final ExamService examService;
    private final GroupService groupService;
    private final SubgroupService subgroupService;
    private final CourseService courseService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ExamResultService examResultService;
    private final ExamMapper examMapper;

    @Override
    @Transactional
    public ExamDto createExam(UserInfo userInfo, CreateExamRequestDto dto) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(dto, "dto should not be null");
        log.debug("Creating exam for provided request - {}", dto);

        final Course course = courseService.findByIdAndTeacherId(dto.getCourseId(), userInfo.id())
                .orElseThrow(() -> new NotFoundException(
                        "Not found course with id - " + dto.getCourseId(),
                        "Not found course with id - " + dto.getCourseId()
                ));

        final Subgroup subgroup = subgroupService.findByIdAndGroupId(dto.getSubgroupId(), course.getGroup().getId())
                .orElseThrow(() -> new NotFoundException(
                        "Not found subgroup with id - " + dto.getSubgroupId(),
                        "Not found subgroup with id - " + dto.getSubgroupId()
                ));

        final Exam exam = examService.create(examMapper.map(dto, course, subgroup));
        final ExamDto responseDto = examMapper.map(exam);

        log.debug("Successfully created exam for provided request - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamDto> getUpcomingExamsByCourseId(UserInfo userInfo, String courseId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(courseId, "courseId should not be null");
        log.trace("Getting upcoming exams for course - {}, for provided request, user - {}", courseId, userInfo.id());

        courseService.findByIdAndTeacherId(courseId, userInfo.id())
                .orElseThrow(() -> new NotFoundException(
                        "Not found course with id - " + courseId,
                        "Not found course with id - " + courseId
                ));

        final List<ExamDto> responseDto = examService.findByCourseIdAndStatus(courseId, ExamStatus.UPCOMING).stream()
                .map(examMapper::map)
                .toList();

        log.trace("Successfully got upcoming exams for course - {}, for provided request, response - {}", courseId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamDto> getNotGradedExamsByCourseId(UserInfo userInfo, String courseId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(courseId, "courseId should not be null");
        log.trace("Getting not graded exams for course - {}, for provided request, user - {}", courseId, userInfo.id());

        courseService.findByIdAndTeacherId(courseId, userInfo.id())
                .orElseThrow(() -> new NotFoundException(
                        "Not found course with id - " + courseId,
                        "Not found course with id - " + courseId
                ));

        final List<ExamDto> responseDto = examService.findByCourseIdAndNotGraded(courseId).stream()
                .map(examMapper::map)
                .toList();

        log.trace("Successfully got not graded exams for course - {}, for provided request, response - {}", courseId, responseDto);
        return responseDto;
    }


    @Override
    @Transactional
    public ExamDto updateExam(UserInfo userInfo, String examId, UpdateExamRequestDto dto) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(examId, "examId should not be null");
        Assert.notNull(dto, "dto should not be null");
        log.debug("Updating exam with id - {} for provided request - {}, user - {}", examId, dto, userInfo.id());

        final Exam exam = examService.findById(examId)
                .orElseThrow(() -> new NotFoundException(
                        "Not found exam with id - " + examId,
                        "Not found exam with id - " + examId
                ));

        courseService.findByIdAndTeacherId(exam.getCourse().getId(), userInfo.id())
                .orElseThrow(() -> new NotFoundException(
                        "Not found exam with id - " + examId,
                        "Not found exam with id - " + examId
                ));

        final ExamDto responseDto = examService.update(examMapper.map(examId, dto))
                .map(examMapper::map)
                .orElseThrow(() -> new NotFoundException(
                        "Not found exam with id - " + examId,
                        "Not found exam with id - " + examId
                ));

        log.debug("Successfully updated exam with id - {} for provided request - {}, response - {}", examId, dto, responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public void deleteExam(UserInfo userInfo, String examId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(examId, "examId should not be null");
        log.debug("Removing exam - {} for provided request user - {}", examId, userInfo.id());

        final Optional<Exam> optionalExam = examService.findById(examId);

        if (optionalExam.isEmpty()) {
            return;
        }

        if (courseService.findByIdAndTeacherId(optionalExam.get().getCourse().getId(), userInfo.id()).isEmpty()) {
            return;
        }

        examService.deleteById(examId);

        log.debug("Successfully removed exam - {} for provided request", examId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamDto> getAllUpcomingExams(UserInfo userInfo) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.trace("Getting all exams for user - {} for provided request", userInfo.id());

        final List<ExamDto> responseDto;

        if (Role.STUDENT.equals(userInfo.role())) {
            final Optional<Student> optionalStudent = studentService.findById(userInfo.id());
            if (optionalStudent.isEmpty()) {
                return Collections.emptyList();
            }
            final String subgroupId = optionalStudent.get().getSubgroup().getId();
            responseDto = examService.findBySubgroupId(subgroupId).stream()
                    .filter(e -> ExamStatus.UPCOMING.equals(e.getStatus()))
                    .map(examMapper::map)
                    .toList();
        } else {
            responseDto = courseService.findByTeacherId(userInfo.id()).stream()
                    .map(c -> examService.findByCourseId(c.getId()))
                    .flatMap(Collection::stream)
                    .filter(e -> ExamStatus.UPCOMING.equals(e.getStatus()) || (ExamStatus.FINISHED.equals(e.getStatus()) && !e.getIsGraded()))
                    .map(examMapper::map)
                    .toList();
        }

        log.trace("Successfully got all exams for user - {} for provided request, response - {}", userInfo.id(), responseDto);
        return responseDto;
    }



    @Override
    @Transactional
    public ExamDto gradeExam(UserInfo userInfo, String examId, GradeExamRequestDto dto) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(examId, "examId should not be null");
        Assert.notNull(dto, "dto should not be null");
        log.debug("Grading exam for provided request - {}, user - {}", dto, userInfo.id());

        final Exam exam = examService.findById(examId)
                .orElseThrow(() -> new NotFoundException(
                        "Not found exam with id - " + examId,
                        "Not found exam with id - " + examId
                ));
        if (!exam.getCourse().getTeacher().getId().equals(userInfo.id())) {
            throw new NotFoundException(
                    "Not found exam with id - " + examId,
                    "Not found exam with id - " + examId
            );
        }

        final Subgroup subgroup = exam.getSubgroup();
        final List<Student> students = studentService.findBySubgroupId(subgroup.getId());
        final Map<String, Integer> grades = dto.getGrades();
        if (grades.size() != students.size()) {
            throw new ConflictException(
                    String.format("Failed to grade all students, exam - %s, user - %s", examId, userInfo.id()),
                    "Please grade all students"
            );
        }

        students.forEach(
                student -> examResultService.create(student, exam, grades.get(student.getId()))
        );
        examService.grade(examId);
        final ExamDto responseDto = examMapper.map(exam);

        log.debug("Successfully graded exam for provided request - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    public ExamDto getExam(UserInfo userInfo, String examId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.trace("Getting exam with id - {} for user - {} for provided request", examId, userInfo.id());

        final Exam exam = examService.findById(examId)
                .orElseThrow(() -> new NotFoundException(
                        "Not found exam with id - " + examId,
                        "Not found exam with id - " + examId
                ));
        if (!exam.getCourse().getTeacher().getId().equals(userInfo.id())) {
            throw new NotFoundException(
                    "Not found exam with id - " + examId,
                    "Not found exam with id - " + examId
            );
        }

        final ExamDto responseDto = examMapper.map(exam);

        log.trace("Successfully got exam with id - {} for user - {} for provided request, response - {}", examId, userInfo.id(), responseDto);
        return responseDto;
    }

    @Override
    public List<ExamDto> getExams(UserInfo userInfo, String groupId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public PagedModel<ExamDto> getExamsByStudentId(UserInfo userInfo, String departmentId, String groupId, String studentId, int page, int size) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        Assert.hasText(studentId, "studentId should not be null");
        log.trace("Getting exams for student - {}, for provided request, user - {}", studentId, userInfo.id());

        getGroupById(groupId, departmentId);
        final Student student = getStudentById(studentId);

        final Page<ExamDto> p = examService.findBySubgroupIdAndStatus(student.getSubgroup().getId(), ExamStatus.UPCOMING, page, size)
                .map(examMapper::map);
        final PagedModel<ExamDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got exams for student - {}, for provided request, response - {}", studentId, responseDto);
        return responseDto;
    }

    @Override
    public PagedModel<ExamDto> getExamsBySubgroupId(UserInfo userInfo, String departmentId, String groupId, String subgroupId, ExamStatus status, int page, int size) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        Assert.hasText(subgroupId, "subgroupId should not be null");
        Assert.notNull(status, "status should not be null");
        log.trace("Getting exams for subgroup - {}, for provided request, user - {}", subgroupId, userInfo.id());

        getGroupById(groupId, departmentId);

        final Page<ExamDto> p = examService.findBySubgroupIdAndStatus(subgroupId, status, page, size)
                .map(examMapper::map);
        final PagedModel<ExamDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got exams for subgroup - {}, for provided request, response - {}", subgroupId, responseDto);
        return responseDto;
    }

    @Override
    public List<ExamDto> getExamsMe(UserInfo userInfo, ExamStatus status) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.trace("Getting exams for user - {}, for provided request", userInfo.id());

        final Optional<Teacher> optionalTeacher = teacherService.findById(userInfo.id());
        if (optionalTeacher.isPresent()) {
            final List<Course> courses = courseService.findByTeacherId(userInfo.id());
            if (!courses.isEmpty()) {
                final List<ExamDto> responseDto = courses.stream()
                        .map(Course::getId)
                        .flatMap((Function<String, Stream<Exam>>) s -> examService.findByCourseId(s)
                                .stream().filter(exam -> status == null || exam.getStatus().equals(status)))
                        .map(examMapper::map)
                        .toList();
                log.trace("Successfully got exams for user - {}, for provided request, response - {}", userInfo.id(), responseDto);
                return responseDto;
            }
        }
        final Student student = getStudentById(userInfo.id());
        final String subgroupId = student.getSubgroup().getId();


        final List<ExamDto> responseDto = examService.findBySubgroupId(subgroupId).stream()
                .filter(exam -> status == null || exam.getStatus().equals(status))
                .map(examMapper::map)
                .toList();

        log.trace("Successfully got exams for user - {}, for provided request, response - {}", userInfo.id(), responseDto);
        return responseDto;
    }

    private void getGroupById(String groupId, String departmentId) {
        groupService.findById(groupId).orElseThrow(() ->
                new NotFoundException(
                        "Not found group with id - " + groupId + "and department id - " + departmentId,
                        "Not found group with id - " + groupId
                ));
    }

    private Subgroup getSubgroupById(String subgroupId, String groupId) {
        return subgroupService.findByIdAndGroupId(subgroupId, groupId).orElseThrow(() ->
                new NotFoundException(
                        "Not found subgroup with id - " + subgroupId + "and group id - " + groupId,
                        "Not found subgroup with id - " + subgroupId
                ));
    }

    private Course getCourseById(String courseId, String groupId) {
        return courseService.findByIdAndGroupId(courseId, groupId).orElseThrow(() ->
                new NotFoundException(
                        "Not found course with id - " + courseId + "and group id - " + groupId,
                        "Not found course with id - " + courseId
                ));
    }

    private Student getStudentById(String studentId) {
        return studentService.findById(studentId).orElseThrow(() ->
                new NotFoundException(
                        "Not found student with id - " + studentId,
                        "Not found student with id - " + studentId
                ));
    }

    private Exam getExamById(String examId) {
        return examService.findById(examId).orElseThrow(() ->
                new NotFoundException(
                        "Not found exam with id - " + examId,
                        "Not found exam with id - " + examId
                ));
    }
}
