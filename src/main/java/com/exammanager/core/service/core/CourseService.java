package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Course;
import com.exammanager.core.model.params.CreateCourseParams;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CourseService {

    Course create(CreateCourseParams params);

    Optional<Course> findByIdAndGroupId(String courseId, String groupId);

    Page<Course> findByGroupId(String groupId, int page, int size);

    void deleteByIdAndGroupId(String courseId, String groupId);
}
