package ir.mehdihosseini.basicframework.base.exceptionHandler.type;

import ir.mehdihosseini.basicframework.base.exceptionHandler.BasicSpecificationException;

public enum BasicRequestExceptionType implements BasicSpecificationException {
    BAD_REQUEST("error.badRequest", "400"),
    REQUEST_FAILED("error.request-failed", "401"),
    ENTERED_VALUE_IS_NOT_VALID("error.enteredValueIsNotValid", "402"),
    NO_RESOURCE_FOUND("error.resourceNotFoundRequest", "403"),
    FILE_NOT_FOUND_BY_FILE_NAME("basic.system.error.fileNotFoundByFileName", "404"),
    PAGE_NOT_FOUND("error.pageNotFound", "404"),
    ;

    BasicRequestExceptionType(String messageKey, String errorCode) {
        this.errorCode = errorCode;
        this.messageKey = messageKey;
    }

    private final String errorCode;
    private final String messageKey;

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessageKey() {
        return this.messageKey;
    }
}
