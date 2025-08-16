package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestAuthConfig;

import com.mehdihosseini.framework.basicframework.mapper.BasicMapper;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestAuthConfig.dto.RequestAuthConfigDto;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.mapperHelper.ExternalOrganizationCommunicationEnumMapHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationCommunicationEnumMapHelper.class})
public interface RequestAuthConfigMapper extends BasicMapper<RequestAuthConfigEntity, RequestAuthConfigDto> {
    @Override
    @Mappings({@Mapping(source = "contentType", target = "contentType", qualifiedByName = "stringToContentType")})
    RequestAuthConfigEntity toEntity(RequestAuthConfigDto dto);

    @Override
    @Mappings({@Mapping(source = "contentType", target = "contentType", qualifiedByName = "contentTypeToString")})
    RequestAuthConfigDto toDto(RequestAuthConfigEntity requestAuthConfigEntity);

}
