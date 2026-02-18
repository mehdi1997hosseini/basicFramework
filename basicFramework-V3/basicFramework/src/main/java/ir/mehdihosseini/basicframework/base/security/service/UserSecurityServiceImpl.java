package ir.mehdihosseini.basicframework.base.security.service;

import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppRunTimeException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicSecurityExceptionType;
import ir.mehdihosseini.basicframework.base.security.entity.UserSecurityInfoEntity;
import ir.mehdihosseini.basicframework.base.security.entity.dto.UserLoginAcceptResponseDto;
import ir.mehdihosseini.basicframework.base.security.entity.dto.UserSecurityLoginDto;
import ir.mehdihosseini.basicframework.base.security.entity.dto.UserSecurityRegistryDto;
import ir.mehdihosseini.basicframework.base.security.jwt.JwtService;
import ir.mehdihosseini.basicframework.base.security.jwt.JwtServiceImpl;
import ir.mehdihosseini.basicframework.base.security.repository.UserSecurityRepository;
import ir.mehdihosseini.basicframework.base.utils.RoleType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final UserSecurityRepository repository;
    private final JwtService jwtService;

    public UserSecurityServiceImpl(UserSecurityRepository userSecurityRepository
            , AuthenticationManager authenticationManager
            , PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repository = userSecurityRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserSecurityRegistryDto registry(UserSecurityRegistryDto registry) {
        if (repository.existsByUsername(registry.getUsername()))
            throw new AppRunTimeException(BasicSecurityExceptionType.REGISTRY_USER_DUPLICATED);


        UserSecurityInfoEntity userSecurityInfo = new UserSecurityInfoEntity();
        userSecurityInfo.setUsername(registry.getUsername());
        userSecurityInfo.setPassword(passwordEncoder.encode(registry.getPassword()));
        Set<RoleType> roles = registry.getRoles();
        userSecurityInfo.setRoles(roles.isEmpty() ? Set.of(RoleType.ROLE_USER) : roles);
        repository.save(userSecurityInfo);

        return registry;
    }

    @Override
    public UserLoginAcceptResponseDto login(UserSecurityLoginDto login) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        if (!authenticate.isAuthenticated())
            throw new AppRunTimeException(BasicSecurityExceptionType.USERNAME_OR_PASSWORD_IS_NOT_VALID);

        return jwtService.generateToken((UserDetails) authenticate.getPrincipal());
    }
}
