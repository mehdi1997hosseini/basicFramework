package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService;

import com.mehdihosseini.framework.basicframework.service.dto.BasicDtoService;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.dto.ExternalOrganizationApiServiceDto;

import java.util.List;

public interface ExternalOrganizationApiService extends BasicDtoService<ExternalOrganizationApiServiceDto> {

    List<ExternalOrganizationApiServiceDto> findByExternalOrgName(String extOrgName);

    public ExternalOrganizationApiServiceEntity findExtOrgApiServiceByExternalOrgNameAndServiceName(String extOrgName , String serviceName);
    void refreshManuallyAllCache();
}
