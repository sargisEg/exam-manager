package com.exammanager.core.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.CourseFacade;
import com.exammanager.core.model.dto.request.CreateCourseRequestDto;
import com.exammanager.core.model.dto.response.CourseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/departments/{departmentId}/groups/{groupId}/courses")
public class CourseController {

    private final CourseFacade courseFacade;

    @PostMapping
    ResponseEntity<CourseDto> createCourse(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @Valid @RequestBody CreateCourseRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(courseFacade.createCourse(userInfo, departmentId, groupId, dto), HttpStatus.OK);
    }

    @GetMapping("{courseId}")
    ResponseEntity<CourseDto> getCourse(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("courseId") String courseId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(courseFacade.getCourse(userInfo, departmentId, groupId, courseId), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<PagedModel<CourseDto>> getAllCoursesInGroup(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(courseFacade.getCourses(userInfo, departmentId, groupId, page, size), HttpStatus.OK);
    }

    @DeleteMapping("{courseId}")
    ResponseEntity<Void> deleteCourse(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("courseId") String courseId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        courseFacade.deleteCourse(userInfo, departmentId, groupId, courseId);
        return ResponseEntity.ok().build();
    }
}
