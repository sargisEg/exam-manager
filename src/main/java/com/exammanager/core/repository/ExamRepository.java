package com.exammanager.core.repository;

import com.exammanager.core.model.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, String> {

    Page<Exam> findBySubgroupId(String subgroupId, Pageable pageRequest);
}
