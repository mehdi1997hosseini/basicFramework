package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.tokenStrategy;


import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;

import java.util.HashMap;
import java.util.Map;

class BasicUsernamePasswordStrategy implements TokenStrategy {
    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationAuthServiceEntity org) {
        Map<String, String> params = new HashMap<>();
        params.put(org.getRequestTemplateAuthConfig().getUsernameParamName(), org.getRequestAuthConfig().getUsername());
        params.put(org.getRequestTemplateAuthConfig().getPasswordParamName(), org.getRequestAuthConfig().getPassword());
        return params;
    }
}
