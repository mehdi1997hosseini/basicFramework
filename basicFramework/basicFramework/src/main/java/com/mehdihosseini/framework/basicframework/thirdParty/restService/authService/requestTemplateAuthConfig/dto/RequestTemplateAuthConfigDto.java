package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestTemplateAuthConfig.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestTemplateAuthConfigDto implements Serializable {

    private String clientIdParamName;
    private String clientSecretParamName;
    private String usernameParamName;
    private String passwordParamName;
    private String grantTypeParamName;
    private String contentTypeParamName;
    private String scopeParamName;
    private String redirectUriParamName;
    private String samlRequestXmlParamName;
    private String staticTokenParamName;

}
