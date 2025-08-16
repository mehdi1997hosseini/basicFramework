package com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo;

import com.mehdihosseini.framework.basicframework.service.BasicDtoServiceImpl;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.dao.ExternalOrganizationInfoInfrastructureService;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.dto.ExternalOrganizationInfoDto;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.mapper.ExternalOrganizationInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalOrganizationInfoServiceImpl extends BasicDtoServiceImpl<ExternalOrganizationInfoEntity,String
        , ExternalOrganizationInfoDto, ExternalOrganizationInfoInfrastructureService, ExternalOrganizationInfoMapper> implements ExternalOrganizationInfoService {

    public ExternalOrganizationInfoServiceImpl(ExternalOrganizationInfoMapper mapper, ExternalOrganizationInfoInfrastructureService infrastructureService) {
        super(mapper, infrastructureService);
    }

    @Override
    public ExternalOrganizationInfoEntity findByDto(ExternalOrganizationInfoDto dto) {
        return infrastructureService.findAllByExternalOrganizationName(dto.getExternalOrganizationNameEn());
    }

    @Override
    public ExternalOrganizationInfoEntity create(ExternalOrganizationInfoDto dto) {
        ExternalOrganizationInfoEntity externalOrganizationInfo = infrastructureService.findAllByExternalOrganizationName(dto.getExternalOrganizationNameEn());
        if (externalOrganizationInfo != null && externalOrganizationInfo.getIsDelete().equals(Boolean.TRUE)) {
            externalOrganizationInfo.setIsDelete(true);
            infrastructureService.save(externalOrganizationInfo);
        }
        return infrastructureService.save(mapper.toEntity(dto));
    }

}
