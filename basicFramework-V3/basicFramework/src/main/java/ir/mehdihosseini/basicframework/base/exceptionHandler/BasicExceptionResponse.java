package ir.mehdihosseini.basicframework.base.exceptionHandler;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@Builder
public class BasicExceptionResponse {

    private List<ExceptionMessageModel> exceptionMessage;
    private String code;
    private String detailMessage;
    @Builder.Default
    private LocalDateTime localDateTime = LocalDateTime.now();
    private String instanceURI;

}

