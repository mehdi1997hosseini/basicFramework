package ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import ir.mehdihosseini.basicframework.base.exceptionHandler.ResponseLanguageExceptionType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Entity
@Table(name = "TBL_EXCEPTION_MESSAGE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ConditionalOnProperty(prefix = "manager.exception-handling", name = "type", havingValue = "DATABASE")
public class ExceptionMessageEntity extends BasicEntity<Long> {

    private String code;
    private ResponseLanguageExceptionType language;
    private String message;

}
