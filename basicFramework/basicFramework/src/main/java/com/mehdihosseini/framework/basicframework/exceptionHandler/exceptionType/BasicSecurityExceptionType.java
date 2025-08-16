package com.mehdihosseini.framework.basicframework.exceptionHandler.exceptionType;

import com.mehdihosseini.framework.basicframework.exceptionHandler.BasicSpecificationException;

public enum BasicSecurityExceptionType implements BasicSpecificationException {
    UNAUTHORIZED("error.unauthorized", "401"),
    AUTHENTICATION("error.authentication", "403"),

    ;
    private final String message;
    private final String errorCode;

    BasicSecurityExceptionType(String message , String errorCode) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
