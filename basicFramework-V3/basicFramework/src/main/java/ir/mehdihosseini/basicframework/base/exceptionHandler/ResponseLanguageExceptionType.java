package ir.mehdihosseini.basicframework.base.exceptionHandler;

import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public enum ResponseLanguageExceptionType {
    EN("en", new Locale("en"), "english"),
    FA("fa", new Locale("fa"), "persian / farsi"),
    AR("ar", new Locale("ar"), "arabic"),
    CH("ch", new Locale("zh", "CN"), "china");

    @Getter
    private final String language;
    private final Locale locale;

    ResponseLanguageExceptionType(String language, Locale locale, String description) {
        this.language = language;
        this.locale = locale;
    }

    public Locale getLocaleLanguage() {
        return locale;
    }

    public static Optional<ResponseLanguageExceptionType> of(String value) {
        if (value == null)
            return Optional.empty();

        return Arrays.stream(values())
                .filter(property -> property.language.equals(value.trim()) || property.name().equals(value.trim()))
                .findFirst();
    }

    public static Optional<ResponseLanguageExceptionType> of(Locale value) {
        if (value == null)
            return Optional.empty();

        return Arrays.stream(values())
                .filter(property -> property.getLocaleLanguage().equals(value))
                .findFirst();
    }

    public static Locale ofPropertyLocalLang(String lang) {
        if (lang == null)
            throw new IllegalArgumentException("language is not null for change to local lang. ");

        return Arrays.stream(values())
                .filter(val -> val.language.equalsIgnoreCase(lang) || val.name().equalsIgnoreCase(lang))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("language is not valid when get local language.")).locale;
    }

}
