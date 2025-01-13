package com.exammanager.core.controller;

import com.exammanager.core.model.dto.request.CreateCourseRequestDto;
import com.exammanager.core.model.dto.response.CourseDto;
import com.exammanager.core.model.dto.response.GroupDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/departments/{departmentId}/groups/{groupId}/courses")
public class CourseController {

    @PostMapping
    ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CreateCourseRequestDto dto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{courseId}")
    ResponseEntity<CourseDto> getDepartment(@PathVariable("departmentId") String departmentId, @PathVariable("groupId") String groupId, @PathVariable("courseId") String courseId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    ResponseEntity<Page<GroupDto>> getAllCoursesInGroup(@PathVariable("departmentId") String departmentId, @PathVariable("groupId") String groupId) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{courseId}")
    ResponseEntity<Void> deleteCourse(@PathVariable("departmentId") String departmentId, @PathVariable("groupId") String groupId) {
        return ResponseEntity.ok().build();
    }
}
