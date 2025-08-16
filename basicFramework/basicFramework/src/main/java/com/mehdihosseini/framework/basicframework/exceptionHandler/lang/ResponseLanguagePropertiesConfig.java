package com.mehdihosseini.framework.basicframework.exceptionHandler.lang;

import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Getter
public class ResponseLanguagePropertiesConfig {
    private List<ResponseLanguageExceptionType> supportedLangs;
    private final ResponseLanguageExceptionType defaultLanguage;
    private String patternNameFileI18n;

    public ResponseLanguagePropertiesConfig(Environment env) {
        String valuesLanguage = env.getProperty("app.response.supported-languages.exception", "en");   // پیش‌فرض en
        this.supportedLangs = Arrays.stream(valuesLanguage.split(","))
                .map(ResponseLanguageExceptionType::of)
                .flatMap(Optional::stream)
                .distinct()
                .collect(Collectors.toList());

        String defaultLanguage = env.getProperty("app.response.supported-languages.default.exception");
        this.defaultLanguage = ResponseLanguageExceptionType.of(defaultLanguage).orElse(ResponseLanguageExceptionType.EN);

        this.patternNameFileI18n = env.getProperty("app.response.exception.basic.pattern.name-file.i18n", "Error_**");
        if (patternNameFileI18n.isEmpty()) {
            this.patternNameFileI18n = "Error_**";
        }

        if (!patternNameFileI18n.contains("**") || !patternNameFileI18n.contains("_**")) {
            this.patternNameFileI18n += "**";
        }

        // اگر کاربر مقدار نامعتبر داده باشد یا لیست خالی شود ⇒ فقط EN
        if (this.supportedLangs.isEmpty()) {
            this.supportedLangs = List.of(ResponseLanguageExceptionType.EN);
        }
    }

    public boolean isSupported(ResponseLanguageExceptionType lang) {
        return supportedLangs.contains(lang);
    }
}
