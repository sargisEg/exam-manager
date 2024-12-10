package com.exammanager.common.exception;

public class AccessDeniedException extends BaseException {

    public AccessDeniedException(String logMessage, String responseMessage) {
        super(logMessage, responseMessage);
    }
}
