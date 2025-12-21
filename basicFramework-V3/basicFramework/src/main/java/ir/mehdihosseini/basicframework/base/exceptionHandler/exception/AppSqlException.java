package ir.mehdihosseini.basicframework.base.exceptionHandler.exception;

import ir.mehdihosseini.basicframework.base.exceptionHandler.BasicSpecificationException;
import jakarta.annotation.Nullable;
import lombok.Getter;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public class AppSqlException extends DataAccessException {

    private final BasicSpecificationException error;
    private String detail;
    private HttpStatus httpStatus;
    private Object[] digits;

    public AppSqlException(@Nullable String msg, @Nullable Throwable cause, BasicSpecificationException error) {
        super(msg, cause);
        this.error = error;
    }

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
        super(error.getMessageKey());
        this.error = error;
        this.detail = detail;
        this.httpStatus = httpStatus;
        this.digits = digits;
    }

}
