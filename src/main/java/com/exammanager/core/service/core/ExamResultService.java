package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.ExamResult;
import org.springframework.data.domain.Page;

public interface ExamResultService {

    Page<ExamResult> findByStudentId(String studentId, int page, int size);
    Page<ExamResult> findBySubgroupId(String subgroupId, int page, int size);
    Page<ExamResult> findByCourseId(String courseId, int page, int size);
}
