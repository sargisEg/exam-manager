package com.exammanager.core.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourseRequestDto {

    @NotNull(message = "name should not be null")
    @NotBlank(message = "name should not be empty")
    private String name;

    @NotNull(message = "groupId should not be null")
    @NotBlank(message = "groupId should not be empty")
    private String groupId;

    @NotNull(message = "teacherId should not be null")
    @NotBlank(message = "teacherId should not be empty")
    private String teacherId;
}
