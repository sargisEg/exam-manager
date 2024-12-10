package com.exammanager.user.service.core;

import com.exammanager.user.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseService extends JpaRepository<Course, String> {
}
