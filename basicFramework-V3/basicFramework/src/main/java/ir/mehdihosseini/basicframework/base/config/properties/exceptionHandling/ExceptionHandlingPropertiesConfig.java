package ir.mehdihosseini.basicframework.base.config.properties.exceptionHandling;

import ir.mehdihosseini.basicframework.base.exceptionHandler.lang.ResponseLanguageExceptionType;
import lombok.Data;

import java.util.List;

@Data
public class ExceptionHandlingPropertiesConfig {

    private Boolean isEnable = false;
    private ExceptionHandlingType type;
    private List<ResponseLanguageExceptionType> supportedLangs;
    private BasedOnFile file;

//    static class BasedOnDatabase {}
    @Data
    public class BasedOnFile {
        private String i18nPatternName;
    }


}
