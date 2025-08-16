package com.mehdihosseini.framework.basicframework.exceptionHandler;

import com.mehdihosseini.framework.basicframework.exceptionHandler.lang.ResponseMessageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@Builder
public class BasicResponseException {
    private List<ResponseMessageDto> responseMessage;
    private String code;
    private String detailMessage;
    @Builder.Default
    private LocalDateTime localDateTime = LocalDateTime.now();
    private String instanceURI;

}
