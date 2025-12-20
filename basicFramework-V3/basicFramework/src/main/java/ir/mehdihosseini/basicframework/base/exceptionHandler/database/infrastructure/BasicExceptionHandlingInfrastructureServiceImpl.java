package ir.mehdihosseini.basicframework.base.exceptionHandler.database.infrastructure;

import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionHandlingModelResponse;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.ExceptionManagerEntity;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.ExceptionMessageEntity;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.repository.ExceptionManagerRepository;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.repository.ExceptionMessageRepository;
import ir.mehdihosseini.basicframework.base.exceptionHandler.infrastrucure.BasicExceptionHandlingInfrastructureService;
import ir.mehdihosseini.basicframework.base.exceptionHandler.lang.ResponseLanguageExceptionType;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ConditionalOnProperty(prefix = "manager.exception-handling", name = "type", havingValue = "DATABASE")
public class BasicExceptionHandlingInfrastructureServiceImpl implements BasicExceptionHandlingInfrastructureService, ExceptionHandlingManagerInfrastructureService {

    private final ExceptionManagerRepository managerRepository;
    private final ExceptionMessageRepository messageRepository;

    private final Map<String, Map<ResponseLanguageExceptionType, ExceptionHandlingModelResponse>> cache =
            new ConcurrentHashMap<>();

    public BasicExceptionHandlingInfrastructureServiceImpl(ExceptionManagerRepository managerRepository, ExceptionMessageRepository messageRepository) {
        this.managerRepository = managerRepository;
        this.messageRepository = messageRepository;
    }

    @PostConstruct
    public void initial() {
        cacheData();
    }

    public void cacheData() {
        List<ExceptionManagerEntity> exceptionManagerEntity = managerRepository.findAll();

        for (ExceptionManagerEntity model : exceptionManagerEntity) {
            String messageKey = model.getMessageKey();
            String statusCode = model.getStatusCode();
            String code = model.getCode();
            List<ExceptionMessageEntity> findAllMessage = messageRepository.findAllByCode(code);

            findAllMessage.stream().forEach(entity -> {
                // change and add language to the object
                ExceptionHandlingModelResponse instance = new ExceptionHandlingModelResponse(entity.getMessage(), statusCode, entity.getLanguage().getLanguage());
                cache.computeIfAbsent(messageKey, inner ->
                        new EnumMap<>(ResponseLanguageExceptionType.class)).put(entity.getLanguage(), instance);
            });
        }
    }


    @Override
    public String addExceptionManager(ExceptionManagerEntity entity) {
        String code = managerRepository.save(entity).getCode();
        return code;
    }

    @Override
    public String addExceptionMessage(ExceptionMessageEntity entity) {
        String code = messageRepository.save(entity).getCode();
        return code;
    }

    @Override
    public Map<ResponseLanguageExceptionType, ExceptionHandlingModelResponse> findAllByMessageKey(String messageKey) {
        return cache.get(messageKey);
    }

}
