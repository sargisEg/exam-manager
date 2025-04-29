package com.exammanager.core.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {

    private String id;
    private String name;
    private Integer academicYear;
    private Integer startYear;
    private Integer endYear;
}
