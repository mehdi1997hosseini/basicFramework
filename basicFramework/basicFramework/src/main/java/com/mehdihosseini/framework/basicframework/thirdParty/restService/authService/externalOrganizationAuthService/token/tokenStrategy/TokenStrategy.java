package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.tokenStrategy;


import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;

import java.util.Map;

public interface TokenStrategy {
    Map<String, String> prepareAuthParams(ExternalOrganizationAuthServiceEntity org);
}
