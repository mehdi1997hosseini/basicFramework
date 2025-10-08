package ir.mehdihosseini.basicframework.base.exceptionHandler.lang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class ResponseMessageDto {

    private String language;
    private String message;

}
