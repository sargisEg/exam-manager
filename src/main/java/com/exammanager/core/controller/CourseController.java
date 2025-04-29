package com.exammanager.core.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.CourseFacade;
import com.exammanager.core.model.dto.response.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/courses")
@SuppressWarnings("unused")
public class CourseController {

    private final CourseFacade courseFacade;

    @GetMapping()
    @Secured("ROLE_TEACHER")
    ResponseEntity<List<CourseDto>> getAllCourses(@RequestParam(value = "groupId", required = false) String groupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        final List<CourseDto> response = courseFacade.getCoursesByGroupIdAndTeacherId(userInfo, groupId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{courseId}")
    @Secured("ROLE_TEACHER")
    ResponseEntity<CourseDto> getCourseById(@PathVariable("courseId") String courseId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        final CourseDto response = courseFacade.getCourseByIdAndTeacherId(userInfo, courseId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
