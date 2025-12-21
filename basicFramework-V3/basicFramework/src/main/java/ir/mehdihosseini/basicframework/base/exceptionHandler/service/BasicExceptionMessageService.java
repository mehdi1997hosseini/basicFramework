package ir.mehdihosseini.basicframework.base.exceptionHandler.service;

import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionMessageModel;

import java.util.List;
import java.util.Locale;

public interface BasicExceptionMessageService {

    ExceptionMessageModel getMessage(String key, Locale locale, Object... digits);

    List<ExceptionMessageModel> getMessages(String key, Object... digits);
}
