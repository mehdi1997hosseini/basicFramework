package ir.mehdihosseini.basicframework.base.exceptionHandler.database.repository;

import ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.ExceptionMessageEntity;
import ir.mehdihosseini.basicframework.base.repository.BasicRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(prefix = "manager.exception-handling" ,name = "type" , havingValue = "DATABASE")
public interface ExceptionMessageRepository extends BasicRepository<ExceptionMessageEntity, Long> {
    List<ExceptionMessageEntity> findAllByCode(String code);
}
