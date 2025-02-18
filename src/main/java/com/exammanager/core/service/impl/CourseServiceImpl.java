package com.exammanager.core.service.impl;

import com.exammanager.core.model.entity.Course;
import com.exammanager.core.model.params.CreateCourseParams;
import com.exammanager.core.service.core.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CourseServiceImpl implements CourseService {
    @Override
    public Course create(CreateCourseParams map) {
        return null;
    }

    @Override
    public Optional<Course> findByIdAndGroupId(String courseId, String groupId) {
        return Optional.empty();
    }

    @Override
    public Page<Course> findByGroupId(String groupId, String keyword, int page, int size) {
        return null;
    }

    @Override
    public void deleteByIdAndGroupId(String courseId, String groupId) {

    }
}
