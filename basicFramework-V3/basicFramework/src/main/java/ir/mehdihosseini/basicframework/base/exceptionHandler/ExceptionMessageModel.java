package ir.mehdihosseini.basicframework.base.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
public class ExceptionMessageModel implements Serializable {
    private String message;
    private String statusCode;
    private String language;

    public ExceptionMessageModel(String message, String language) {
        this.message = message;
        this.language = language;
    }
}
