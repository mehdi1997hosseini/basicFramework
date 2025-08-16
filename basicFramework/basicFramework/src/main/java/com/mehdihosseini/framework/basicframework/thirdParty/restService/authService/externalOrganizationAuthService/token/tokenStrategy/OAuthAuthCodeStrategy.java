package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.tokenStrategy;

import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;

import java.util.HashMap;
import java.util.Map;

class OAuthAuthCodeStrategy implements TokenStrategy {
    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationAuthServiceEntity org) {
        Map<String, String> body = new HashMap<>();
        body.put(org.getRequestTemplateAuthConfig().getAuthCodeParamName(), org.getRequestAuthConfig().getAuthCode());
        body.put(org.getRequestTemplateAuthConfig().getClientIdParamName(), org.getRequestAuthConfig().getClientId());
        body.put(org.getRequestTemplateAuthConfig().getClientSecretParamName(), org.getRequestAuthConfig().getClientSecret());
        body.put(org.getRequestTemplateAuthConfig().getRedirectUriParamName(), org.getRequestAuthConfig().getRedirectUri());
        body.put(org.getRequestTemplateAuthConfig().getGrantTypeParamName(), "authorization_code");
        return body;
    }
}
