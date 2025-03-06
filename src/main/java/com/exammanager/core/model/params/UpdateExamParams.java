package com.exammanager.core.model.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateExamParams {

    private String id;
    private String title;
    private Long startDate;
    private Long endDate;
    private String location;
    private Integer maxPoints;
}
