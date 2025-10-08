package ir.mehdihosseini.basicframework.base.exceptionHandler.lang;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "exception-handling.language-response")
public record ExceptionLanguagePropertiesConfig(
        List<ResponseLanguageExceptionType> supportedLangs,
        String patternNameFileI18n
) {

    public ExceptionLanguagePropertiesConfig {
        if (supportedLangs == null || supportedLangs.isEmpty()) {
            supportedLangs = List.of(ResponseLanguageExceptionType.EN);
        }
        if (patternNameFileI18n == null || patternNameFileI18n.isEmpty()) {
            patternNameFileI18n = "Error_**";
        }

        if (!patternNameFileI18n.contains("**") || !patternNameFileI18n.contains("_**")) {
            patternNameFileI18n += "**";
        }
    }

    public String getDefaultLanguage() {
        return supportedLangs.get(0).name();
    }

}