//package com.mehdihosseini.framework.basicframework.exceptionHandler;
//
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import java.text.MessageFormat;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Properties;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @Author M.Hosseini
// * @Description
// * @Pattern error_{nameFile}_{fa (persian) or en (english)}.properties
// */
//@Component
//public class DynamicMessageSource {
//
//    private final Map<String, Map<Locale, String>> messages = new ConcurrentHashMap<>();
//
//    @PostConstruct
//    public void loadAllErrorFiles() throws IOException {
//        String basePath = "src/main/java/com/example/demo"; // مسیر اصلی پکیج‌های
//        String pattern = basePath + "/**/Error_*.properties"; // الگوی جستجو برای فایل‌ها
//
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource[] resources = resolver.getResources("file:" + pattern);
//        for (Resource resource : resources) {
//            String filename = resource.getFilename();
//            if (filename == null || !filename.startsWith("Error_")) continue;
//
//            String langPart = filename.substring(filename.lastIndexOf('_') + 1).replace(".properties", "");
//            Locale locale = langPart.equalsIgnoreCase("fa") ? new Locale("fa") : Locale.ENGLISH;
//
//            Properties props = new Properties();
//            props.load(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
//
//            for (String key : props.stringPropertyNames()) {
//                String value = props.getProperty(key);
//
//                messages
//                        .computeIfAbsent(key, k -> new HashMap<>())
//                        .put(locale, value);
//            }
//        }
//    }
//
//    public String getMessage(String key, Locale locale) {
//        Map<Locale, String> localizedMessages = messages.get(key);
//        if (localizedMessages == null) return "کلید نامعتبر";
//
//        return localizedMessages.getOrDefault(locale, localizedMessages.get(Locale.ENGLISH));
//    }
//
//    public String convertMessageByDigits(String key, Locale locale, Object... args) {
//        String template = getMessage(key, locale);
//        if (args == null || args.length == 0) return template;
//        try {
//            return MessageFormat.format(template, args);
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("خطا در فرمت پیام: ");
//        } catch (Exception e) {
//            throw new IllegalArgumentException("خطای ناشناخته در ساخت پیام خطا.");
//        }
//    }
//
//}
