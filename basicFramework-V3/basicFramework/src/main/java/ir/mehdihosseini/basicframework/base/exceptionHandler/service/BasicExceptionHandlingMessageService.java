package ir.mehdihosseini.basicframework.base.exceptionHandler.service;

import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionHandlingModelResponse;

import java.util.List;
import java.util.Locale;

public interface BasicExceptionHandlingMessageService {

    ExceptionHandlingModelResponse getMessage(String key, Locale locale, Object... digits);

    List<ExceptionHandlingModelResponse> getMessages(String key, Object... digits);
}
