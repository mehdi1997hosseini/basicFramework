package com.mehdihosseini.framework.basicframework.thirdParty.cache.apiServiceCache;

import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.structure.ExternalOrganizationInfoStructure;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.structure.ExternalOrganizationApiServiceStructure;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ExternalOrganizationApiCatchServiceImpl implements ExternalOrganizationApiCatchService {

    private final Map<String, Map<String, ExternalOrganizationApiServiceEntity>> extOrgServiceMap = new ConcurrentHashMap<>();

    @Override
    public Boolean saveOrUpdate(ExternalOrganizationApiServiceEntity entity) {
        ExternalOrganizationInfoEntity externalOrganizationInfo = entity.getExternalOrganizationInfo();
        if (Boolean.TRUE.equals(isServiceNameExistByExtOrgName(externalOrganizationInfo.getExternalOrganizationNameEn(), entity.getServiceName()))) {
            return update(entity);
        } else {
            extOrgServiceMap.put(externalOrganizationInfo.getExternalOrganizationNameEn(), Map.of(entity.getServiceName(), entity));
            return true;
        }
    }

    @Override
    public ExternalOrganizationApiServiceEntity findByExtOrgNameAndServiceName(String extOrgName, String serviceName) {
        return extOrgServiceMap.get(extOrgName).get(serviceName);
    }

    @Override
    public <I extends ExternalOrganizationInfoStructure, S extends ExternalOrganizationApiServiceStructure> ExternalOrganizationApiServiceEntity findByExtOrgNameAndServiceName(I extOrgName, S serviceName) {
        return findByExtOrgNameAndServiceName(extOrgName.getExternalOrganizationNameEn(),serviceName.getServiceName());
    }

    @Override
    public <I extends ExternalOrganizationInfoStructure, S extends ExternalOrganizationApiServiceStructure> Boolean removeFromCache(I extOrgName, S serviceName) {
        return removeFromCache(extOrgName.getExternalOrganizationNameEn(),serviceName.getServiceName());
    }

    @Override
    public Boolean removeFromCache(String extOrgName, String serviceName) {
        ExternalOrganizationApiServiceEntity findByExtOrgNameAndServiceName = findByExtOrgNameAndServiceName(extOrgName, serviceName);
        return findByExtOrgNameAndServiceName != null && extOrgServiceMap.get(extOrgName).remove(serviceName, findByExtOrgNameAndServiceName);
    }

    @Override
    public Boolean isServiceNameExistByExtOrgName(String extOrgName, String serviceName) {
        return extOrgServiceMap.get(extOrgName).containsKey(serviceName);
    }

    @Override
    public <I extends ExternalOrganizationInfoStructure> Map<String, ExternalOrganizationApiServiceEntity> findAllByExtOrgName(I extOrgName) {
        return extOrgServiceMap.get(extOrgName.getExternalOrganizationNameEn());
    }

    @Override
    public Boolean refreshExtOrgServiceApiByExtOrgNameAndServiceName(ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity) {
        return saveOrUpdate(externalOrganizationApiServiceEntity);
    }

    @Override
    public void refreshAllCache(List<ExternalOrganizationApiServiceEntity> listEntity) {
        if (listEntity == null || listEntity.isEmpty()) return;

        listEntity.forEach(this::saveOrUpdate);
    }

    private Boolean update(ExternalOrganizationApiServiceEntity entity) {
        ExternalOrganizationInfoEntity externalOrganizationInfo = entity.getExternalOrganizationInfo();
        ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity = extOrgServiceMap.get(externalOrganizationInfo.
                getExternalOrganizationNameEn()).get(entity.getServiceName());
        // replace by <key , oldValue , newValue>
        return extOrgServiceMap.get(externalOrganizationInfo.getExternalOrganizationNameEn()).replace(entity.getServiceName(), externalOrganizationApiServiceEntity, entity);
    }

}
