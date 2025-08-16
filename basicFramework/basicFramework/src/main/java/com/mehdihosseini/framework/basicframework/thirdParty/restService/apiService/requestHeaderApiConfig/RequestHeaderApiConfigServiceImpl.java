package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig;

import com.mehdihosseini.framework.basicframework.service.BasicDtoServiceImpl;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.dao.RequestHeaderApiConfigInfrastructureService;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.dto.RequestHeaderApiConfigDto;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.mapper.RequestHeaderApiConfigMapper;
import org.springframework.stereotype.Service;

@Service
public class RequestHeaderApiConfigServiceImpl extends BasicDtoServiceImpl<RequestHeaderApiConfigEntity, String, RequestHeaderApiConfigDto, RequestHeaderApiConfigInfrastructureService, RequestHeaderApiConfigMapper> implements RequestHeaderApiConfigService {

    public RequestHeaderApiConfigServiceImpl(RequestHeaderApiConfigInfrastructureService currentInfrastructureService, RequestHeaderApiConfigMapper mapper) {
        super(mapper, currentInfrastructureService);
    }

    @Override
    public RequestHeaderApiConfigEntity saveAndFlush(RequestHeaderApiConfigDto requestHeaderApiConfigDto) {
        return infrastructureService.save(mapper.toEntity(requestHeaderApiConfigDto));
    }

}
