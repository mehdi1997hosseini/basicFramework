package ir.mehdihosseini.basicframework.base.exceptionHandler.dao;

import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionHandlingModelResponse;
import ir.mehdihosseini.basicframework.base.exceptionHandler.lang.ResponseLanguageExceptionType;

import java.util.Map;

public interface MaintenanceMessageExceptionDao {

    Map<ResponseLanguageExceptionType, ExceptionHandlingModelResponse> findAllResponseExceptionByKey(String key);
    ExceptionHandlingModelResponse findResponseExceptionByKeyAndLanguage(String key , ResponseLanguageExceptionType language);

}
