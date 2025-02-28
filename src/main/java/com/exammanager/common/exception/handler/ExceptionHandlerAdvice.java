package com.exammanager.common.exception.handler;

import com.exammanager.common.exception.AccessDeniedException;
import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.exception.UnauthorizedException;
import com.exammanager.common.model.dto.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        log.error(e.getLogMessage());
        return new ResponseEntity<>(this.createErrorResponse(e.getResponseMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException e) {
        log.error(e.getLogMessage());
        return new ResponseEntity<>(this.createErrorResponse(e.getResponseMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        log.error(e.getLogMessage());
        return new ResponseEntity<>(this.createErrorResponse(e.getResponseMessage(), HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    private ErrorResponse createErrorResponse(String errorMessage, HttpStatus status) {
        return new ErrorResponse(status, new Date(), errorMessage);
    }
}
