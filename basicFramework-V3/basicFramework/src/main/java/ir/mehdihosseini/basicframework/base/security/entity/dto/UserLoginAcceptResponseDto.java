package ir.mehdihosseini.basicframework.base.security.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class UserLoginAcceptResponseDto implements Serializable {

    private String token;
    private String expireTime;

}
