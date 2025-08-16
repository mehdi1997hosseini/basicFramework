package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig;

import com.mehdihosseini.framework.basicframework.service.BasicEntityServiceImpl;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig.dto.ResponseTokenConfigDto;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig.mapper.ResponseTokenConfigMapper;
import org.springframework.stereotype.Service;

@Service
public class ResponseTokenConfigServiceImpl extends BasicEntityServiceImpl<ResponseTokenConfigEntity, String, ResponseTokenConfigRepository> implements ResponseTokenConfigService {
    private final ResponseTokenConfigMapper mapper;

    public ResponseTokenConfigServiceImpl(ResponseTokenConfigRepository repository, ResponseTokenConfigMapper mapper) {
        super(ResponseTokenConfigEntity.class, repository);
        this.mapper = mapper;
    }

    @Override
    public ResponseTokenConfigEntity save(ResponseTokenConfigDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

}
