package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestAuthConfig;

import com.mehdihosseini.framework.basicframework.service.entity.BasicEntityService;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestAuthConfig.dto.RequestAuthConfigDto;

public interface RequestAuthConfigService extends BasicEntityService<RequestAuthConfigEntity, String> {
    RequestAuthConfigEntity save(RequestAuthConfigDto dto);
}
