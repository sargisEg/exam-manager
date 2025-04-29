package com.exammanager.core.service.impl;

import com.exammanager.common.service.UuidProvider;
import com.exammanager.core.model.entity.Exam;
import com.exammanager.core.model.entity.ExamResult;
import com.exammanager.core.model.entity.Student;
import com.exammanager.core.repository.ExamResultRepository;
import com.exammanager.core.service.core.ExamResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl implements ExamResultService {

    private final ExamResultRepository examResultRepository;
    private final UuidProvider uuidProvider;

    @Override
    public ExamResult create(Student student, Exam exam, Integer point) {
        Assert.notNull(student, "student should not be null");
        Assert.notNull(exam, "exam should not be null");
        Assert.notNull(point, "point should not be null");
        log.trace("Creating exam result student - {}, exam - {}, point - {}", student, exam, point);

        final ExamResult examResult = examResultRepository.save(new ExamResult(
                uuidProvider.getUuid(),
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                student,
                exam,
                point
        ));

        log.trace("Successfully created exam result student - {}, exam - {}, point - {}, result - {}", student, exam, point, examResult);
        return examResult;
    }

    @Override
    public Page<ExamResult> findByStudentId(String studentId, int page, int size) {
        Assert.hasText(studentId, "studentId should not be null");
        log.trace("Finding exam results page by student id - {}", studentId);
        Page<ExamResult> examResults = examResultRepository.findByStudentId(studentId, PageRequest.of(page, size));
        log.trace("Successfully found exam results page by student id - {}, result - {}", studentId, examResults);
        return examResults;
    }

    @Override
    public List<ExamResult> findByStudentId(String studentId) {
        Assert.hasText(studentId, "studentId should not be null");
        log.trace("Finding exam results by student id - {}", studentId);
        List<ExamResult> examResults = examResultRepository.findByStudentId(studentId);
        log.trace("Successfully found exam results by student id - {}, result - {}", studentId, examResults);
        return examResults;
    }

    @Override
    public Page<ExamResult> findBySubgroupId(String subgroupId, int page, int size) {
        Assert.hasText(subgroupId, "studentId should not be null");
        log.trace("Finding exam results by subgroup id - {}", subgroupId);
        Page<ExamResult> examResults = examResultRepository.findByExamSubgroupId(subgroupId, PageRequest.of(page, size));
        log.trace("Successfully found exam results by subgroup id - {}, result - {}", subgroupId, examResults);
        return examResults;
    }

    @Override
    public Page<ExamResult> findByCourseId(String courseId, int page, int size) {
        Assert.hasText(courseId, "courseId should not be null");
        log.trace("Finding exam results by course id - {}", courseId);
        Page<ExamResult> examResults = examResultRepository.findByExamCourseId(courseId, PageRequest.of(page, size));
        log.trace("Successfully found exam results by course id - {}, result - {}", courseId, examResults);
        return examResults;
    }

    @Override
    public List<ExamResult> findByExamId(String examId) {
        Assert.hasText(examId, "examId should not be null");
        log.trace("Finding exam results by exam id - {}", examId);
        List<ExamResult> examResults = examResultRepository.findByExamId(examId);
        log.trace("Successfully found exam results by exam id - {}, result - {}", examId, examResults);
        return examResults;
    }
}
