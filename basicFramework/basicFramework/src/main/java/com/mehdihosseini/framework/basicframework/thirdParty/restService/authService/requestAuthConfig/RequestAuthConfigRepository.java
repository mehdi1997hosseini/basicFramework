package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestAuthConfig;

import com.mehdihosseini.framework.basicframework.repository.BasicRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestAuthConfigRepository extends BasicRepository<RequestAuthConfigEntity, String> {
}
