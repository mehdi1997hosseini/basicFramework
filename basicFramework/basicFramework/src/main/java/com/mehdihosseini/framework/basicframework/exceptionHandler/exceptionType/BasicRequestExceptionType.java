package com.mehdihosseini.framework.basicframework.exceptionHandler.exceptionType;

import com.mehdihosseini.framework.basicframework.exceptionHandler.BasicSpecificationException;

public enum BasicRequestExceptionType implements BasicSpecificationException {
    BAD_REQUEST("error.badRequest", "400"),
    REQUEST_FAILED("error.request-failed","401"),
    ENTERED_VALUE_IS_NOT_VALID("error.valueIsNotValid", "402"),
    NO_RESOURCE_FOUND("error.noResourceFound", "403"),
    PAGE_NOT_FOUND("error.pageNotFound", "404"),

    ;
    BasicRequestExceptionType(String message,String errorCode) {
        this.errorCode = errorCode;
        this.message = message;
    }
    private final String errorCode;
    private final String message;

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
