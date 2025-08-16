package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig;

import com.mehdihosseini.framework.basicframework.entity.BasicEntity;
import com.mehdihosseini.framework.basicframework.utils.TimeUnitType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_RESPONSE_TOKEN_CONFIG")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseTokenConfigEntity extends BasicEntity<String> {
    @Column(name = "TOKEN_FIELD_NAME")
    private String tokenFieldName;
    @Column(name = "EXPIRE_TIME_FIELD_NAME")
    private String expireTimeFieldName;
    @Enumerated(EnumType.STRING)
    @Column(name = "TIME_UNIT_TYPE", nullable = false)
    private TimeUnitType timeUnitType;
    @Column(name = "EXPIRES_IN")
    private Integer expiresIn;

}
