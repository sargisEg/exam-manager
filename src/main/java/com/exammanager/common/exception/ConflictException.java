package com.exammanager.common.exception;


import lombok.Getter;

@Getter
public class ConflictException extends BaseException {

    public ConflictException(String logMessage, String responseMessage) {
        super(logMessage, responseMessage);
    }
}
