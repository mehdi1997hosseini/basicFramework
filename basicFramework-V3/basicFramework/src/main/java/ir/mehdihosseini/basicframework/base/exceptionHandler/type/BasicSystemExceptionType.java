package ir.mehdihosseini.basicframework.base.exceptionHandler.type;

import ir.mehdihosseini.basicframework.base.exceptionHandler.BasicSpecificationException;

public enum BasicSystemExceptionType implements BasicSpecificationException {
    FILE_NOT_FOUND_BY_FILE_NAME("error.fileNotFoundByFileName", "404"),
    INTERNAL_SERVER_ERROR("error.internalServerError", "500"),
    SYSTEM_ERROR_KEY_IS_NOT_VALID("system.error.keyIsNotValid", "501"),
    SYSTEM_ERROR_FORMAT_MESSAGE_IS_NOT_VALID("system.error.formatMessageIsNotValid", "502"),
    SYSTEM_UNKNOWN_BUILDING_MESSAGE_ERROR("system.unknownBuildingMessageError", "503"),
    EXCEPTION_HANDLING_MESSAGE_KEY_IS_DUPLICATED("system.exceptionHandling.messageKeyIsDuplicated", "504"),
    ;

    BasicSystemExceptionType(String messageKey, String errorCode) {
        this.messageKey = messageKey;
        this.errorCode = errorCode;
    }

    private final String messageKey;

    private final String errorCode;

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessageKey() {
        return this.messageKey;
    }

}
