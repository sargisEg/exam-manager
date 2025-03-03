package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Exam;
import com.exammanager.core.model.enums.ExamStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExamService {

    Page<Exam> findBySubgroupIdAndStatus(String subgroupId, ExamStatus status, int page, int size);
    Page<Exam> findByCourseIdAndStatus(String courseId, ExamStatus status, int page, int size);
    List<Exam> findByCourseId(String courseId);
    List<Exam> findBySubgroupId(String subgroupId);
}
