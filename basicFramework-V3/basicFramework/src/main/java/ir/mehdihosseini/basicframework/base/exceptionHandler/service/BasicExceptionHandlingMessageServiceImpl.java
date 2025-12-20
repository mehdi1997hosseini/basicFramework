package ir.mehdihosseini.basicframework.base.exceptionHandler.service;

import ir.mehdihosseini.basicframework.base.config.properties.ManagerPropertiesConfig;
import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionHandlingModelResponse;
import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppRunTimeException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.infrastrucure.BasicExceptionHandlingInfrastructureService;
import ir.mehdihosseini.basicframework.base.exceptionHandler.lang.ResponseLanguageExceptionType;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicInternalSystemExceptionType;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class BasicExceptionHandlingMessageServiceImpl implements BasicExceptionHandlingMessageService {

    private final BasicExceptionHandlingInfrastructureService infrastructure;
    private final ManagerPropertiesConfig managerPropertiesConfig;

    public BasicExceptionHandlingMessageServiceImpl(BasicExceptionHandlingInfrastructureService infrastructure, ManagerPropertiesConfig managerPropertiesConfig) {
        this.infrastructure = infrastructure;
        this.managerPropertiesConfig = managerPropertiesConfig;
    }

    @Override
    public ExceptionHandlingModelResponse getMessage(String messageKey, Locale locale, Object... digits) {
        ExceptionHandlingModelResponse templateMessage = getTemplateMessage(messageKey, locale);
        templateMessage.setMessage(convertMessageByDigits(templateMessage.getMessage(), digits));
        return templateMessage;
    }

    @Override
    public List<ExceptionHandlingModelResponse> getMessages(String messageKey, Object... digits) {
        List<ExceptionHandlingModelResponse> templateMessage = getTemplateMessage(messageKey);
        templateMessage.forEach(template -> {
            template.setMessage(convertMessageByDigits(template.getMessage(), digits));
        });

        return templateMessage;
    }

    private List<ExceptionHandlingModelResponse> getTemplateMessage(String messageKey) {
        Map<ResponseLanguageExceptionType, ExceptionHandlingModelResponse> allResponseExceptionByKey = infrastructure.findAllByMessageKey(messageKey);

        List<ExceptionHandlingModelResponse> list = new ArrayList<>();

        managerPropertiesConfig.getExceptionHandling().getSupportedLangs().forEach(lang ->
                list.add(allResponseExceptionByKey.get(lang)));

        return list;
    }

    private ExceptionHandlingModelResponse getTemplateMessage(String messageKey, Locale locale) {
        return infrastructure.findAllByMessageKey(messageKey).get(ResponseLanguageExceptionType.of(locale.getLanguage()));
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
