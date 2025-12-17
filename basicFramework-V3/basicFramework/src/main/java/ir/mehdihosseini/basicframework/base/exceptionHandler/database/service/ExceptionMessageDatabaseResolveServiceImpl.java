package ir.mehdihosseini.basicframework.base.exceptionHandler.database.service;

import ir.mehdihosseini.basicframework.base.config.properties.ManagerPropertiesConfig;
import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionHandlingModelResponse;
import ir.mehdihosseini.basicframework.base.exceptionHandler.dao.MaintenanceMessageExceptionDao;
import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppRunTimeException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.lang.ResponseLanguageExceptionType;
import ir.mehdihosseini.basicframework.base.exceptionHandler.service.BasicResolveExceptionHandlingService;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicInternalSystemExceptionType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@ConditionalOnProperty(prefix = "manager.exception-handling", name = "type", havingValue = "DATABASE")
public class ExceptionMessageDatabaseResolveServiceImpl implements BasicResolveExceptionHandlingService {

    private final MaintenanceMessageExceptionDao dao;
    private final ManagerPropertiesConfig managerPropertiesConfig;

    public ExceptionMessageDatabaseResolveServiceImpl(MaintenanceMessageExceptionDao dao, ManagerPropertiesConfig managerPropertiesConfig) {
        this.dao = dao;
        this.managerPropertiesConfig = managerPropertiesConfig;
    }

    @Override
    public ExceptionHandlingModelResponse getMessage(String key, Object... digits) {



        return null;
    }

    @Override
    public ExceptionHandlingModelResponse getMessage(String key, Locale locale, Object... digits) {
        return null;
    }

    @Override
    public List<ExceptionHandlingModelResponse> getMessages(String key, Object... digits) {
        return List.of();
    }

    @Override
    public List<ExceptionHandlingModelResponse> getMessages(String key, Locale locale, Object... digits) {
        return List.of();
    }


    private List<String> getTemplateMessage(String key){
        Map<ResponseLanguageExceptionType, ExceptionHandlingModelResponse> allResponseExceptionByKey = dao.findAllResponseExceptionByKey(key);

        List<ExceptionHandlingModelResponse> list = new ArrayList<>();

        managerPropertiesConfig.getExceptionHandling().getSupportedLangs().forEach(lang ->
                list.add(allResponseExceptionByKey.get(lang)));

        return list.stream().map(ExceptionHandlingModelResponse::getMessage).toList();
    }

    private String convertMessageByDigits(String messageResponse, Object... digits) {
        try {
            return MessageFormat.format(messageResponse, digits);
        } catch (IllegalArgumentException e) {
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INTERNAL_SERVER_ERROR, "خطا در فرمت پیام.");
        } catch (Exception e) {
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INTERNAL_SERVER_ERROR, "خطای ناشناخته در ساخت پیام خطا.");
        }
    }
}
