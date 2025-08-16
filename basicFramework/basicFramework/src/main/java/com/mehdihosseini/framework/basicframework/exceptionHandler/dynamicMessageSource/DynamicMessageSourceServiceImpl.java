package com.mehdihosseini.framework.basicframework.exceptionHandler.dynamicMessageSource;

import com.mehdihosseini.framework.basicframework.exceptionHandler.exception.AppRunTimeException;
import com.mehdihosseini.framework.basicframework.exceptionHandler.exceptionType.BasicSystemExceptionType;
import com.mehdihosseini.framework.basicframework.exceptionHandler.lang.ResponseLanguageExceptionType;
import com.mehdihosseini.framework.basicframework.exceptionHandler.lang.ResponseLanguagePropertiesConfig;
import com.mehdihosseini.framework.basicframework.exceptionHandler.lang.ResponseMessageDto;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DynamicMessageSourceServiceImpl implements DynamicMessageSourceService {

    private final PathMatchingResourcePatternResolver _RESOLVER = new PathMatchingResourcePatternResolver();
    private final String FILE_PATTERN;
    private final String FILE_ERROR_BASIC_PATTERN = "Error_Basic_**";
    private final ResponseLanguagePropertiesConfig responseLanguagePropertiesConfig;

    public DynamicMessageSourceServiceImpl(ResponseLanguagePropertiesConfig responseLanguagePropertiesConfig) {
        this.responseLanguagePropertiesConfig = responseLanguagePropertiesConfig;
        this.FILE_PATTERN = responseLanguagePropertiesConfig.getPatternNameFileI18n();
    }

    @PostConstruct
    private void init() throws IOException {
        if (isRunningFromJar()) {
            loadFromJar();
        } else {
            loadFromFileSystem();
        }
    }

    private boolean isRunningFromJar() {
        String className = getClass().getName().replace(".", "/");
        System.out.println("className : " + className);
        String classJar = Objects.requireNonNull(getClass().getResource("/" + className + ".class")).toString();
        System.out.println("classJar : " + classJar);
        return classJar.startsWith("jar:");
    }

    private void loadFromJar() throws IOException {
        String BASE_CLASS_PATH = "classpath*:";
        Resource[] resources = _RESOLVER.getResources(BASE_CLASS_PATH + "**/" + FILE_PATTERN + ".properties");

        for (Resource resource : resources) {
            loadPropertyFile((Path) resource);
        }
    }

    private void loadPropertyFile(Path path) throws IOException {
        InputStreamReader read = new InputStreamReader(Files.newInputStream(path), StandardCharsets.UTF_8);
        Properties prop = new Properties();
        prop.load(read);
        extractMessages(path.getFileName().toString(), prop);
    }

    private void extractMessages(String fileName, Properties props) {
        String lang = fileName.substring(fileName.lastIndexOf("_") + 1).replace(".properties", "");
        Locale locale = ResponseLanguageExceptionType.ofPropertyLocalLang(lang);

        for (String key : props.stringPropertyNames()) {
            String value = props.getProperty(key);
            messages.computeIfAbsent(key, k -> new HashMap<>()).put(locale, value);
        }
    }

    private void loadFromFileSystem() throws IOException {
        Path startPath = Paths.get("src/main/java");
        Files.walk(startPath)
                .filter(path -> path.getFileName().toString().matches(FILE_PATTERN.replace("*", ".*")) ||
                        path.getFileName().toString().matches(FILE_ERROR_BASIC_PATTERN.replace("*", ".*")))
                .forEach(path -> {
                    try {
                        loadPropertyFile(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private List<Locale> getAllSupportLanguage() {
        return responseLanguagePropertiesConfig.getSupportedLangs()
                .stream().map(ResponseLanguageExceptionType::getLocaleLanguage)
                .collect(Collectors.toList());
    }

    private String getMessage(String key, Locale locale) {
        Map<Locale, String> localizedMessages = messages.get(key);
        if (localizedMessages == null)
            throw new AppRunTimeException(BasicSystemExceptionType.SYSTEM_ERROR_KEY_IS_NOT_VALID);

        return localizedMessages.get(locale);
    }

    public String getMessageByDigits(String key, Locale locale, Object... args) {
        String template = getMessage(key, locale);
        if (args == null || args.length == 0) return template;
        try {
            return MessageFormat.format(template, args);
        } catch (IllegalArgumentException e) {
            throw new AppRunTimeException(BasicSystemExceptionType.SYSTEM_ERROR_FORMAT_MESSAGE_IS_NOT_VALID);
        } catch (Exception e) {
            throw new AppRunTimeException(BasicSystemExceptionType.SYSTEM_UNKNOWN_BUILDING_MESSAGE_ERROR);
        }
    }

    @Override
    public List<ResponseMessageDto> getMessageResponse(String key, Object... digits) {
        List<ResponseMessageDto> responseMessageDtoList = new ArrayList<>();
        List<Locale> allSupportLanguage = getAllSupportLanguage();

        for (Locale language : allSupportLanguage) {
            String message = getMessageByDigits(key, language, digits);
            responseMessageDtoList.add(new ResponseMessageDto(language.getLanguage(), message));
        }

        return responseMessageDtoList;
    }


}
