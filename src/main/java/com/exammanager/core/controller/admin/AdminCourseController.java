package com.exammanager.core.controller.admin;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.CourseFacade;
import com.exammanager.core.model.dto.request.CreateCourseRequestDto;
import com.exammanager.core.model.dto.response.CourseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/admin/courses")
@SuppressWarnings("unused")
@Secured("ROLE_ADMIN")
public class AdminCourseController {

    private final CourseFacade courseFacade;

    @GetMapping()
    ResponseEntity<List<CourseDto>> getAllCourses(
            @RequestParam(value = "groupId", required = false) String groupId,
            @RequestParam(value = "teacherId", required = false) String teacherId
    ) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        final List<CourseDto> response;
        if (groupId != null) {
            response = courseFacade.getCoursesByGroupId(userInfo, groupId);
        } else {
            response = courseFacade.getCoursesByTeacherId(userInfo, teacherId);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{courseId}")
    ResponseEntity<Void> deleteCourse(@PathVariable("courseId") String courseId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        courseFacade.deleteCourse(userInfo, courseId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CreateCourseRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(courseFacade.createCourse(userInfo, dto), HttpStatus.OK);
    }
}
