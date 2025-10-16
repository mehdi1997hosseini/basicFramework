package ir.mehdihosseini.basicframework.base.exceptionHandler;

import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppRunTimeException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.lang.ExceptionLanguagePropertiesConfig;
import ir.mehdihosseini.basicframework.base.exceptionHandler.lang.ResponseLanguageExceptionType;
import ir.mehdihosseini.basicframework.base.exceptionHandler.lang.ResponseMessageDto;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicInternalSystemExceptionType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author M.Hosseini
 * @Description
 * @Pattern error_{nameFile}_{fa (persian) or en (english)}.properties
 */
@Component
@RequiredArgsConstructor
public class DynamicMessageSource {

    private final Map<String, Map<Locale, String>> messages = new ConcurrentHashMap<>();
    private final ExceptionLanguagePropertiesConfig languagePropertiesConfig;

    @PostConstruct
    public void loadAllErrorFiles() throws IOException {

        String pattern = File.separator
                + languagePropertiesConfig.patternNameFileI18n()
                + ".properties"; // الگوی جستجو برای فایل‌ها

        System.out.println("pattern " + pattern);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:**" + pattern);
        for (Resource resource : resources) {
            String filename = resource.getFilename();
            if (isPropertiesFile(filename))
                continue;

            String langPart = filename.substring(filename.lastIndexOf('_') + 1).replace(".properties", "");
            Locale locale = ResponseLanguageExceptionType.ofPropertyLocalLang(langPart);

            Properties props = new Properties();
            props.load(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));

            for (String key : props.stringPropertyNames()) {
                String value = props.getProperty(key);

                messages.compute(key, (k, v) -> {
                    if (v == null) {
                        Map<Locale, String> map = new HashMap<>();
                        map.put(locale, value);
                        return map;
                    } else {
                        if (v.containsKey(locale)) {
                            Object[] arr = new Object[3];
                            arr[0] = key;
                            arr[1] = value;
                            arr[2] = filename;
                            throw new AppRunTimeException(BasicInternalSystemExceptionType.EXCEPTION_HANDLING_MESSAGE_KEY_IS_DUPLICATED, arr);
                        }
                        v.put(locale, value);
                        return v;
                    }
                });
            }
        }

    }

    private boolean isPropertiesFile(String filename) {
        return filename == null
                || filename.contains(".java")
                || filename.contains(".class");
    }

    public String getMessage(String key, Locale locale) {
        Map<Locale, String> localizedMessages = messages.get(key);
        if (localizedMessages == null) return "type of message response in not valid ... ";
        return localizedMessages.getOrDefault(locale, localizedMessages.get(Locale.ENGLISH));
    }

    public String convertMessageByDigits(String key, Locale locale, Object... args) {
        String template = getMessage(key, locale);
        if (args == null || args.length == 0) return template;
        try {
            return MessageFormat.format(template, args);
        } catch (IllegalArgumentException e) {
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INTERNAL_SERVER_ERROR, "خطا در فرمت پیام.");
        } catch (Exception e) {
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INTERNAL_SERVER_ERROR, "خطای ناشناخته در ساخت پیام خطا.");
        }
    }

    public List<ResponseMessageDto> getMessageResponse(String key, Object... digits) {
        Map<Locale, String> message = messages.get(key);
        if (message == null)
            return List.of(new ResponseMessageDto(Locale.ENGLISH.getLanguage(),
                    "type of message response in not valid ... "));

        List<ResponseLanguageExceptionType> responseLanguageExceptionTypes = languagePropertiesConfig.supportedLangs();

        List<ResponseMessageDto> responseResultMessage = new ArrayList<>();

        for (ResponseLanguageExceptionType languageType : responseLanguageExceptionTypes) {
            String response = message.get(languageType.getLocaleLanguage());

            if (digits != null)
                responseResultMessage
                        .add(new ResponseMessageDto(languageType.getLanguage(), convertMessageByDigits(response, digits)));
            else
                responseResultMessage.add(new ResponseMessageDto(languageType.getLanguage(), response));
        }
        return responseResultMessage;
    }

    private String convertMessageByDigits(String messageResponse, Object... digits) {
        try {
            return MessageFormat.format(messageResponse, digits);
        } catch (IllegalArgumentException e) {
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INTERNAL_SERVER_ERROR, "خطا در فرمت پیام.");
        } catch (Exception e) {
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INTERNAL_SERVER_ERROR, "خطای ناشناخته در ساخت پیام خطا.");
        }
    }

}
