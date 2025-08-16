package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.dao;

import com.mehdihosseini.framework.basicframework.repository.BasicRepository;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestHeaderApiConfigRepository extends BasicRepository<RequestHeaderApiConfigEntity, String> {
}
