package com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate.unified;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ExternalUnifiedResponse implements Serializable {

    private boolean isSuccess;
    private String organizationName;
    private Object responseBody;
    private ResponseType responseType;

}
