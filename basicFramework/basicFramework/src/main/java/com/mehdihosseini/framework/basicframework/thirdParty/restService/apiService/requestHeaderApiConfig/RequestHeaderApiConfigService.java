package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig;

import com.mehdihosseini.framework.basicframework.service.dto.BasicDtoService;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.dto.RequestHeaderApiConfigDto;

public interface RequestHeaderApiConfigService extends BasicDtoService<RequestHeaderApiConfigDto> {
    RequestHeaderApiConfigEntity saveAndFlush(RequestHeaderApiConfigDto requestHeaderApiConfigDto);
}
