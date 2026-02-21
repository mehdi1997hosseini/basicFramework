package ir.mehdihosseini.basicframework.base.config.properties.exceptionHandling;

import ir.mehdihosseini.basicframework.base.exceptionHandler.ResponseLanguageExceptionType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "manager.exception-handling")
public class ExceptionHandlingPropertiesConfig {

    private Boolean isEnable = false;
    private ExceptionHandlingType type;
    private List<ResponseLanguageExceptionType> supportedLangs = List.of(ResponseLanguageExceptionType.EN);
    private BasedOnFile file = new BasedOnFile();

//    static class BasedOnDatabase {}
    @Data
    public class BasedOnFile {
        private String i18nPatternName = "Error_**";
    }


}
