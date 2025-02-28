package com.exammanager.core.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequestDto {

    @NotNull(message = "fullName should not be null")
    @NotBlank(message = "fullName should not be empty")
    private String fullName;

    @NotNull(message = "password should not be null")
    @NotBlank(message = "password should not be empty")
    @ToString.Exclude
    private String password;

    @NotNull(message = "email should not be null")
    @NotBlank(message = "email should not be empty")
    private String email;

    @NotNull(message = "phone should not be null")
    @NotBlank(message = "phone should not be empty")
    private String phone;

    @NotNull(message = "subgroupId should not be null")
    @NotBlank(message = "subgroupId should not be empty")
    private String subgroupId;

}
