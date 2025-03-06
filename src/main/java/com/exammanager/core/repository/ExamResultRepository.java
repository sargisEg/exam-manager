package com.exammanager.core.repository;

import com.exammanager.core.model.entity.ExamResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamResultRepository extends JpaRepository<ExamResult, String> {

    Page<ExamResult> findByStudentId(String studentId, Pageable pageable);

    Page<ExamResult> findByExamSubgroupId(String subgroupId, Pageable pageable);

    Page<ExamResult> findByExamCourseId(String courseId, Pageable pageable);

    List<ExamResult> findByExamId(String examId);

    Page<ExamResult> findByStudentIdAndExamCourseId(String studentId, String courseId, Pageable pageable);
}
