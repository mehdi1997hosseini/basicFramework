package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestTemplateAuthConfig;

import com.mehdihosseini.framework.basicframework.mapper.BasicMapper;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestTemplateAuthConfig.dto.RequestTemplateAuthConfigDto;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.mapperHelper.ExternalOrganizationCommunicationEnumMapHelper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationCommunicationEnumMapHelper.class})
public interface RequestTemplateAuthConfigMapper extends BasicMapper<RequestTemplateAuthConfigEntity, RequestTemplateAuthConfigDto> {
}
