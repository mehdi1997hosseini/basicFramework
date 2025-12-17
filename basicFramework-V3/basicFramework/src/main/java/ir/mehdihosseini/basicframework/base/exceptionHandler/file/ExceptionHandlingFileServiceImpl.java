package ir.mehdihosseini.basicframework.base.exceptionHandler.file;

import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionHandlingModelResponse;
import ir.mehdihosseini.basicframework.base.exceptionHandler.dao.MaintenanceMessageExceptionDao;
import ir.mehdihosseini.basicframework.base.exceptionHandler.lang.ResponseLanguageExceptionType;

import java.util.Map;



public class ExceptionHandlingFileServiceImpl implements MaintenanceMessageExceptionDao {


    @Override
    public Map<ResponseLanguageExceptionType, ExceptionHandlingModelResponse> findAllResponseExceptionByKey(String title) {
        return Map.of();
    }

    @Override
    public ExceptionHandlingModelResponse findResponseExceptionByKeyAndLanguage(String title, ResponseLanguageExceptionType language) {
        return null;
    }
}
