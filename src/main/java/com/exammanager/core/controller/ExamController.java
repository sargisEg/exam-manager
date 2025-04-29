package com.exammanager.core.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.ExamFacade;
import com.exammanager.core.model.dto.request.CreateExamRequestDto;
import com.exammanager.core.model.dto.request.GradeExamRequestDto;
import com.exammanager.core.model.dto.request.UpdateExamRequestDto;
import com.exammanager.core.model.dto.response.ExamDto;
import com.exammanager.core.model.enums.ExamStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1")
@SuppressWarnings("unused")
public class ExamController {

    private final ExamFacade examFacade;

    @PostMapping("exams")
    @Secured("ROLE_TEACHER")
    ResponseEntity<ExamDto> createExam(@Valid @RequestBody CreateExamRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.createExam(userInfo, dto), HttpStatus.OK);
    }

    @PutMapping("/departments/{departmentId}/groups/{groupId}/exams/{examId}")
    @Secured("ROLE_TEACHER")
    ResponseEntity<ExamDto> updateExam(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("examId") String examId,
            @RequestBody UpdateExamRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.updateExam(userInfo, departmentId, groupId, examId, dto), HttpStatus.OK);
    }

    @PutMapping("/departments/{departmentId}/groups/{groupId}/exams/{examId}/grade")
    ResponseEntity<ExamDto> gradeExam(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("examId") String examId,
            @RequestBody GradeExamRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.gradeExam(userInfo, departmentId, groupId, examId, dto), HttpStatus.OK);
    }


    @GetMapping("/departments/{departmentId}/groups/{groupId}/exams/{examId}")
    ResponseEntity<ExamDto> getExam(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("examId") String examId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.getExam(userInfo, departmentId, groupId, examId), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<PagedModel<ExamDto>> getAllExamsInGroup(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.getExams(userInfo, departmentId, groupId, page, size), HttpStatus.OK);
    }

    @GetMapping("exams/me")
    ResponseEntity<List<ExamDto>> getExamsMe(
            @RequestParam(value = "status", required = false) ExamStatus status
    ) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.getExamsMe(userInfo, status), HttpStatus.OK);
    }

    @GetMapping("/departments/{departmentId}/groups/{groupId}/exams/subgroups/{subgroupId}")
    @Secured("ROLE_TEACHER")
    ResponseEntity<PagedModel<ExamDto>> getAllExamsBySubgroupId(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("subgroupId") String subgroupId,
            @RequestParam("status") ExamStatus status, @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.getExamsBySubgroupId(userInfo, departmentId, groupId, subgroupId, status, page, size), HttpStatus.OK);
    }

    @GetMapping("/departments/{departmentId}/groups/{groupId}/exams/courses/{courseId}")
    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    ResponseEntity<PagedModel<ExamDto>> getAllExamsByCourseId(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("courseId") String courseId,
            @RequestParam("status") ExamStatus status, @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.getExamsByCourseId(userInfo, departmentId, groupId, courseId, status, page, size), HttpStatus.OK);
    }

    @GetMapping("/departments/{departmentId}/groups/{groupId}/exams/students/{studentId}")
    @Secured("ROLE_ADMIN")
    ResponseEntity<PagedModel<ExamDto>> getAllExamsByStudentId(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("studentId") String studentId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.getExamsByStudentId(userInfo, departmentId, groupId, studentId, page, size), HttpStatus.OK);
    }

    @DeleteMapping("/departments/{departmentId}/groups/{groupId}/exams/{examId}")
    @Secured("ROLE_TEACHER")
    ResponseEntity<Void> deleteExam(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("examId") String examId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        examFacade.deleteExam(userInfo, departmentId, groupId, examId);
        return ResponseEntity.ok().build();
    }
}
