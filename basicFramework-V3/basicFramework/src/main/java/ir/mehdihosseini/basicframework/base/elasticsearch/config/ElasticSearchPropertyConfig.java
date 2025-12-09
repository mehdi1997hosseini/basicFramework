package ir.mehdihosseini.basicframework.base.elasticsearch.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "manager.elastic-search")
@Setter
@Getter
public class ElasticSearchPropertyConfig {

    private String host = "localhost";
    private Integer port = 9200;
    private String username;
    private String password;
    private String token;
    private Integer connectionTimeOut = 3000000;

    public static class Trace {

        @Getter
        @Setter
        public static class InputRequests {
            private Boolean isEnable;
        }

        @Getter
        @Setter
        public static class ThirdParty {
            private Boolean isEnable;
        }

    }

}
