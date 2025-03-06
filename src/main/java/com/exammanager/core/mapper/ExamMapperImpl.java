package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateExamRequestDto;
import com.exammanager.core.model.dto.request.UpdateExamRequestDto;
import com.exammanager.core.model.dto.response.ExamDto;
import com.exammanager.core.model.dto.response.ExamResultDto;
import com.exammanager.core.model.entity.Course;
import com.exammanager.core.model.entity.Exam;
import com.exammanager.core.model.entity.ExamResult;
import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.model.enums.ExamStatus;
import com.exammanager.core.model.params.CreateExamParams;
import com.exammanager.core.model.params.UpdateExamParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamMapperImpl implements ExamMapper {

    private final CourseMapper courseMapper;
    private final GroupMapper groupMapper;

    @Override
    public Exam map(CreateExamParams params) {
        return new Exam(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                params.getTitle(),
                params.getCourse(),
                params.getSubgroup(),
                params.getLocation(),
                params.getStartDate(),
                params.getEndDate(),
                params.getMaxPoints(),
                false,
                ExamStatus.UPCOMING,
                params.getType()
        );
    }

    @Override
    public CreateExamParams map(CreateExamRequestDto dto, Course course, Subgroup subgroup) {
        return new CreateExamParams(
                dto.getTitle(),
                course,
                subgroup,
                dto.getLocation(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getMaxPoints(),
                dto.getType()
        );
    }

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

    @Override
    public Exam map(Exam exam, UpdateExamParams params) {
        exam.setTitle(params.getTitle() == null || params.getTitle().isBlank() ? exam.getTitle() : params.getTitle());
        exam.setStartDate(params.getStartDate() == null ? exam.getStartDate() : params.getStartDate());
        exam.setEndDate(params.getEndDate() == null ? exam.getEndDate() : params.getEndDate());
        exam.setLocation(params.getLocation() == null || params.getLocation().isBlank() ? exam.getLocation() : params.getLocation());
        exam.setMaxPoints(params.getMaxPoints() == null ? exam.getMaxPoints() : params.getMaxPoints());
        return exam;
    }

    @Override
    public UpdateExamParams map(String examId, UpdateExamRequestDto dto) {
        return new UpdateExamParams(
                examId,
                dto.getTitle(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getLocation(),
                dto.getMaxPoints()
        );
    }
}
