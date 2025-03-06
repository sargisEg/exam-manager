package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateExamRequestDto;
import com.exammanager.core.model.dto.request.UpdateExamRequestDto;
import com.exammanager.core.model.dto.response.ExamDto;
import com.exammanager.core.model.dto.response.ExamResultDto;
import com.exammanager.core.model.entity.Course;
import com.exammanager.core.model.entity.Exam;
import com.exammanager.core.model.entity.ExamResult;
import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.model.params.CreateExamParams;
import com.exammanager.core.model.params.UpdateExamParams;

public interface ExamMapper {

    Exam map(CreateExamParams params);

    CreateExamParams map(CreateExamRequestDto dto, Course course, Subgroup subgroup);

    ExamDto map(Exam exam);

    ExamResultDto map(ExamResult examResult);

    Exam map(Exam exam, UpdateExamParams params);

    UpdateExamParams map(String examId, UpdateExamRequestDto dto);
}
