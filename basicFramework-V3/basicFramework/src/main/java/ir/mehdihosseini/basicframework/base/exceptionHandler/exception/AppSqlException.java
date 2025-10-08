package ir.mehdihosseini.basicframework.base.exceptionHandler.exception;

import ir.mehdihosseini.basicframework.base.exceptionHandler.BasicSpecificationException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicSqlExceptionType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public class AppSqlException {

    private static final String DUPLICATE_VALUE = "23505";

    private final BasicSpecificationException error;
    private String detail;
    private HttpStatus httpStatus;
    private Object[] digits;

    public AppSqlException(BasicSpecificationException error) {
        this(error, null, BAD_REQUEST, (Object) null);
    }

    public AppSqlException(BasicSpecificationException error, String detail) {
        this(error, detail, BAD_REQUEST, (Object) null);
    }

    public AppSqlException(BasicSpecificationException error, String detail, Object... digits) {
        this(error, detail, BAD_REQUEST, digits);
    }

    public AppSqlException(BasicSpecificationException error, String detail, HttpStatus httpStatus, Object... digits) {
        this.error = error;
        this.detail = detail;
        this.httpStatus = httpStatus;
        this.digits = digits;
    }

    public static AppSqlException doJob(SQLException exception) {
        if (exception.getSQLState().equals(DUPLICATE_VALUE))
            return new AppSqlException(BasicSqlExceptionType.SQL_DUPLICATE_DATA, getSqlExceptionMessageDetails(exception.getMessage()), BAD_REQUEST);
        else
            return new AppSqlException(BasicSqlExceptionType.SQL_EXCEPTION, getSqlExceptionMessageDetails(exception.getMessage()), BAD_REQUEST);
    }

    private static String getSqlExceptionMessageDetails(String message) {
        List<String> list = Arrays.stream(message.split("\n")).toList();
        return list.get(list.size() - 1);
    }

}
