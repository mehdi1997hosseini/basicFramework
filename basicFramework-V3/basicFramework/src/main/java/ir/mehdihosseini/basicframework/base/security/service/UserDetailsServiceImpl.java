package ir.mehdihosseini.basicframework.base.security.service;

import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppRunTimeException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicSecurityExceptionType;
import ir.mehdihosseini.basicframework.base.security.entity.UserSecurityInfoEntity;
import ir.mehdihosseini.basicframework.base.security.repository.UserSecurityRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "manager.security" , name = "enable" , havingValue = "true")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserSecurityRepository repository;

    public UserDetailsServiceImpl(UserSecurityRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurityInfoEntity userSecurityInfo = repository.findByUsername(username);
        if (userSecurityInfo == null) {
            throw new AppRunTimeException(BasicSecurityExceptionType.AUTHENTICATION_FAILED);
        }

        return userSecurityInfo;
    }
}
