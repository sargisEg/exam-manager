package com.exammanager.core.controller.admin;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.TeacherFacade;
import com.exammanager.core.model.dto.request.CreateTeacherRequestDto;
import com.exammanager.core.model.dto.response.TeacherDto;
import com.exammanager.user.model.dto.response.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/admin/teachers")
@SuppressWarnings("unused")
@Secured("ROLE_ADMIN")
public class AdminTeacherController {

    private final TeacherFacade teacherFacade;


    @GetMapping()
    ResponseEntity<List<UserDto>> getAllTeachers() {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(teacherFacade.getAllTeachers(userInfo), HttpStatus.OK);
    }

    @GetMapping("/page")
    ResponseEntity<PagedModel<UserDto>> getAllTeachersPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(teacherFacade.getAllTeachers(userInfo, page, size), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<UserDto> createTeacher(@RequestBody CreateTeacherRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(teacherFacade.createTeacher(userInfo, dto), HttpStatus.OK);
    }

    @GetMapping("{teacherId}")
    ResponseEntity<TeacherDto> getTeacherById(@PathVariable("teacherId") String teacherId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(teacherFacade.getTeacherById(userInfo, teacherId), HttpStatus.OK);
    }

    @DeleteMapping("{teacherId}")
    ResponseEntity<Void> deleteTeacherById(@PathVariable("teacherId") String teacherId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        teacherFacade.deleteTeacherById(userInfo, teacherId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
