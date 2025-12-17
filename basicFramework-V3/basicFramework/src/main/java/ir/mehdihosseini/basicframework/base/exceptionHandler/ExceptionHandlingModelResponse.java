package ir.mehdihosseini.basicframework.base.exceptionHandler;

import lombok.Getter;

import java.io.Serializable;


@Getter
public class ExceptionHandlingModelResponse implements Serializable {
    private String message;
    private String code;

    public ExceptionHandlingModelResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
