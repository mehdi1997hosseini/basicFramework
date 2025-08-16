package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig;

import com.mehdihosseini.framework.basicframework.entity.BasicEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.ContentType;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.TokenHeaderName;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.TokenType;
import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "TBL_REQUEST_HEADER_API_CONFIG")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestHeaderApiConfigEntity extends BasicEntity<String> {

    @Enumerated(EnumType.STRING)
    @Column(name = "TOKEN_HEADER_NAME")
    private TokenHeaderName tokenHeaderName;
    @Column(name = "TOKEN_HEADER_CUSTOM_NAME")
    private String tokenHeaderCustomName;
    @Enumerated(EnumType.STRING)
    @Column(name = "CONTENT_TYPE")
    private ContentType contentType;
    @Column(name = "CONTENT_TYPE_PARAM_NAME")
    private String contentTypeParamName;
    @Enumerated(EnumType.STRING)
    @Column(name = "TOKEN_TYPE")
    private TokenType tokenType;
    @Column(name = "TOKEN_TYPE_CUSTOM")
    private String tokenTypeCustom;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> defaultHeaders;


}
