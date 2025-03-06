package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Exam;
import com.exammanager.core.model.entity.ExamResult;
import com.exammanager.core.model.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExamResultService {

    ExamResult create(Student student, Exam exam, Integer point);
    Page<ExamResult> findByStudentId(String studentId, int page, int size);
    Page<ExamResult> findByStudentId(String studentId, String courseId, int page, int size);
    Page<ExamResult> findBySubgroupId(String subgroupId, int page, int size);
    Page<ExamResult> findByCourseId(String courseId, int page, int size);
    List<ExamResult> findByExamId(String examId);
}
