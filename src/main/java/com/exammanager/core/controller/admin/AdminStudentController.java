package com.exammanager.core.controller.admin;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.StudentFacade;
import com.exammanager.core.model.dto.request.CreateStudentRequestDto;
import com.exammanager.core.model.dto.response.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/admin/students")
@SuppressWarnings("unused")
@Secured("ROLE_ADMIN")
public class AdminStudentController {

    private final StudentFacade studentFacade;

    @GetMapping()
    ResponseEntity<List<StudentDto>> getStudentsByGroupId(@RequestParam(value = "groupId") String groupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(studentFacade.getStudentsByGroupId(userInfo, groupId), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<StudentDto> createStudent(@RequestBody CreateStudentRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(studentFacade.createStudent(userInfo, dto), HttpStatus.OK);
    }

    @DeleteMapping("{studentId}")
    ResponseEntity<Void> deleteStudent(@PathVariable("studentId") String studentId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        studentFacade.removeStudent(userInfo, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
