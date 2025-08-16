package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestAuthConfig;

import com.mehdihosseini.framework.basicframework.service.BasicEntityServiceImpl;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestAuthConfig.dto.RequestAuthConfigDto;
import org.springframework.stereotype.Service;

@Service
public class RequestAuthConfigServiceImpl extends BasicEntityServiceImpl<RequestAuthConfigEntity, String, RequestAuthConfigRepository> implements RequestAuthConfigService {

    private final RequestAuthConfigMapper mapper;

    public RequestAuthConfigServiceImpl(RequestAuthConfigRepository repository, RequestAuthConfigMapper mapper) {
        super(RequestAuthConfigEntity.class, repository);
        this.mapper = mapper;
    }


    @Override
    public RequestAuthConfigEntity save(RequestAuthConfigDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

}
