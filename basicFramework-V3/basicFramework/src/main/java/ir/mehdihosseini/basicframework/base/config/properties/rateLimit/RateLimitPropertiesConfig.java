package ir.mehdihosseini.basicframework.base.config.properties.rateLimit;

import jakarta.validation.constraints.Max;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "manager.rate-limit")
public class RateLimitPropertiesConfig {

    private Boolean isEnable = false;
    @Max(value = 999999999, message = "maxNumber can not be large value 999999999")
    private Integer maxNum = 10;
    @Max(value = 999999999, message = "maxPerSecond can not be large value 999999999")
    private Integer maxPerSecond = 30;

}
