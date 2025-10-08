package ir.mehdihosseini.basicframework.base.exceptionHandler.exception;

import ir.mehdihosseini.basicframework.base.exceptionHandler.BasicSpecificationException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Getter
public class AppIoException extends IOException {

    private final BasicSpecificationException error;
    private Object[] digits;
    private HttpStatus httpStatus;

    protected AppIoException(BasicSpecificationException error) {
        super(error.getMessageKey());
        this.error = error;
    }

}
