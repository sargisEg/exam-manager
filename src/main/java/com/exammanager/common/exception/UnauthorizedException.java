package com.exammanager.common.exception;

public class UnauthorizedException extends BaseException {

  public UnauthorizedException(String logMessage, String responseMessage) {
    super(logMessage, responseMessage);
  }

}
