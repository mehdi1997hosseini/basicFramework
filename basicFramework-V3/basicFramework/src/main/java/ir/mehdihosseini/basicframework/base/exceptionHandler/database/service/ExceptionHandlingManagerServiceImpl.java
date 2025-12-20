package ir.mehdihosseini.basicframework.base.exceptionHandler.database.service;

import ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.ExceptionManagerEntity;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.ExceptionMessageEntity;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.dto.ExceptionHandlingManagerDto;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.infrastructure.ExceptionHandlingManagerInfrastructureService;
import ir.mehdihosseini.basicframework.base.exceptionHandler.lang.ResponseLanguageExceptionType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "manager.exception-handling", name = "type", havingValue = "DATABASE")
public class ExceptionHandlingManagerServiceImpl implements ExceptionHandlingManagerService {

    private final ExceptionHandlingManagerInfrastructureService infrastructure;

    public ExceptionHandlingManagerServiceImpl(ExceptionHandlingManagerInfrastructureService infrastructure) {
        this.infrastructure = infrastructure;
    }

    @Override
    public String add(ExceptionHandlingManagerDto dto) {

        ExceptionManagerEntity managerEntity = new ExceptionManagerEntity();
        managerEntity.setStatusCode(dto.getStatusCode());
        managerEntity.setMessageKey(dto.getMessageKey());
        String code = infrastructure.addExceptionManager(managerEntity);
        ExceptionMessageEntity messageEntity = new ExceptionMessageEntity();
        messageEntity.setCode(code);
        messageEntity.setLanguage(ResponseLanguageExceptionType.of(dto.getLanguage()).get());
        messageEntity.setMessage(dto.getMessage());
        return infrastructure.addExceptionMessage(messageEntity);
    }

}
