package ir.mehdihosseini.basicframework.base.config.properties;

import ir.mehdihosseini.basicframework.base.config.properties.elasticsearch.ElasticsearchPropertiesConfig;
import ir.mehdihosseini.basicframework.base.config.properties.exceptionHandling.ExceptionHandlingPropertiesConfig;
import ir.mehdihosseini.basicframework.base.config.properties.rateLimit.RateLimitPropertiesConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "manager")
@Data
public class ManagerPropertiesConfig {

    private ElasticsearchPropertiesConfig elasticSearch;
    private RateLimitPropertiesConfig rateLimit;
    private ExceptionHandlingPropertiesConfig exceptionHandling;

}
