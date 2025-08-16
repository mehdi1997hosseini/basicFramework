package com.mehdihosseini.framework.basicframework.exceptionHandler.exception;

import com.mehdihosseini.framework.basicframework.exceptionHandler.BasicSpecificationException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Getter
public class AppIoException extends IOException {
    private final BasicSpecificationException error;
    private final Object[] digits;
    private HttpStatus httpStatus;

    public AppIoException(BasicSpecificationException error, Object... digits) {
        super(error.getMessage());
        this.error = error;
        this.digits = digits;
    }

    public AppIoException(BasicSpecificationException error, HttpStatus httpStatus, Object... digits) {
        super(error.getMessage());
        this.error = error;
        this.httpStatus = httpStatus;
        this.digits = digits;
    }

}
