package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.tokenStrategy;

import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;

import java.util.HashMap;
import java.util.Map;

class SamlStrategy implements TokenStrategy {
    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationAuthServiceEntity org) {
        Map<String, String> samlRequest = new HashMap<>();
        samlRequest.put(org.getRequestTemplateAuthConfig().getSamlRequestXmlParamName(), org.getRequestAuthConfig().getSamlRequestXml());
        return samlRequest;
    }
}
