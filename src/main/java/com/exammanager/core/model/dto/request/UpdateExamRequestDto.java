package com.exammanager.core.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateExamRequestDto {

    @NotNull(message = "title should not be null")
    @NotBlank(message = "title should not be empty")
    private String title;

    @NotNull(message = "course Id should not be null")
    @NotBlank(message = "course Id should not be empty")
    private String courseId;

    @NotNull(message = "subgroup Id should not be null")
    @NotBlank(message = "subgroup Id should not be empty")
    private String subgroupId;

    @NotNull(message = "location should not be null")
    @NotBlank(message = "location should not be empty")
    private String location;

    @NotNull(message = "start date should not be null")
    private Long startDate;

    @NotNull(message = "end date should not be null")
    private Long endDate;

    @NotNull(message = "max points should not be null")
    private Long maxPoints;
}
