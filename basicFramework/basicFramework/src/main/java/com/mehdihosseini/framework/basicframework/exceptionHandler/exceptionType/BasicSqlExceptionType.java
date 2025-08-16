package com.mehdihosseini.framework.basicframework.exceptionHandler.exceptionType;

import com.mehdihosseini.framework.basicframework.exceptionHandler.BasicSpecificationException;

public enum BasicSqlExceptionType implements BasicSpecificationException {
    SQL_EXCEPTION("error.sqlException.databaseError", "-150"),
    SQL_INSERT_FAILED("error.sqlException.insert-failed","-151"),
    SQL_DUPLICATE_DATA("error.sqlException.duplicateData", "-152"),
    SQL_DATA_NOTFOUND("error.sqlException.dataNotFound", "-153"),

    ;

    BasicSqlExceptionType(String message, String errorCode) {
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
