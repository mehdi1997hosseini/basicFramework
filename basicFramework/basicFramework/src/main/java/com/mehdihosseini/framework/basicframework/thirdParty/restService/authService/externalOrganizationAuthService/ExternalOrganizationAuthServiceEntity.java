package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService;

import com.mehdihosseini.framework.basicframework.entity.BasicEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.tokenStrategy.AuthType;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestAuthConfig.RequestAuthConfigEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestTemplateAuthConfig.RequestTemplateAuthConfigEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig.ResponseTokenConfigEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.TokenType;
import lombok.*;
import org.springframework.http.HttpMethod;

import javax.persistence.*;

@Entity
@Table(name = "TBL_EXTERNAL_ORGANIZATION_AUTH_SERVICE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExternalOrganizationAuthServiceEntity extends BasicEntity<String> {

    @Enumerated(EnumType.STRING)
    @Column(name = "TOKEN_TYPE", nullable = false)
    private TokenType tokenType;
    @Enumerated(EnumType.STRING)
    @Column(name = "AUTH_TYPE", nullable = false)
    private AuthType authType;
    @Column(name = "AUTH_URI")
    private String authUri;
    @Column(name = "HTTP_METHOD")
    private HttpMethod httpMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EXTERNAL_ORGANIZATION_INFO_ID")
    private ExternalOrganizationInfoEntity externalOrganizationInfo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REQUEST_AUTH_CONFIG_ID")
    private RequestAuthConfigEntity requestAuthConfig;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REQUEST_TEMPLATE_AUTH_CONFIG_ID")
    private RequestTemplateAuthConfigEntity requestTemplateAuthConfig;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RESPONSE_TOKEN_CONFIG_ID")
    private ResponseTokenConfigEntity responseTokenConfig;

}
