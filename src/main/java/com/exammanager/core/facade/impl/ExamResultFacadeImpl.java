package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.ExamResultFacade;
import com.exammanager.core.mapper.ExamMapper;
import com.exammanager.core.model.dto.response.ExamResultDto;
import com.exammanager.core.model.entity.Department;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.entity.Student;
import com.exammanager.core.service.core.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExamResultFacadeImpl implements ExamResultFacade {

    private final ExamResultService examResultService;
//    private final ExamService examService;
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
