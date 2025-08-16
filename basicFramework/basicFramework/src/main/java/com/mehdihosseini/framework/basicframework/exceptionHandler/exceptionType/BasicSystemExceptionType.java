package com.mehdihosseini.framework.basicframework.exceptionHandler.exceptionType;

import com.mehdihosseini.framework.basicframework.exceptionHandler.BasicSpecificationException;

public enum BasicSystemExceptionType implements BasicSpecificationException {
    FILE_NOT_FOUND_BY_FILE_NAME("error.fileNotFoundByFileName", "404"),
    INTERNAL_SERVER_ERROR("error.internalServerError", "500"),
    SYSTEM_ERROR_KEY_IS_NOT_VALID("system.error.keyIsNotValid", "501"),
    SYSTEM_ERROR_FORMAT_MESSAGE_IS_NOT_VALID("system.error.formatMessageIsNotValid", "502"),
    SYSTEM_UNKNOWN_BUILDING_MESSAGE_ERROR("system.unknownBuildingMessageError", "503"),

    ;

    BasicSystemExceptionType(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    private final String message;

    private final String errorCode;

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
