package ir.mehdihosseini.basicframework.base.security.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSecurityLoginDto implements Serializable {
    private String username;
    private String password;
}
