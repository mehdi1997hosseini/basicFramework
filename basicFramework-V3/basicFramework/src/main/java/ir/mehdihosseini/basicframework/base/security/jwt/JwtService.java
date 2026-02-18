package ir.mehdihosseini.basicframework.base.security.jwt;

import ir.mehdihosseini.basicframework.base.security.entity.dto.UserLoginAcceptResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    UserLoginAcceptResponseDto generateToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean validationToken(String token, UserDetails userDetails);
}
