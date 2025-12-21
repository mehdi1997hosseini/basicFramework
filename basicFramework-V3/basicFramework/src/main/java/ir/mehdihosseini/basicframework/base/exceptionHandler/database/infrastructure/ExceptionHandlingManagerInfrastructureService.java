package ir.mehdihosseini.basicframework.base.exceptionHandler.database.infrastructure;

import ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.ExceptionManagerEntity;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.ExceptionMessageEntity;

public interface ExceptionHandlingManagerInfrastructureService {

    String addExceptionManager(ExceptionManagerEntity entity);
    void addExceptionMessage(ExceptionMessageEntity entity);

    void addExceptionInCache(String messageKey, ExceptionMessageEntity messageEntity);
    void refreshCache();

}


