package ir.mehdihosseini.basicframework.base.security.service;

import ir.mehdihosseini.basicframework.base.security.entity.dto.UserLoginAcceptResponseDto;
import ir.mehdihosseini.basicframework.base.security.entity.dto.UserSecurityLoginDto;
import ir.mehdihosseini.basicframework.base.security.entity.dto.UserSecurityRegistryDto;

public interface UserSecurityService {
    UserSecurityRegistryDto registry(UserSecurityRegistryDto registry);

    UserLoginAcceptResponseDto login(UserSecurityLoginDto login);
}
