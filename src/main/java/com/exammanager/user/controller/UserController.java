package com.exammanager.user.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.user.facade.UserFacade;
import com.exammanager.user.model.dto.response.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/v1")
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("me")
    @Secured({"ROLE_STUDENT", "ROLE_TEACHER", "ROLE_ADMIN"})
    ResponseEntity<UserDto> me() {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(userFacade.me(userInfo.id()), HttpStatus.OK);
    }
}
