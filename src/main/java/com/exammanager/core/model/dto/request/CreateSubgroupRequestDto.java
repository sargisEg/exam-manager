package com.exammanager.core.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSubgroupRequestDto {

    @NotNull(message = "name should not be null")
    @NotBlank(message = "name should not be empty")
    private String name;
}
