package ir.mehdihosseini.basicframework.base.security;

import ir.mehdihosseini.basicframework.base.security.entity.dto.UserSecurityRegistryDto;
import ir.mehdihosseini.basicframework.base.security.service.UserSecurityService;
import ir.mehdihosseini.basicframework.base.utils.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SecurityDataInitial implements CommandLineRunner {

    @Autowired
    UserSecurityService userSecurityService;

    @Override
    public void run(String... args) throws Exception {
        UserSecurityRegistryDto userSecurityRegistryDto = new UserSecurityRegistryDto("mehdi", "mehdi", Set.of(RoleType.ROLE_SUPPER_ADMIN));
        userSecurityService.registry(userSecurityRegistryDto);
    }
}
