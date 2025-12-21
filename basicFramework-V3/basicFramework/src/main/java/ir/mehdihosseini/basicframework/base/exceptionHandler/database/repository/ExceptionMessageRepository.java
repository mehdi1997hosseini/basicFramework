package ir.mehdihosseini.basicframework.base.exceptionHandler.database.repository;

import ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.ExceptionMessageEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(prefix = "manager.exception-handling", name = "type", havingValue = "DATABASE")
public interface ExceptionMessageRepository extends JpaRepository<ExceptionMessageEntity, Long> {
    List<ExceptionMessageEntity> findAllByCode(String code);
}
