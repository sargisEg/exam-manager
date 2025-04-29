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

    @GetMapping("courses/{courseId}/exams")
    @Secured({"ROLE_TEACHER"})
    ResponseEntity<List<ExamDto>> getAllExamsByCourseId(
            @PathVariable("courseId") String courseId,
            @RequestParam("graded") boolean graded
    ) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();

        final List<ExamDto> response = graded ?
                examFacade.getUpcomingExamsByCourseId(userInfo, courseId) :
                examFacade.getNotGradedExamsByCourseId(userInfo, courseId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("exams/{examId}")
    @Secured("ROLE_TEACHER")
    ResponseEntity<ExamDto> updateExam(@PathVariable("examId") String examId, @RequestBody UpdateExamRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.updateExam(userInfo, examId, dto), HttpStatus.OK);
    }

    @DeleteMapping("exams/{examId}")
    @Secured("ROLE_TEACHER")
    ResponseEntity<Void> deleteExam(@PathVariable("examId") String examId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        examFacade.deleteExam(userInfo, examId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("exams")
    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    ResponseEntity<List<ExamDto>> getAllExams() {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();

        final List<ExamDto> response = examFacade.getAllUpcomingExams(userInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("exams/{examId}")
    @Secured("ROLE_TEACHER")
    ResponseEntity<ExamDto> getExam(@PathVariable("examId") String examId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.getExam(userInfo, examId), HttpStatus.OK);
    }

    @PutMapping("exams/{examId}/grade")
    ResponseEntity<ExamDto> gradeExam(
            @PathVariable("examId") String examId,
            @RequestBody GradeExamRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.gradeExam(userInfo, examId, dto), HttpStatus.OK);
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
}
