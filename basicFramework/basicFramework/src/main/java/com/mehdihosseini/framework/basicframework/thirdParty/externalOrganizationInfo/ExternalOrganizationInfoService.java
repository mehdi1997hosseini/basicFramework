package com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo;

import com.mehdihosseini.framework.basicframework.service.dto.BasicDtoService;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.dto.ExternalOrganizationInfoDto;


public interface ExternalOrganizationInfoService extends BasicDtoService<ExternalOrganizationInfoDto> {

    ExternalOrganizationInfoEntity findByDto(ExternalOrganizationInfoDto dto);
    ExternalOrganizationInfoEntity create(ExternalOrganizationInfoDto dto);

}
