package com.mehdihosseini.framework.basicframework.thirdParty.cache.apiServiceCache;

import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.structure.ExternalOrganizationInfoStructure;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.structure.ExternalOrganizationApiServiceStructure;

import java.util.List;
import java.util.Map;

public interface ExternalOrganizationApiCatchService {
    /** ------ CRUD ------ */
    Boolean saveOrUpdate(ExternalOrganizationApiServiceEntity entity);
    Boolean isServiceNameExistByExtOrgName(String extOrgName, String serviceName);

    ExternalOrganizationApiServiceEntity findByExtOrgNameAndServiceName(String extOrgName, String serviceName);

    <I extends ExternalOrganizationInfoStructure, S extends ExternalOrganizationApiServiceStructure> ExternalOrganizationApiServiceEntity findByExtOrgNameAndServiceName(I extOrgName, S serviceName);

    <I extends ExternalOrganizationInfoStructure> Map<String, ExternalOrganizationApiServiceEntity> findAllByExtOrgName(I extOrgName);

    <I extends ExternalOrganizationInfoStructure , S extends ExternalOrganizationApiServiceStructure > Boolean removeFromCache(I extOrgName , S serviceName);
    Boolean removeFromCache(String extOrgName , String serviceName);


    /** ------- Operation ------- */
    Boolean refreshExtOrgServiceApiByExtOrgNameAndServiceName(ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity);
    void refreshAllCache(List<ExternalOrganizationApiServiceEntity> listEntity);

}
