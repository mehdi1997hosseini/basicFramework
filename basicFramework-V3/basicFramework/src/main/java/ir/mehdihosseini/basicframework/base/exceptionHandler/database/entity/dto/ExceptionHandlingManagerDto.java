package ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionHandlingManagerDto implements Serializable {

    private String messageKey;
    private String statusCode;

    private String language;
    private String message;

}
