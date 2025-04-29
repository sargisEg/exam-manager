package com.exammanager.core.service.impl;

import com.exammanager.common.service.UuidProvider;
import com.exammanager.core.mapper.ExamMapper;
import com.exammanager.core.model.entity.Exam;
import com.exammanager.core.model.enums.ExamStatus;
import com.exammanager.core.model.params.CreateExamParams;
import com.exammanager.core.model.params.UpdateExamParams;
import com.exammanager.core.repository.ExamRepository;
import com.exammanager.core.service.core.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final UuidProvider uuidProvider;

    @Override
    public Exam create(CreateExamParams params) {
        Assert.notNull(params, "params should not be null");
        log.trace("Creating exam with params - {}", params);
        final Exam examFromParams = examMapper.map(params);
        examFromParams.setId(uuidProvider.getUuid());

        final Exam exam = examRepository.save(examFromParams);

        log.trace("Successfully created exam with params - {}, result - {}", params, exam);
        return exam;
    }

    @Override
    public Optional<Exam> update(UpdateExamParams params) {
        Assert.notNull(params, "params should not be null");
        log.trace("Updating exam with params - {}", params);
        final Optional<Exam> optionalExam = examRepository.findById(params.getId());
        if (optionalExam.isEmpty()) {
            return Optional.empty();
        }

        final Exam exam = examRepository.save(examMapper.map(optionalExam.get(), params));

        log.trace("Successfully updated exam with params - {}, result - {}", params, exam);
        return Optional.of(exam);
    }

    @Override
    public List<Exam> findByCourseIdAndStatus(String courseId, ExamStatus status) {
        Assert.hasText(courseId, "courseId should not be null");
        Assert.notNull(status, "status should not be null");
        log.trace("Finding exams by course id - {}", courseId);
        List<Exam> exams = examRepository.findByCourseIdAndStatus(courseId, status);
        log.trace("Successfully found exams by course id - {}, result - {}", courseId, exams);
        return exams;
    }

    @Override
    public List<Exam> findByCourseIdAndNotGraded(String courseId) {
        Assert.hasText(courseId, "courseId should not be null");
        log.trace("Finding not graded exams by course id - {}", courseId);
        List<Exam> exams = examRepository.findByCourseIdAndStatusAndIsGradedFalse(courseId, ExamStatus.FINISHED);
        log.trace("Successfully found not graded exams by course id - {}, result - {}", courseId, exams);
        return exams;
    }

    @Override
    public List<Exam> findByCourseId(String courseId) {
        Assert.hasText(courseId, "courseId should not be null");
        log.trace("Finding exam by course id - {}", courseId);
        List<Exam> exams = examRepository.findByCourseId(courseId);
        log.trace("Successfully found exam by course id - {}, result - {}", courseId, exams);
        return exams;
    }

    @Override
    public Page<Exam> findBySubgroupIdAndStatus(String subgroupId, ExamStatus status, int page, int size) {
        Assert.hasText(subgroupId, "subgroupId should not be null");
        Assert.notNull(status, "status should not be null");
        log.trace("Finding exam page by subgroup id - {}", subgroupId);
        Page<Exam> exams = examRepository.findBySubgroupIdAndStatus(subgroupId, status, PageRequest.of(page, size));
        log.trace("Successfully found exam page by subgroup id - {}, result - {}", subgroupId, exams);
        return exams;
    }

    @Override
    public List<Exam> findBySubgroupId(String subgroupId) {
        Assert.hasText(subgroupId, "subgroupId should not be null");
        log.trace("Finding exam by subgroup id - {}", subgroupId);
        List<Exam> exams = examRepository.findBySubgroupId(subgroupId);
        log.trace("Successfully found exam by subgroup id - {}, result - {}", subgroupId, exams);
        return exams;
    }

    @Override
    public Optional<Exam> findById(String examId) {
        Assert.hasText(examId, "examId should not be null");
        log.trace("Finding exam by id - {}", examId);
        Optional<Exam> exam = examRepository.findById(examId);
        log.trace("Successfully found exam by id - {}, result - {}", examId, exam);
        return exam;
    }

    @Override
    public void grade(String examId) {
        Assert.hasText(examId, "examId should not be null");
        log.trace("Grading exam with id - {}", examId);
        examRepository.findById(examId).ifPresent(exam -> {
            exam.setIsGraded(true);
            examRepository.save(exam);
        });
        log.trace("Successfully graded exam with id - {}", examId);
    }

    @Override
    public void deleteById(String examId) {
        Assert.hasText(examId, "examId should not be null");
        log.trace("Deleting exam with id - {}", examId);
        examRepository.deleteById(examId);
        log.trace("Successfully deleted exam with id - {}", examId);
    }
}
