package com.exammanager.core.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGroupRequestDto {

    @NotNull(message = "name should not be null")
    @NotBlank(message = "name should not be empty")
    private String name;

    @NotNull(message = "start year should not be null")
    private Integer startYear;

    @NotNull(message = "end year should not be null")
    private Integer endYear;

    @NotNull(message = "department Id should not be null")
    @NotBlank(message = "department Id should not be empty")
    private String departmentId;
}
