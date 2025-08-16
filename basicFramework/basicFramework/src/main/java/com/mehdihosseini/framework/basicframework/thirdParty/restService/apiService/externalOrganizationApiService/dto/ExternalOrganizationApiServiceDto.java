package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.dto;

import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.requestStrategy.RequestSendType;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.dto.RequestHeaderApiConfigDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExternalOrganizationApiServiceDto implements Serializable {
    @NotBlank(message = "host-address is null")
    private String hostAddress;
    @NotBlank
    private String contextPath;
    @NotBlank
    private String endPoint;
    @NotBlank
    private String serviceName;
    @NotBlank
    private String httpMethod;
    private Boolean isActive;
    private RequestSendType requestSendType;


    @NotNull
    private RequestHeaderApiConfigDto requestHeader;
}
