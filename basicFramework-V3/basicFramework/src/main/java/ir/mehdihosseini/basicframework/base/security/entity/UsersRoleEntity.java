package ir.mehdihosseini.basicframework.base.security.entity;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Entity
@Table(name = "TBL_USERS_ROLE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConditionalOnProperty(prefix = "manager.security" , name = "enable" , havingValue = "true")
public class UsersRoleEntity extends BasicEntity<String> {

    private String roleName;
    private String description;

}
