package com.mehdihosseini.framework.basicframework.exceptionHandler.exception;

import com.mehdihosseini.framework.basicframework.exceptionHandler.BasicSpecificationException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.SQLException;

@Getter
@Builder
public class AppSqlException extends SQLException {
    private final BasicSpecificationException error;
    private String detail;
    private HttpStatus httpStatus;
    private Object[] digits;

}
