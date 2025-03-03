package com.exammanager.core.service.impl;

import com.exammanager.core.model.entity.Exam;
import com.exammanager.core.model.enums.ExamStatus;
import com.exammanager.core.repository.ExamRepository;
import com.exammanager.core.service.core.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;


    @Override
    public Page<Exam> findByCourseIdAndStatus(String courseId, ExamStatus status, int page, int size) {
        log.trace("Finding exam page by course id - {}", courseId);
        Page<Exam> exams = examRepository.findByCourseIdAndStatus(courseId, status, PageRequest.of(page, size));
        log.trace("Successfully found exam page by course id - {}, result - {}", courseId, exams);
        return exams;
    }

    @Override
    public List<Exam> findByCourseId(String courseId) {
        log.trace("Finding exam by course id - {}", courseId);
        List<Exam> exams = examRepository.findByCourseId(courseId);
        log.trace("Successfully found exam by course id - {}, result - {}", courseId, exams);
        return exams;
    }

    @Override
    public Page<Exam> findBySubgroupIdAndStatus(String subgroupId, ExamStatus status, int page, int size) {
        log.trace("Finding exam page by subgroup id - {}", subgroupId);
        Page<Exam> exams = examRepository.findBySubgroupIdAndStatus(subgroupId, status, PageRequest.of(page, size));
        log.trace("Successfully found exam page by subgroup id - {}, result - {}", subgroupId, exams);
        return exams;
    }

    @Override
    public List<Exam> findBySubgroupId(String subgroupId) {
        log.trace("Finding exam by subgroup id - {}", subgroupId);
        List<Exam> exams = examRepository.findBySubgroupId(subgroupId);
        log.trace("Successfully found exam by subgroup id - {}, result - {}", subgroupId, exams);
        return exams;
    }
}
