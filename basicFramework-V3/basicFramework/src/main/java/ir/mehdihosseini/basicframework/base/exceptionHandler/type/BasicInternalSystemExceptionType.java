package ir.mehdihosseini.basicframework.base.exceptionHandler.type;

import ir.mehdihosseini.basicframework.base.exceptionHandler.BasicSpecificationException;

public enum BasicInternalSystemExceptionType implements BasicSpecificationException {
    INTERNAL_SERVER_ERROR("basic.system.error.internalServerError", "500"),
    SYSTEM_ERROR_KEY_IS_NOT_VALID("basic.system.error.keyIsNotValid", "501"),
    SYSTEM_ERROR_FORMAT_MESSAGE_IS_NOT_VALID("basic.system.error.formatMessageIsNotValid", "502"),
    SYSTEM_UNKNOWN_BUILDING_MESSAGE_ERROR("basic.system.error.unknownBuildingMessageError", "503"),
    EXCEPTION_HANDLING_MESSAGE_KEY_IS_DUPLICATED("basic.system.error.exceptionHandling.messageKeyIsDuplicated", "504"),
    INFRASTRUCTURE_FILE_PROCESS("basic.system.error.infrastructure.processFile", "505"),
    INFRASTRUCTURE_FILE_PROCESS_ADD_IN_FILE("basic.system.error.infrastructure.processFile.addInFile", "506"),
    INFRASTRUCTURE_FILE_PROCESS_FIND_ALL_FROM_FILE("basic.system.error.infrastructure.processFile.findAllFromFile", "507"),
    INFRASTRUCTURE_FILE_PROCESS_DELETE_LINE_FROM_FILE("basic.system.error.infrastructure.processFile.deleteLineFromFile", "508"),
    SWAGGER_CONFIG_PRODUCTION_MODE_SCHEMA_TYPE_NOT_VALID("basic.system.error.swagger.config.schemaTypeIsNotValid", "509"),
    SWAGGER_CONFIG_PRODUCTION_MODE_AUTH_TYPE_NOT_VALID("basic.system.error.swagger.config.authTypeIsNotValid", "510"),
    ;

    BasicInternalSystemExceptionType(String messageKey, String errorCode) {
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
