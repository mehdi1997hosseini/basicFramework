package ir.mehdihosseini.basicframework.base.exceptionHandler.database.infrastructure;

import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionMessageModel;
import ir.mehdihosseini.basicframework.base.exceptionHandler.ResponseLanguageExceptionType;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.ExceptionManagerEntity;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.ExceptionMessageEntity;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.repository.ExceptionManagerRepository;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.repository.ExceptionMessageRepository;
import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppRunTimeException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.infrastrucure.BasicExceptionInfrastructureService;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicInternalSystemExceptionType;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ConditionalOnProperty(prefix = "manager.exception-handling", name = "type", havingValue = "DATABASE")
public class BasicExceptionInfrastructureServiceImpl implements BasicExceptionInfrastructureService, ExceptionHandlingManagerInfrastructureService {

    private final ExceptionManagerRepository managerRepository;
    private final ExceptionMessageRepository messageRepository;

    private final Map<String, Map<ResponseLanguageExceptionType, ExceptionMessageModel>> cache =
            new ConcurrentHashMap<>();

    public BasicExceptionInfrastructureServiceImpl(ExceptionManagerRepository managerRepository, ExceptionMessageRepository messageRepository) {
        this.managerRepository = managerRepository;
        this.messageRepository = messageRepository;
    }

    @PostConstruct
    public void initial() {
        cacheData();
    }

    @Transactional
    public void cacheData() {
        List<ExceptionManagerEntity> exceptionManagerEntity = managerRepository.findAll();

        for (ExceptionManagerEntity model : exceptionManagerEntity) {
            String messageKey = model.getMessageKey();
            String statusCode = model.getStatusCode();
            String code = model.getCode();
            List<ExceptionMessageEntity> findAllMessage = messageRepository.findAllByCode(code);

            findAllMessage.stream().forEach(entity -> {
                // change and add language to the object
                ExceptionMessageModel instance = new ExceptionMessageModel(entity.getMessage(), statusCode, entity.getLanguage().getLanguage());
                cache.computeIfAbsent(messageKey, inner ->
                        new EnumMap<>(ResponseLanguageExceptionType.class)).put(entity.getLanguage(), instance);
            });
        }
    }

    public String addExceptionManager(ExceptionManagerEntity entity) {
        ExceptionManagerEntity save = managerRepository.save(entity);
        if (save == null)
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INTERNAL_SERVER_ERROR);

        return save.getCode();
    }

    public void addExceptionMessage(ExceptionMessageEntity entity) {
        ExceptionMessageEntity save = messageRepository.save(entity);
        if (save == null)
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INTERNAL_SERVER_ERROR);

    }

    @Override
    public Map<ResponseLanguageExceptionType, ExceptionMessageModel> findAllByMessageKey(String messageKey) {
        return cache.get(messageKey);
    }

    @Override
    public void addExceptionInCache(String messageKey, ExceptionMessageEntity exceptionMessage) {
        ExceptionMessageModel instance = new ExceptionMessageModel(
                exceptionMessage.getMessage(),
                exceptionMessage.getCode(),
                exceptionMessage.getLanguage().getLanguage());

        cache.computeIfAbsent(messageKey, inner ->
                new EnumMap<>(ResponseLanguageExceptionType.class)).put(exceptionMessage.getLanguage(), instance);
    }

    @Override
    public void refreshCache() {
        cache.clear();
        cacheData();
    }

}
