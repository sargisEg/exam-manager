package com.exammanager.core.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {

    private String id;
    private String title;
    private CourseDto course;
    private SubGroupDto subGroup;
    private String location;
    private Long startDate;
    private Long endDate;
    private Long maxPoints;
    private String status;
}
