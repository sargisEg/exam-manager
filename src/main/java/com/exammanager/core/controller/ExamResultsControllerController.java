package com.exammanager.core.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.ExamResultFacade;
import com.exammanager.core.model.dto.response.ExamResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/departments/{departmentId}/groups/{groupId}/exam-results")
@SuppressWarnings("unused")
public class ExamResultsControllerController {

    private final ExamResultFacade examResultFacade;

    @GetMapping("page")
    ResponseEntity<PagedModel<ExamResultDto>> getAllExamResults(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examResultFacade.getAllByGroupId(userInfo, departmentId, groupId, page, size), HttpStatus.OK);
    }

    @GetMapping("subgroups/{subgroupId}")
    ResponseEntity<PagedModel<ExamResultDto>> getAllExamResultsBySubgroupId(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("subgroupId") String subgroupId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examResultFacade.getAllBySubgroupId(userInfo, departmentId, groupId, subgroupId, page, size), HttpStatus.OK);
    }

    @GetMapping("courses/{courseId}")
    ResponseEntity<PagedModel<ExamResultDto>> getAllExamResultsByCourseId(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("courseId") String courseId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examResultFacade.getAllByCourseId(userInfo, departmentId, groupId, courseId, page, size), HttpStatus.OK);
    }

    @GetMapping("students/{studentId}")
    @Secured("ROLE_ADMIN")
    ResponseEntity<PagedModel<ExamResultDto>> getAllExamResultsByStudentId(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("studentId") String studentId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(examResultFacade.getAllByStudentId(userInfo, departmentId, groupId, studentId, page, size), HttpStatus.OK);
    }
}
