package com.exammanager.user.service.core;

import com.exammanager.user.model.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamService extends JpaRepository<Exam, String> {
}
