package com.exammanager.core.repository;

import com.exammanager.core.model.entity.Exam;
import com.exammanager.core.model.enums.ExamStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, String> {

    Page<Exam> findByCourseIdAndStatus(String courseId, ExamStatus status, Pageable pageable);
    Page<Exam> findBySubgroupIdAndStatus(String subgroupId, ExamStatus status, Pageable pageable);
    List<Exam> findByCourseId(String courseId);
    List<Exam> findBySubgroupId(String subgroupId);
}
