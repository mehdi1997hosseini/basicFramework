package ir.mehdihosseini.basicframework.base.exceptionHandler.type;

import ir.mehdihosseini.basicframework.base.exceptionHandler.BasicSpecificationException;

public enum BasicSqlExceptionType implements BasicSpecificationException {
    SQL_EXCEPTION("error.sqlException.databaseError", "-150"),
    SQL_INSERT_FAILED("error.sqlException.insert-failed", "-151"),
    SQL_DUPLICATE_DATA("error.sqlException.duplicateData", "-152"),
    SQL_DATA_NOTFOUND("error.sqlException.dataNotFound", "-153"),

    ;

    BasicSqlExceptionType(String messageKey, String errorCode) {
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
