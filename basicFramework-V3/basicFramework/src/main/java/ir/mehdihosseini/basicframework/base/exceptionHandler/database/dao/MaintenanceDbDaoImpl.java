package ir.mehdihosseini.basicframework.base.exceptionHandler.database.dao;

import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionHandlingModelResponse;
import ir.mehdihosseini.basicframework.base.exceptionHandler.dao.MaintenanceMessageExceptionDao;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.model.ExceptionHandlingModel;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.repository.ExceptionHandlingModelRepository;
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
public class MaintenanceDbDaoImpl implements MaintenanceMessageExceptionDao {

    private final ExceptionHandlingModelRepository repository;

    private final Map<String, Map<ResponseLanguageExceptionType, ExceptionHandlingModelResponse>> cache =
            new ConcurrentHashMap<>();


    public MaintenanceDbDaoImpl(ExceptionHandlingModelRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void initial() {
        cacheData();
    }

    public void cacheData() {
        List<ExceptionHandlingModel> exceptionHandlingModel = repository.findAll();

        for (ExceptionHandlingModel model : exceptionHandlingModel) {
            String title = model.getTitle();
            ResponseLanguageExceptionType languageType = model.getLanguageType();

            ExceptionHandlingModelResponse instance = new ExceptionHandlingModelResponse(model.getMessage(), model.getCode());

//            if (!cache.containsKey(title))
//                cache.put(title, new HashMap<>());
//
//            cache.get(title).put(languageType, instance);

            // or other implement
            cache.computeIfAbsent(title, inner ->
                    new EnumMap<>(ResponseLanguageExceptionType.class)).put(languageType, instance);

        }
    }

    @Override
    public Map<ResponseLanguageExceptionType, ExceptionHandlingModelResponse> findAllResponseExceptionByKey(String key) {
        return cache.get(key);
    }

    @Override
    public ExceptionHandlingModelResponse findResponseExceptionByKeyAndLanguage(String key, ResponseLanguageExceptionType language) {
        return cache.getOrDefault(key, null).getOrDefault(language, null);
    }

}
