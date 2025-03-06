package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.ExamResultFacade;
import com.exammanager.core.mapper.ExamMapper;
import com.exammanager.core.model.dto.response.ExamResultDto;
import com.exammanager.core.service.core.DepartmentService;
import com.exammanager.core.service.core.ExamResultService;
import com.exammanager.core.service.core.GroupService;
import com.exammanager.core.service.core.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExamResultFacadeImpl implements ExamResultFacade {

    private final ExamResultService examResultService;
    private final DepartmentService departmentService;
    private final GroupService groupService;
    private final StudentService studentService;
    private final ExamMapper examMapper;

    @Override
    public PagedModel<ExamResultDto> getAllByGroupId(UserInfo userInfo, String departmentId, String groupId, int page, int size) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public PagedModel<ExamResultDto> getAllByStudentId(UserInfo userInfo, String departmentId, String groupId, String studentId, int page, int size) {
        log.trace("Getting all exam result for student - {}, for provided request, user - {}", studentId, userInfo.id());

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);
        getStudentById(studentId);

        final Page<ExamResultDto> p = examResultService.findByStudentId(studentId, page, size)
                .map(examMapper::map);
        final PagedModel<ExamResultDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got all exam result for student - {}, for provided request, response - {}", studentId, responseDto);
        return responseDto;
    }

    @Override
    public PagedModel<ExamResultDto> getAllBySubgroupId(UserInfo userInfo, String departmentId, String groupId, String subgroupId, int page, int size) {
        log.trace("Getting all exam result for subgroup - {}, for provided request, user - {}", subgroupId, userInfo.id());

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);


        final Page<ExamResultDto> p = examResultService.findBySubgroupId(subgroupId, page, size)
                .map(examMapper::map);
        final PagedModel<ExamResultDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got all exam result for subgroup - {}, for provided request, response - {}", subgroupId, responseDto);
        return responseDto;
    }

    @Override
    public PagedModel<ExamResultDto> getAllByCourseId(UserInfo userInfo, String departmentId, String groupId, String courseId, int page, int size) {
        log.trace("Getting all exam result for course - {}, for provided request, user - {}", courseId, userInfo.id());

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);


        final Page<ExamResultDto> p = examResultService.findByCourseId(courseId, page, size)
                .map(examMapper::map);
        final PagedModel<ExamResultDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got all exam result for course - {}, for provided request, response - {}", courseId, responseDto);
        return responseDto;
    }

    @Override
    public List<ExamResultDto> getAllByExamId(UserInfo userInfo, String departmentId, String groupId, String examId) {
        log.trace("Getting all exam result for exam - {}, for provided request, user - {}", examId, userInfo.id());

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);


        final List<ExamResultDto> responseDto = examResultService.findByExamId(examId).stream()
                .map(examMapper::map)
                .toList();

        log.trace("Successfully got all exam result for exam - {}, for provided request, response - {}", examId, responseDto);
        return responseDto;
    }

    @Override
    public PagedModel<ExamResultDto> getMyResults(UserInfo userInfo, String departmentId, String groupId, String courseId, int page, int size) {
        log.trace("Getting all exam result for user - {}, for provided request", userInfo.id());

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);

        final Page<ExamResultDto> p;
        if (courseId == null || courseId.isBlank()) {
            p = examResultService.findByStudentId(userInfo.id(), page, size)
                    .map(examMapper::map);
        } else {
            p = examResultService.findByStudentId(userInfo.id(), courseId, page, size)
                    .map(examMapper::map);
        }


        final PagedModel<ExamResultDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got all exam result for user - {}, for provided request, response - {}", userInfo.id(), responseDto);
        return responseDto;
    }

    private void getDepartmentById(String departmentId) {
        departmentService.findById(departmentId).orElseThrow(() ->
                new NotFoundException(
                        "Not found department with id - " + departmentId,
                        "Not found department with id - " + departmentId
                ));
    }

    private void getGroupById(String groupId, String departmentId) {
        groupService.findByIdAndDepartmentId(groupId, departmentId).orElseThrow(() ->
                new NotFoundException(
                        "Not found group with id - " + groupId + "and department id - " + departmentId,
                        "Not found group with id - " + groupId
                ));
    }

    private void getStudentById(String studentId) {
        studentService.findById(studentId).orElseThrow(() ->
                new NotFoundException(
                        "Not found student with id - " + studentId,
                        "Not found student with id - " + studentId
                ));
    }
}
