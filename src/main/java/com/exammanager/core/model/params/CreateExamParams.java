package com.exammanager.core.model.params;

import com.exammanager.core.model.entity.Course;
import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.model.enums.ExamType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateExamParams {

    private String title;
    private Course course;
    private Subgroup subgroup;
    private String location;
    private Long startDate;
    private Long endDate;
    private Long maxPoints;
    private ExamType type;
}
