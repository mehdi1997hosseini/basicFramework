package ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Entity
@Table(name = "TBL_EXCEPTION_MANAGER")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ConditionalOnProperty(prefix = "manager.exception-handling", name = "type", havingValue = "DATABASE")
public class ExceptionManagerEntity extends BasicEntity<Long> {

    private String messageKey;
    private String statusCode;
    @Column(name = "CODE", unique = true, updatable = false)
    @GenericGenerator(
            name = "eventCodeGen",
            strategy = "ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.DateTimeSequenceGenerator"
    )
    @GeneratedValue(generator = "eventCodeGen")
    private String code;

}
