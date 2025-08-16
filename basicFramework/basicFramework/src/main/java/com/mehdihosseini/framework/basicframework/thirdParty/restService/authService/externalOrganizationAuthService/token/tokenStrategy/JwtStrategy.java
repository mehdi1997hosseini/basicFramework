package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.tokenStrategy;


import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;

import java.util.Map;

class JwtStrategy implements TokenStrategy {
    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationAuthServiceEntity org) {
        return Map.of();
    }
}
