package com.exammanager.core.repository;

import com.exammanager.core.model.entity.ExamResult;
import com.exammanager.core.model.entity.SubGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamResultRepository extends JpaRepository<ExamResult, String> {
}
