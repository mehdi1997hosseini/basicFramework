package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig.mapper;

import com.mehdihosseini.framework.basicframework.mapper.BasicMapper;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig.ResponseTokenConfigEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig.dto.ResponseTokenConfigDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponseTokenConfigMapper extends BasicMapper<ResponseTokenConfigEntity, ResponseTokenConfigDto> {
}
