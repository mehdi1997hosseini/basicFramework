package com.mehdihosseini.framework.basicframework.exceptionHandler.dynamicMessageSource;

import com.mehdihosseini.framework.basicframework.exceptionHandler.lang.ResponseMessageDto;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface DynamicMessageSourceService {
    public final Map<String, Map<Locale, String>> messages = new ConcurrentHashMap<>();

    public String getMessageByDigits(String key, Locale locale, Object... args);

    public List<ResponseMessageDto> getMessageResponse(String key , Object... digits);
}
