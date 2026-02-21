package ir.mehdihosseini.basicframework.base.config.properties.security;

import ir.mehdihosseini.basicframework.base.utils.RoleType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Data
@Configuration
@ConfigurationProperties(prefix = "manager.security")
public class SecurityPropertiesConfig {
    private boolean isEnable = false;
    private DefaultUser defaultUser;

    @Data
    public static class DefaultUser {

        private boolean isEnable = false;
        private String username;
        private String password;
        private Set<RoleType> roles;

    }

}
