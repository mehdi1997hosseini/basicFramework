package ir.mehdihosseini.basicframework.base.exceptionHandler.service;

import ir.mehdihosseini.basicframework.base.config.properties.ManagerPropertiesConfig;
import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionMessageModel;
import ir.mehdihosseini.basicframework.base.exceptionHandler.ResponseLanguageExceptionType;
import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppRunTimeException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.infrastrucure.BasicExceptionInfrastructureService;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicInternalSystemExceptionType;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class BasicExceptionMessageServiceImpl implements BasicExceptionMessageService {

    private final BasicExceptionInfrastructureService infrastructure;
    private final ManagerPropertiesConfig managerPropertiesConfig;

    public BasicExceptionMessageServiceImpl(BasicExceptionInfrastructureService infrastructure, ManagerPropertiesConfig managerPropertiesConfig) {
        this.infrastructure = infrastructure;
        this.managerPropertiesConfig = managerPropertiesConfig;
    }

    @Override
    public ExceptionMessageModel getMessage(String messageKey, Locale locale, Object... digits) {
        ExceptionMessageModel templateMessage = getTemplateMessage(messageKey, locale);
        templateMessage.setMessage(convertMessageByDigits(templateMessage.getMessage(), digits));
        return templateMessage;
    }

    @Override
    public List<ExceptionMessageModel> getMessages(String messageKey, Object... digits) {
        List<ExceptionMessageModel> templateMessage = getTemplateMessage(messageKey);
        templateMessage.forEach(template -> {
            template.setMessage(convertMessageByDigits(template.getMessage(), digits));
        });

        return templateMessage;
    }

    private List<ExceptionMessageModel> getTemplateMessage(String messageKey) {
        Map<ResponseLanguageExceptionType, ExceptionMessageModel> allResponseExceptionByKey = infrastructure.findAllByMessageKey(messageKey);

        List<ExceptionMessageModel> list = new ArrayList<>();

        managerPropertiesConfig.getExceptionHandling().getSupportedLangs().forEach(lang ->
                list.add(allResponseExceptionByKey.get(lang)));

        return list;
    }

    private ExceptionMessageModel getTemplateMessage(String messageKey, Locale locale) {
        return infrastructure.findAllByMessageKey(messageKey).get(ResponseLanguageExceptionType.of(locale.getLanguage()));
    }


    private String convertMessageByDigits(String messageResponse, Object... digits) {
        try {
            if (digits != null)
                return MessageFormat.format(messageResponse, digits);
            return messageResponse;
        } catch (IllegalArgumentException e) {
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INTERNAL_SERVER_ERROR, "خطا در فرمت پیام.");
        } catch (Exception e) {
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INTERNAL_SERVER_ERROR, "خطای ناشناخته در ساخت پیام خطا.");
        }
    }

}
