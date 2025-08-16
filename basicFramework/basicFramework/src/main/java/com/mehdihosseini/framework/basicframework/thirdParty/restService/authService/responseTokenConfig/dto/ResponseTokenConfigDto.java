package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig.dto;

import com.mehdihosseini.framework.basicframework.utils.TimeUnitType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseTokenConfigDto implements Serializable {
    @NotNull
    private String tokenFieldName;
    private String expireTimeFieldName;
    @NotNull
    private TimeUnitType timeUnitType;
    private Integer expiresIn;

}
