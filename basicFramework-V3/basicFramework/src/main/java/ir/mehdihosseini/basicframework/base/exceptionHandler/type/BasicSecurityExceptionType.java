package ir.mehdihosseini.basicframework.base.exceptionHandler.type;

import ir.mehdihosseini.basicframework.base.exceptionHandler.BasicSpecificationException;

public enum BasicSecurityExceptionType implements BasicSpecificationException {
    UNAUTHORIZED("error.unauthorized", "401"),
    ACCESS_DENIED("error.access-denied", "4003"),
    AUTHENTICATION("error.authentication-failed", "403"),
    AUTHENTICATION_FAILED("error.user-not-found", "4001"),
    REGISTRY_USER_DUPLICATED("error.username-is-exist","4002"),
    USERNAME_OR_PASSWORD_IS_NOT_VALID("error.username-or-password-is-not-valid","4003"),
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
