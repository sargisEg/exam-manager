package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.ExamFacade;
import com.exammanager.core.mapper.ExamMapper;
import com.exammanager.core.model.dto.request.CreateExamRequestDto;
import com.exammanager.core.model.dto.request.UpdateExamRequestDto;
import com.exammanager.core.model.dto.response.ExamDto;
import com.exammanager.core.model.entity.Department;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.entity.Student;
import com.exammanager.core.service.core.DepartmentService;
import com.exammanager.core.service.core.ExamService;
import com.exammanager.core.service.core.GroupService;
import com.exammanager.core.service.core.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExamFacadeImpl implements ExamFacade {

    private final ExamService examService;
    private final DepartmentService departmentService;
    private final GroupService groupService;
    private final StudentService studentService;
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

        final Page<ExamDto> p = examService.findBySubgroupId(student.getSubgroup().getId(), page, size)
                .map(examMapper::map);
        final PagedModel<ExamDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got exams for student - {}, for provided request, response - {}", studentId, responseDto);
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
