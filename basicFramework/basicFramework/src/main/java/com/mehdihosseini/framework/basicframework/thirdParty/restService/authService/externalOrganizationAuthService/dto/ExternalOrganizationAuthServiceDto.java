package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.dto;

import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.tokenStrategy.AuthType;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestAuthConfig.dto.RequestAuthConfigDto;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestTemplateAuthConfig.dto.RequestTemplateAuthConfigDto;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig.dto.ResponseTokenConfigDto;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalOrganizationAuthServiceDto implements Serializable {
    @NotNull
    private AuthType authType;
    @NotNull
    private TokenType tokenType;
    @NotNull
    private String authUri;
    private String httpMethod;

    private RequestAuthConfigDto requestAuthConfig;
    private RequestTemplateAuthConfigDto requestTemplateAuthConfig;
    private ResponseTokenConfigDto responseTokenConfigDto;

}
