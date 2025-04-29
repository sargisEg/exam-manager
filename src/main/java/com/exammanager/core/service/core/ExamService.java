package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Exam;
import com.exammanager.core.model.enums.ExamStatus;
import com.exammanager.core.model.params.CreateExamParams;
import com.exammanager.core.model.params.UpdateExamParams;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ExamService {

    Exam create(CreateExamParams params);
    Optional<Exam> update(UpdateExamParams params);

    Page<Exam> findBySubgroupIdAndStatus(String subgroupId, ExamStatus status, int page, int size);

    List<Exam> findByCourseIdAndStatus(String courseId, ExamStatus status);
    List<Exam> findByCourseIdAndNotGraded(String courseId);

    List<Exam> findByCourseId(String courseId);
    List<Exam> findBySubgroupId(String subgroupId);
    Optional<Exam> findById(String examId);
    void grade(String examId);
    void deleteById(String examId);
}
