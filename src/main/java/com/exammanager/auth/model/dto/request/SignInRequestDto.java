package com.exammanager.auth.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequestDto {

    @NotBlank(message = "email should not be empty")
    private String email;

    @NotBlank(message = "password should not be empty")
    @ToString.Exclude
    private String password;
}
