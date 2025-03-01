package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.ExamResult;
import org.springframework.data.domain.Page;

public interface ExamResultService {

    Page<ExamResult> findByStudentId(String studentId, int page, int size);
}
