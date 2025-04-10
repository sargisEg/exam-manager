package com.exammanager.auth.controller;

import com.exammanager.auth.facade.SignInFacade;
import com.exammanager.auth.model.dto.request.RefreshRequestDto;
import com.exammanager.auth.model.dto.request.SignInRequestDto;
import com.exammanager.auth.model.dto.response.SignInResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth/v1")
//@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS}, allowedHeaders = "*")
public class SignInControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final SignInFacade signInFacade;

    @PostMapping("sign-in")
    ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto dto) {

        final SignInResponseDto signInResponseDto = signInFacade.signIn(dto);

        return new ResponseEntity<>(signInResponseDto, HttpStatus.OK);
    }

    @PostMapping("refresh")
    ResponseEntity<SignInResponseDto> refresh(@RequestBody RefreshRequestDto dto) {

        final SignInResponseDto signInResponseDto = signInFacade.refresh(dto);

        return new ResponseEntity<>(signInResponseDto, HttpStatus.OK);
    }

    @PostMapping("test")
    @Secured("ROLE_TEACHER")
    ResponseEntity<String> signIn() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
