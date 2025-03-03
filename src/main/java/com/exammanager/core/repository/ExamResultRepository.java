package com.exammanager.core.repository;

import com.exammanager.core.model.entity.ExamResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamResultRepository extends JpaRepository<ExamResult, String> {

    Page<ExamResult> findByStudentId(String studentId, Pageable pageable);

    Page<ExamResult> findByExamSubgroupId(String subgroupId, Pageable pageable);

    Page<ExamResult> findByExamCourseId(String courseId, Pageable pageable);
}
