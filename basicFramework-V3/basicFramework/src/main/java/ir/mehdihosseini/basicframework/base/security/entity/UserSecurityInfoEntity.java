package ir.mehdihosseini.basicframework.base.security.entity;

import ir.mehdihosseini.basicframework.base.entity.BasicAuditEntity;
import ir.mehdihosseini.basicframework.base.utils.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TBL_USER_SECURITY_INFO")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class UserSecurityInfoEntity extends BasicAuditEntity<String> implements UserDetails {

    @Getter
    @Column(unique = true, nullable = false)
    @NotEmpty
    private String username;
    @Getter
    @NotEmpty
    private String password;
    @Getter
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<RoleType> roles;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(roleType -> new SimpleGrantedAuthority(roleType.name()))
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return !super.getIsDelete();
    }
}
