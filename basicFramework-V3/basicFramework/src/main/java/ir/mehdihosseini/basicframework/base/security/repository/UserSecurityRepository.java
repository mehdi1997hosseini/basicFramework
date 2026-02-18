package ir.mehdihosseini.basicframework.base.security.repository;

import ir.mehdihosseini.basicframework.base.repository.BasicRepository;
import ir.mehdihosseini.basicframework.base.security.entity.UserSecurityInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSecurityRepository extends BasicRepository<UserSecurityInfoEntity, String> {
    UserSecurityInfoEntity findByUsername(String username);

    boolean existsByUsername(String username);
}
