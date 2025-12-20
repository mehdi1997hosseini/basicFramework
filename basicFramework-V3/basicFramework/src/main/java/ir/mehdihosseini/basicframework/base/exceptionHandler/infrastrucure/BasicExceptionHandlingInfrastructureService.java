package ir.mehdihosseini.basicframework.base.exceptionHandler.infrastrucure;

import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionHandlingModelResponse;
import ir.mehdihosseini.basicframework.base.exceptionHandler.lang.ResponseLanguageExceptionType;

import java.util.Map;

public interface BasicExceptionHandlingInfrastructureService {

    Map<ResponseLanguageExceptionType, ExceptionHandlingModelResponse> findAllByMessageKey(String messageKey);

}
