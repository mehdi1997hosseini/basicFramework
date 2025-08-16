package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig;

import com.mehdihosseini.framework.basicframework.service.entity.BasicEntityService;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig.dto.ResponseTokenConfigDto;

public interface ResponseTokenConfigService extends BasicEntityService<ResponseTokenConfigEntity, String> {
    ResponseTokenConfigEntity save(ResponseTokenConfigDto dto);
}
