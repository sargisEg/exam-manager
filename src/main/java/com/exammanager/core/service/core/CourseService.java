package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Course;
import com.exammanager.core.model.params.CreateCourseParams;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course create(CreateCourseParams params);

    Optional<Course> findByIdAndGroupId(String courseId, String groupId);

    Page<Course> findByGroupId(String groupId, int page, int size);

    List<Course> findByGroupId(String groupId);

    List<Course> findByTeacherId(String teacherId);

    void deleteById(String courseId);

    void deleteByTeacherId(String teacherId);

    Optional<Course> findByIdAndTeacherId(String courseId, String teacherId);

    Optional<Course> findById(String courseId);
}
