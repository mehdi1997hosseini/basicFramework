package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.dao;


import com.mehdihosseini.framework.basicframework.service.BasicInfrastructureService;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;

import java.util.List;

public interface ExternalOrganizationApiServiceInfrastructureService extends BasicInfrastructureService<ExternalOrganizationApiServiceEntity, String> {

    List<ExternalOrganizationApiServiceEntity> findAllByExternalOrgName(String extOrgName);
    ExternalOrganizationApiServiceEntity findByExtOrgNameAndServiceName(String extOrgName , String serviceName);
}
