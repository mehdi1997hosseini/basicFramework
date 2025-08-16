package com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.mapper;

import com.mehdihosseini.framework.basicframework.mapper.BasicMapper;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.dto.ExternalOrganizationInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExternalOrganizationInfoMapper extends BasicMapper<ExternalOrganizationInfoEntity, ExternalOrganizationInfoDto> {
}
