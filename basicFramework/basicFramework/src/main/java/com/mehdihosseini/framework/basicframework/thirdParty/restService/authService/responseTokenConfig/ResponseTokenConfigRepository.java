package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig;

import com.mehdihosseini.framework.basicframework.repository.BasicRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseTokenConfigRepository extends BasicRepository<ResponseTokenConfigEntity, String> {
}
