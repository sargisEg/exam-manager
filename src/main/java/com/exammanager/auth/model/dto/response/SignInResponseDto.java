package com.exammanager.auth.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponseDto {

    @ToString.Exclude
    private String token;
    @ToString.Exclude
    private String refreshToken;
}
