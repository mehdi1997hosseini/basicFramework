package ir.mehdihosseini.basicframework.base.exceptionHandler.file;

import ir.mehdihosseini.basicframework.base.config.properties.ManagerPropertiesConfig;
import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionMessageModel;
import ir.mehdihosseini.basicframework.base.exceptionHandler.ResponseLanguageExceptionType;
import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppRunTimeException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.infrastrucure.BasicExceptionInfrastructureService;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicInternalSystemExceptionType;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ConditionalOnProperty(prefix = "manager.exception-handling", name = "type", havingValue = "PROPERTIES_FILE")
public class BasicExceptionInfrastructureServiceImpl implements BasicExceptionInfrastructureService {

    private final Map<String, Map<ResponseLanguageExceptionType, ExceptionMessageModel>> cache = new ConcurrentHashMap<>();
    private final ManagerPropertiesConfig managerPropertiesConfig;

    public BasicExceptionInfrastructureServiceImpl(ManagerPropertiesConfig managerPropertiesConfig) {
        this.managerPropertiesConfig = managerPropertiesConfig;
    }

    @PostConstruct
    public void loadAllErrorFiles() throws IOException {

        String pattern = File.separator
                + managerPropertiesConfig.getExceptionHandling().getFile().getI18nPatternName()
                + ".properties"; // الگوی جستجو برای فایل‌ها

        System.out.println("pattern " + pattern);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:**" + pattern);
        for (Resource resource : resources) {
            String filename = resource.getFilename();
            if (isPropertiesFile(filename))
                continue;

            ResponseLanguageExceptionType localLang = getLocalLang(filename);

            Properties props = new Properties();
            props.load(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));

            for (String key : props.stringPropertyNames()) {
                String value = props.getProperty(key);
                cache.compute(key, (k, v) -> {
                    if (v == null) {
                        Map<ResponseLanguageExceptionType, ExceptionMessageModel> map = new HashMap<>();
                        // change and add language to the object
                        map.put(localLang, new ExceptionMessageModel(value, localLang.getLanguage()));
                        return map;
                    } else {
                        if (v.containsKey(localLang)) {
                            Object[] arr = new Object[3];
                            arr[0] = key;
                            arr[1] = value;
                            arr[2] = filename;
                            throw new AppRunTimeException(BasicInternalSystemExceptionType.EXCEPTION_HANDLING_MESSAGE_KEY_IS_DUPLICATED, arr);
                        }
                        v.put(localLang, new ExceptionMessageModel(value, localLang.getLanguage()));
                        return v;
                    }
                });
            }
        }
    }

    private ResponseLanguageExceptionType getLocalLang(String filename) {
        String langPart = filename.substring(filename.lastIndexOf('_') + 1).replace(".properties", "");
        return ResponseLanguageExceptionType.of(langPart).orElseThrow(() -> new RuntimeException("value is not present"));
    }

    private boolean isPropertiesFile(String filename) {
        return filename == null
                || filename.contains(".java")
                || filename.contains(".class");
    }

    @Override
    public Map<ResponseLanguageExceptionType, ExceptionMessageModel> findAllByMessageKey(String messageKey) {
        return cache.getOrDefault(messageKey, null);
    }
}
