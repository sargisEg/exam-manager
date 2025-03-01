package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.response.ExamDto;
import com.exammanager.core.model.dto.response.ExamResultDto;
import com.exammanager.core.model.entity.Exam;
import com.exammanager.core.model.entity.ExamResult;

public interface ExamMapper {

    ExamDto map(Exam exam);

    ExamResultDto map(ExamResult examResult);
}
