package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Exam;
import org.springframework.data.domain.Page;

public interface ExamService {

    Page<Exam> findBySubgroupId(String subgroupId, int page, int size);
}
