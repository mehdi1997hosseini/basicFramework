package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.mapper;

import com.mehdihosseini.framework.basicframework.mapper.BasicMapper;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.dto.RequestHeaderApiConfigDto;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.mapperHelper.ExternalOrganizationCommunicationEnumMapHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationCommunicationEnumMapHelper.class})
public interface RequestHeaderApiConfigMapper extends BasicMapper<RequestHeaderApiConfigEntity, RequestHeaderApiConfigDto> {

    @Override
    @Mappings({@Mapping(source = "tokenHeaderName", target = "tokenHeaderName", qualifiedByName = "stringToTokenHeaderName"),
            @Mapping(source = "contentType", target = "contentType", qualifiedByName = "stringToContentType"),
            @Mapping(source = "tokenType", target = "tokenType", qualifiedByName = "stringToTokenType")})
    RequestHeaderApiConfigEntity toEntity(RequestHeaderApiConfigDto requestHeaderApiConfigDto);

    @Override
    @Mappings({@Mapping(source = "tokenHeaderName", target = "tokenHeaderName", qualifiedByName = "tokenHeaderNameToString"),
            @Mapping(source = "contentType", target = "contentType", qualifiedByName = "contentTypeToString"),
            @Mapping(source = "tokenType", target = "tokenType", qualifiedByName = "tokenTypeToString")})
    RequestHeaderApiConfigDto toDto(RequestHeaderApiConfigEntity requestHeaderApiConfigEntity);

}
