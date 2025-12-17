package ir.mehdihosseini.basicframework.base.exceptionHandler.service;

import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionHandlingModelResponse;

import java.util.List;
import java.util.Locale;

public interface BasicResolveExceptionHandlingService {

    ExceptionHandlingModelResponse getMessage(String key, Object... digits);

    ExceptionHandlingModelResponse getMessage(String key, Locale locale, Object... digits);

    List<ExceptionHandlingModelResponse> getMessages(String key, Object... digits);

    List<ExceptionHandlingModelResponse> getMessages(String key, Locale locale, Object... digits);

}
