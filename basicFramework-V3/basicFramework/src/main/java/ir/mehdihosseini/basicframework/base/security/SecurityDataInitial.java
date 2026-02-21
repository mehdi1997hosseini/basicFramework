package ir.mehdihosseini.basicframework.base.security;

import ir.mehdihosseini.basicframework.base.config.properties.security.SecurityPropertiesConfig;
import ir.mehdihosseini.basicframework.base.security.entity.dto.UserSecurityRegistryDto;
import ir.mehdihosseini.basicframework.base.security.service.UserSecurityService;
import ir.mehdihosseini.basicframework.base.utils.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@ConditionalOnProperty(prefix = "manager.security.default-user", name = "enable", havingValue = "true")
public class SecurityDataInitial implements CommandLineRunner {

    @Autowired
    UserSecurityService userSecurityService;
    @Autowired
    SecurityPropertiesConfig security;

    @Override
    public void run(String... args) throws Exception {
        UserSecurityRegistryDto userSecurityRegistryDto =
                new UserSecurityRegistryDto(security.getDefaultUser().getUsername(),
                        security.getDefaultUser().getPassword(),
                        security.getDefaultUser().getRoles());

        userSecurityService.registry(userSecurityRegistryDto);
    }
}
