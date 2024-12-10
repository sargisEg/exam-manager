package com.exammanager.auth.facade;


import com.exammanager.auth.model.dto.request.RefreshRequestDto;
import com.exammanager.auth.model.dto.request.SignInRequestDto;
import com.exammanager.auth.model.dto.response.SignInResponseDto;

public interface SignInFacade {

    SignInResponseDto signIn(SignInRequestDto dto);
    SignInResponseDto refresh(RefreshRequestDto dto);
}
