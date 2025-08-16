package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService;

import com.mehdihosseini.framework.basicframework.service.BasicDtoServiceImpl;
import com.mehdihosseini.framework.basicframework.thirdParty.cache.apiServiceCache.ExternalOrganizationApiCatchService;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.dao.ExternalOrganizationApiServiceInfrastructureService;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.dto.ExternalOrganizationApiServiceDto;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.mapper.ExternalOrganizationApiServiceMapper;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalOrganizationApiServiceImpl extends BasicDtoServiceImpl<ExternalOrganizationApiServiceEntity, String,
        ExternalOrganizationApiServiceDto,
        ExternalOrganizationApiServiceInfrastructureService,
        ExternalOrganizationApiServiceMapper> implements ExternalOrganizationApiService {

    private final RequestHeaderApiConfigService requestHeaderApiConfigService;
    private final ExternalOrganizationApiCatchService currentEntityCache;

    @Autowired
    public ExternalOrganizationApiServiceImpl(ExternalOrganizationApiServiceInfrastructureService currentInfrastructureService,
                                              ExternalOrganizationApiServiceMapper mapper,
                                              RequestHeaderApiConfigService requestHeaderApiConfigService,
                                              ExternalOrganizationApiCatchService currentEntityCache) {
        super(mapper, currentInfrastructureService);
        this.requestHeaderApiConfigService = requestHeaderApiConfigService;
        this.currentEntityCache = currentEntityCache;
    }

    @Override
    public ExternalOrganizationApiServiceDto save(ExternalOrganizationApiServiceDto dto) {
        ExternalOrganizationApiServiceEntity entity = mapper.toEntity(dto);
        entity.setRequestHeader(requestHeaderApiConfigService.saveAndFlush(dto.getRequestHeader()));

        return mapper.toDto(infrastructureService.save(entity));
    }

    @Override
    public List<ExternalOrganizationApiServiceDto> findByExternalOrgName(String extOrgName) {
        List<ExternalOrganizationApiServiceEntity> externalOrganizationApiServiceEntity = infrastructureService.findAllByExternalOrgName(extOrgName);
        if (externalOrganizationApiServiceEntity == null || externalOrganizationApiServiceEntity.isEmpty())
            return null;
        return mapper.toDto(externalOrganizationApiServiceEntity);
    }

    @Override
    public ExternalOrganizationApiServiceEntity findExtOrgApiServiceByExternalOrgNameAndServiceName(String extOrgName, String serviceName) {

        return null;
    }

    @Override
    public void refreshManuallyAllCache() {
        currentEntityCache.refreshAllCache(infrastructureService.findAll());
    }
}
