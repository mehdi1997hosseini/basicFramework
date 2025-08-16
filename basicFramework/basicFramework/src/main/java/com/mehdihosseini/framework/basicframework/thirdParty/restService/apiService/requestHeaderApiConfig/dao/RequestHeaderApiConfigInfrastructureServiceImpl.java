package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.dao;

import com.mehdihosseini.framework.basicframework.service.BasicInfrastructureServiceImpl;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestHeaderApiConfigInfrastructureServiceImpl extends BasicInfrastructureServiceImpl<RequestHeaderApiConfigEntity, String, RequestHeaderApiConfigRepository> implements RequestHeaderApiConfigInfrastructureService {

    public RequestHeaderApiConfigInfrastructureServiceImpl(Class<RequestHeaderApiConfigEntity> entityClass, RequestHeaderApiConfigRepository repository) {
        super(entityClass, repository);
    }

    @Autowired
    public RequestHeaderApiConfigInfrastructureServiceImpl(RequestHeaderApiConfigRepository repository) {
        super(repository);
    }


}
