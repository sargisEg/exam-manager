package com.exammanager.core.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.ExamFacade;
import com.exammanager.core.model.dto.request.CreateExamRequestDto;
import com.exammanager.core.model.dto.request.UpdateExamRequestDto;
import com.exammanager.core.model.dto.response.ExamDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/departments/{departmentId}/groups/{groupId}/exams")
public class ExamController {

    private final ExamFacade examFacade;

    @PostMapping
    ResponseEntity<ExamDto> createExam(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @Valid @RequestBody CreateExamRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.createExam(userInfo, departmentId, groupId, dto), HttpStatus.OK);
    }

    @PutMapping("{examId}")
    ResponseEntity<ExamDto> updateExam(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("examId") String examId,
            @RequestBody UpdateExamRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.updateExam(userInfo, departmentId, groupId, examId), HttpStatus.OK);
    }

    @GetMapping("{examId}")
    ResponseEntity<ExamDto> getExam(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("examId") String examId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.getExam(userInfo, departmentId, groupId, examId), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<Page<ExamDto>> getAllExamsInGroup(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @RequestParam("keyword") String keyword, @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examFacade.getExams(userInfo, departmentId, groupId, keyword, page, size), HttpStatus.OK);
    }

    @DeleteMapping("{examId}")
    ResponseEntity<Void> deleteExam(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("examId") String examId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        examFacade.deleteExam(userInfo, departmentId, groupId, examId);
        return ResponseEntity.ok().build();
    }
}
