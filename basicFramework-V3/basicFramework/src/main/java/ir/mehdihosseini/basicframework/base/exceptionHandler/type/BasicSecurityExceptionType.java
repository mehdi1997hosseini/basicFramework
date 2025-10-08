package ir.mehdihosseini.basicframework.base.exceptionHandler.type;

import ir.mehdihosseini.basicframework.base.exceptionHandler.BasicSpecificationException;

public enum BasicSecurityExceptionType implements BasicSpecificationException {
    UNAUTHORIZED("error.unauthorized", "401"),
    AUTHENTICATION("error.authentication-failed", "403"),

    ;
    private final String messageKey;
    private final String errorCode;

    BasicSecurityExceptionType(String messageKey, String errorCode) {
        this.errorCode = errorCode;
        this.messageKey = messageKey;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessageKey() {
        return this.messageKey;
    }
}
