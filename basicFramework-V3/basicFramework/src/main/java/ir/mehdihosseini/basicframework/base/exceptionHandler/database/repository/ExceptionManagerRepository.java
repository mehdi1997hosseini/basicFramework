package ir.mehdihosseini.basicframework.base.exceptionHandler.database.repository;

import ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.ExceptionManagerEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "manager.exception-handling", name = "type", havingValue = "DATABASE")
public interface ExceptionManagerRepository extends JpaRepository<ExceptionManagerEntity, Long> {

}
