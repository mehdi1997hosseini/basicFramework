package ir.mehdihosseini.basicframework.base.security.repository;

import ir.mehdihosseini.basicframework.base.repository.BasicRepository;
import ir.mehdihosseini.basicframework.base.security.entity.UserSecurityInfoEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "manager.security" , name = "enable" , havingValue = "true")
public interface UserSecurityRepository extends BasicRepository<UserSecurityInfoEntity, String> {
    UserSecurityInfoEntity findByUsername(String username);

    boolean existsByUsername(String username);
}
