package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestTemplateAuthConfig;

import com.mehdihosseini.framework.basicframework.service.entity.BasicEntityService;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestTemplateAuthConfig.dto.RequestTemplateAuthConfigDto;

public interface RequestTemplateAuthConfigService extends BasicEntityService<RequestTemplateAuthConfigEntity, String> {

    RequestTemplateAuthConfigEntity save(RequestTemplateAuthConfigDto dto);

}
