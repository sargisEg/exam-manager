package com.exammanager.core.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.StudentFacade;
import com.exammanager.core.model.dto.response.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/students")
@SuppressWarnings("unused")
public class StudentController {

    private final StudentFacade studentFacade;

    @GetMapping()
    ResponseEntity<List<StudentDto>> getStudentsByGroupIdAndTeacherId(@RequestParam(value = "groupId") String groupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(studentFacade.getStudentsByGroupIdAndTeacherId(userInfo, groupId), HttpStatus.OK);
    }

    @GetMapping("{studentId}")
    @Secured("ROLE_ADMIN")
    ResponseEntity<StudentDto> getStudentById(@PathVariable(value = "studentId") String studentId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(studentFacade.getStudent(userInfo, studentId), HttpStatus.OK);
    }


    @GetMapping("me")
    @Secured({"ROLE_STUDENT"})
    ResponseEntity<StudentDto> me() {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(studentFacade.getStudent(userInfo, userInfo.id()), HttpStatus.OK);
    }
}
