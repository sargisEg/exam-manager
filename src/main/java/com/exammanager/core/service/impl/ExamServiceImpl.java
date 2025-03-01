package com.exammanager.core.service.impl;

import com.exammanager.core.model.entity.Exam;
import com.exammanager.core.repository.ExamRepository;
import com.exammanager.core.service.core.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    @Override
    public Page<Exam> findBySubgroupId(String subgroupId, int page, int size) {
        log.trace("Finding exam by subgroup id - {}", subgroupId);
        Page<Exam> exam = examRepository.findBySubgroupId(subgroupId, PageRequest.of(page, size));
        log.trace("Successfully found exam by subgroup id - {}, result - {}", subgroupId, exam);
        return exam;
    }
}
