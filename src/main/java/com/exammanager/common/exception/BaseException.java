package com.exammanager.common.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final String logMessage;
    private final String responseMessage;

    public BaseException(String logMessage, String responseMessage) {
        super(responseMessage);
        this.logMessage = logMessage;
        this.responseMessage = responseMessage;
    }
}
