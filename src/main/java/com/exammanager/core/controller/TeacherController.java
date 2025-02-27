package com.exammanager.core.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.TeacherFacade;
import com.exammanager.core.model.dto.request.CreateTeacherRequestDto;
import com.exammanager.user.model.dto.response.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/teachers")
public class TeacherController {

    private final TeacherFacade teacherFacade;

    @PostMapping()
    @Secured("ROLE_ADMIN")
    ResponseEntity<UserDto> createTeacher(@RequestBody CreateTeacherRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(teacherFacade.createTeacher(userInfo, dto), HttpStatus.OK);
    }

    @GetMapping()
    @Secured("ROLE_ADMIN")
    ResponseEntity<PagedModel<UserDto>> getAllTeachers(@RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(teacherFacade.getAllTeachers(userInfo, page, size), HttpStatus.OK);
    }
}
