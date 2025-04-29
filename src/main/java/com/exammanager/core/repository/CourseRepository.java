package com.exammanager.core.repository;

import com.exammanager.core.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, String> {

    Page<Course> findByGroupId(String groupId, Pageable pageable);

    Optional<Course> findByIdAndGroupId(String courseId, String groupId);

    List<Course> findByTeacherId(String teacherId);

    Page<Course> findByTeacherId(String teacherId, Pageable pageable);

    List<Course> findByGroupId(String groupId);

    void deleteByTeacherId(String teacherId);

    Optional<Course> findByIdAndTeacherId(String courseId, String teacherId);
}
