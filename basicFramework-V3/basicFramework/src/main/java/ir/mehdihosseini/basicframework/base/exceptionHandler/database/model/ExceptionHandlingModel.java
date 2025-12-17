package ir.mehdihosseini.basicframework.base.exceptionHandler.database.model;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import ir.mehdihosseini.basicframework.base.exceptionHandler.lang.ResponseLanguageExceptionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Entity
@Table(name = "TBL_EXCEPTION_HANDLING_MODEL")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ConditionalOnProperty(prefix = "manager.exception-handling" ,name = "type" , havingValue = "DATABASE")
public class ExceptionHandlingModel extends BasicEntity<Long> {

    private String title;
    private String message;
    private String code;
    @Enumerated(EnumType.STRING)
    private ResponseLanguageExceptionType languageType;

}
