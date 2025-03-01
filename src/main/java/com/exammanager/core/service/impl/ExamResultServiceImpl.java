package com.exammanager.core.service.impl;

import com.exammanager.core.model.entity.ExamResult;
import com.exammanager.core.repository.ExamResultRepository;
import com.exammanager.core.service.core.ExamResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl implements ExamResultService {

    private final ExamResultRepository examResultRepository;

    @Override
    public Page<ExamResult> findByStudentId(String studentId, int page, int size) {
        Assert.hasText(studentId, "studentId should not be null");
        log.trace("Finding exam results by student id - {}", studentId);
        Page<ExamResult> examResults = examResultRepository.findByStudentId(studentId, PageRequest.of(page, size));
        log.trace("Successfully found exam results by student id - {}, result - {}", studentId, examResults);
        return examResults;
    }
}
