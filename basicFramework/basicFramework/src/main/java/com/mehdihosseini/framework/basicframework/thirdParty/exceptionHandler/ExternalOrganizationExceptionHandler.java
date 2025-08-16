package com.mehdihosseini.framework.basicframework.thirdParty.exceptionHandler;


import com.mehdihosseini.framework.basicframework.exceptionHandler.BasicSpecificationException;

public enum ExternalOrganizationExceptionHandler implements BasicSpecificationException {
    /** error code 400 - 500 **/
    EXTERNAL_ORGANIZATION_NAME_IS_NOT_EXIST("external.organization.name.is.not.exist", "404"),
    EXTERNAL_ORGANIZATION_INFORMATION_IS_NOT_EXIST("external.organization.information.is.not.exist","405"),
    ERROR_FROM_EXTERNAL_ORGANIZATION_SERVICE("error.from.external.organization.service","406"),
    TOKEN_IS_NOT_VALID("token.is.not.valid","407"),


    /** error code 500 - 600 **/
    FAILED_PROCESS_RESTART_MANUALLY_EXTERNAL_ORGANIZATION("failed.process.restart.manually.external.organization", "500"),
    FAILED_PROCESS_SHOT_DOWN_MANUALLY_EXTERNAL_ORGANIZATION("failed.process.shot.down.manually.external.organization", "501"),
    INTERNAL_ERROR_ON_GET_INFORMATION_EXTERNAL_ORGANIZATION_FROM_DB("internal.error.on.get.information.external.organization.fromDB","502"),
    CONNECTION_ERROR_EXTERNAL_ORGANIZATION("connection.error.external.organization", "503"),
    INTERNAL_SERVER_ERROR_WHEN_SAVE_DATA("internal.server.error.when.save.data","504"),

    ;
    private final String message;
    private final String errorCode;

    ExternalOrganizationExceptionHandler(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
