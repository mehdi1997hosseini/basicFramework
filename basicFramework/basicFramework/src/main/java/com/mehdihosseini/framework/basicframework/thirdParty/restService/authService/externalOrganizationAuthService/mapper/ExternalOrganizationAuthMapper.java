package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.mapper;

import com.mehdihosseini.framework.basicframework.mapper.BasicMapper;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.mapper.ExternalOrganizationInfoMapper;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.dto.ExternalOrganizationAuthServiceDto;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.mapperHelper.ExternalOrganizationCommunicationEnumMapHelper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationCommunicationEnumMapHelper.class,
        ExternalOrganizationInfoMapper.class})
public interface ExternalOrganizationAuthMapper extends BasicMapper<ExternalOrganizationAuthServiceEntity, ExternalOrganizationAuthServiceDto> {
    @Override
    @Mapping(source = "httpMethod", target = "httpMethod", qualifiedByName = "stringToHttpMethod")
    ExternalOrganizationAuthServiceEntity toEntity(ExternalOrganizationAuthServiceDto externalOrganizationAuthServiceDto);

    @Override
    @Mapping(source = "httpMethod", target = "httpMethod", qualifiedByName = "httpMethodToString")
    ExternalOrganizationAuthServiceDto toDto(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "httpMethod", target = "httpMethod", qualifiedByName = "stringToHttpMethod")
    void updateFromDto(ExternalOrganizationAuthServiceDto dto, @MappingTarget ExternalOrganizationAuthServiceEntity entity);

}
