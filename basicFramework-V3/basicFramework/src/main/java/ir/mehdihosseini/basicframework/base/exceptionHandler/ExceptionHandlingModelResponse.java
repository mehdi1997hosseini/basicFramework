package ir.mehdihosseini.basicframework.base.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
public class ExceptionHandlingModelResponse implements Serializable {
    private String message;
    private String statusCode;
    private String language;

    public ExceptionHandlingModelResponse(String message, String statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
