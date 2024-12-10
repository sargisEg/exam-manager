package com.exammanager.common.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends BaseException {

    public NotFoundException(String logMessage, String responseMessage) {
        super(logMessage, responseMessage);
    }
}
