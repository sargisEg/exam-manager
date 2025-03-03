package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.response.ExamDto;
import com.exammanager.core.model.dto.response.ExamResultDto;
import com.exammanager.core.model.entity.Exam;
import com.exammanager.core.model.entity.ExamResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamMapperImpl implements ExamMapper {

    private final CourseMapper courseMapper;
    private final GroupMapper groupMapper;

    @Override
    public ExamDto map(Exam exam) {
        return new ExamDto(
                exam.getId(),
                exam.getTitle(),
                courseMapper.map(exam.getCourse()),
                groupMapper.map(exam.getSubgroup()),
                exam.getLocation(),
                exam.getStartDate(),
                exam.getEndDate(),
                exam.getMaxPoints(),
                exam.getIsGraded(),
                exam.getStatus().name(),
                exam.getType().name()
        );
    }

    @Override
    public ExamResultDto map(ExamResult examResult) {
        return new ExamResultDto(
                examResult.getId(),
                map(examResult.getExam()),
                examResult.getStudent().getId(),
                examResult.getPoint()
        );
    }
}
