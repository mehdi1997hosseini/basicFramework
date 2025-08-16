package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.tokenStrategy;


import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;

import java.util.HashMap;
import java.util.Map;

class ClientCredentialsStrategy implements TokenStrategy {

    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationAuthServiceEntity org) {
        Map<String, String> params = new HashMap<>();
        params.put(org.getRequestTemplateAuthConfig().getClientIdParamName(), org.getRequestAuthConfig().getClientId());
        params.put(org.getRequestTemplateAuthConfig().getClientSecretParamName(), org.getRequestAuthConfig().getClientSecret());
        params.put(org.getRequestTemplateAuthConfig().getGrantTypeParamName(), "client_credentials");
        return params;
    }

}
