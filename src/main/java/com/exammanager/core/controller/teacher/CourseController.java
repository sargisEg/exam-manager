//package com.exammanager.core.controller.teacher;
//
//import com.exammanager.common.security.UserInfo;
//import com.exammanager.common.security.UserInfoProvider;
//import com.exammanager.core.facade.core.CourseFacade;
//import com.exammanager.core.model.dto.request.CreateCourseRequestDto;
//import com.exammanager.core.model.dto.response.CourseDto;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.web.PagedModel;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("api/core/v1/courses")
//@SuppressWarnings("unused")
//public class CourseController {
//
//    private final CourseFacade courseFacade;
//
//    @PostMapping
//    @Secured("ROLE_ADMIN")
//    ResponseEntity<CourseDto> createCourse(
//            @PathVariable("departmentId") String departmentId,
//            @PathVariable("groupId") String groupId,
//            @Valid @RequestBody CreateCourseRequestDto dto) {
//        final UserInfo userInfo = UserInfoProvider.getUserInfo();
//        return new ResponseEntity<>(courseFacade.createCourse(userInfo, departmentId, groupId, dto), HttpStatus.OK);
//    }
//
//    @GetMapping("{courseId}")
//    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
//    ResponseEntity<CourseDto> getCourse(
//            @PathVariable("departmentId") String departmentId,
//            @PathVariable("groupId") String groupId,
//            @PathVariable("courseId") String courseId) {
//        final UserInfo userInfo = UserInfoProvider.getUserInfo();
//        return new ResponseEntity<>(courseFacade.getCourse(userInfo, departmentId, groupId, courseId), HttpStatus.OK);
//    }
//
//    @GetMapping()
//    @Secured({"ROLE_TEACHER"})
//    ResponseEntity<PagedModel<CourseDto>> getAllCourses(
//            @RequestParam("page") int page, @RequestParam("size") int size) {
//        final UserInfo userInfo = UserInfoProvider.getUserInfo();
//        return new ResponseEntity<>(courseFacade.getCoursesPage(userInfo, page, size), HttpStatus.OK);
//    }
//
//    @DeleteMapping("{courseId}")
//    ResponseEntity<Void> deleteCourse(
//            @PathVariable("departmentId") String departmentId,
//            @PathVariable("groupId") String groupId,
//            @PathVariable("courseId") String courseId) {
//        final UserInfo userInfo = UserInfoProvider.getUserInfo();
//        courseFacade.deleteCourse(userInfo, departmentId, groupId, courseId);
//        return ResponseEntity.ok().build();
//    }
//}
