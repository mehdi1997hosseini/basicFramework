package ir.mehdihosseini.basicframework.base.utils.security;

import ir.mehdihosseini.basicframework.base.security.entity.UserSecurityInfoEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    public static String currentUsername() {
        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return "SYSTEM";
        }

        return auth.getName();
    }

    public static UserDetails currentUserInfo() {
        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return new UserSecurityInfoEntity();
        }

        return (UserDetails) auth.getPrincipal();
    }

}
