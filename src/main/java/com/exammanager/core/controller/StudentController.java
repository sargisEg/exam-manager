package com.exammanager.core.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.StudentFacade;
import com.exammanager.core.model.dto.request.CreateStudentRequestDto;
import com.exammanager.core.model.dto.request.StudentRequestFilter;
import com.exammanager.core.model.dto.response.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/students")
public class StudentController {

    private final StudentFacade studentFacade;

    @PostMapping()
    @Secured("ROLE_ADMIN")
    ResponseEntity<StudentDto> createStudent(@RequestBody CreateStudentRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(studentFacade.createStudent(userInfo, dto), HttpStatus.OK);
    }

    @GetMapping("page")
    @Secured("ROLE_ADMIN")
    ResponseEntity<PagedModel<StudentDto>> getAllStudents(
            @RequestParam(value = "department", required = false) String departmentId,
            @RequestParam(value = "group", required = false) String groupId,
            @RequestParam(value = "subgroup", required = false) String subgroupId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        final StudentRequestFilter filter = new StudentRequestFilter(departmentId, groupId, subgroupId);
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(studentFacade.getAllStudents(userInfo, filter, page, size), HttpStatus.OK);
    }

    @GetMapping()
    @Secured("ROLE_ADMIN")
    ResponseEntity<List<StudentDto>> getAllStudents(
            @RequestParam(value = "department", required = false) String departmentId,
            @RequestParam(value = "group", required = false) String groupId,
            @RequestParam(value = "subgroup", required = false) String subgroupId) {
        final StudentRequestFilter filter = new StudentRequestFilter(departmentId, groupId, subgroupId);
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(studentFacade.getAllStudents(userInfo, filter), HttpStatus.OK);
    }
}
