package ir.mehdihosseini.basicframework.base.exceptionHandler.infrastrucure;

import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionMessageModel;
import ir.mehdihosseini.basicframework.base.exceptionHandler.ResponseLanguageExceptionType;

import java.util.Map;

public interface BasicExceptionInfrastructureService {

    Map<ResponseLanguageExceptionType, ExceptionMessageModel> findAllByMessageKey(String messageKey);

}
