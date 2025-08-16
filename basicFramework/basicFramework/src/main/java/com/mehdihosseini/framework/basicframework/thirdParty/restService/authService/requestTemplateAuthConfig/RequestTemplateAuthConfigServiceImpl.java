package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestTemplateAuthConfig;

import com.mehdihosseini.framework.basicframework.service.BasicEntityServiceImpl;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestTemplateAuthConfig.dto.RequestTemplateAuthConfigDto;
import org.springframework.stereotype.Service;

@Service
public class RequestTemplateAuthConfigServiceImpl extends BasicEntityServiceImpl<RequestTemplateAuthConfigEntity, String, RequestTemplateAuthConfigRepository> implements RequestTemplateAuthConfigService {

    private final RequestTemplateAuthConfigMapper mapper;

    public RequestTemplateAuthConfigServiceImpl(RequestTemplateAuthConfigRepository repository, RequestTemplateAuthConfigMapper mapper) {
        super(RequestTemplateAuthConfigEntity.class, repository);
        this.mapper = mapper;
    }

    @Override
    public RequestTemplateAuthConfigEntity save(RequestTemplateAuthConfigDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

}
