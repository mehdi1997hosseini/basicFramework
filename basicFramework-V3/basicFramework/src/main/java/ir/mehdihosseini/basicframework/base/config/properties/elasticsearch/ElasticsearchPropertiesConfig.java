package ir.mehdihosseini.basicframework.base.config.properties.elasticsearch;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "manager.elastic-search")
public class ElasticsearchPropertiesConfig {

    private String host = "localhost";
    private Integer port = 9200;
    private String username;
    private String password;
    private String token;
    private Integer connectionTimeOut = 3000000;
    private TraceRequestPropertiesConfig trace;

}
