package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService;

import com.mehdihosseini.framework.basicframework.entity.BasicEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.requestStrategy.RequestSendType;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import lombok.*;
import org.springframework.http.HttpMethod;

import javax.persistence.*;

@Entity
@Table(name = "TBL_EXTERNAL_ORGANIZATION_API_SERVICE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExternalOrganizationApiServiceEntity extends BasicEntity<String> {

    @Column(name = "SERVICE_NAME")
    private String serviceName;
    @Column(name = "HOST_ADDRESS")
    private String hostAddress;
    @Column(name = "CONTEXT_PATH")
    private String contextPath;
    @Column(name = "END_POINT")
    private String endPoint;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive = true;
    @Enumerated(EnumType.STRING)
    @Column(name = "HTTP_METHOD")
    private HttpMethod httpMethod;
    @Enumerated(EnumType.STRING)
    @Column(name = "REQUEST_SEND_TYPE")
    private RequestSendType requestSendType;

    @Transient
    private transient String fullApiUri;

    @OneToOne
    @JoinColumn(name = "REQUEST_HEADER_API_CONFIG_ID")
    private RequestHeaderApiConfigEntity requestHeader;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EXTERNAL_ORGANIZATION_INFO_ID")
    private ExternalOrganizationInfoEntity externalOrganizationInfo;

    public String getFullApiUri() {
        StringBuilder uri = new StringBuilder();

        if (!hostAddress.endsWith("/"))
            hostAddress += "/";

        uri.append(hostAddress);

        if (contextPath.endsWith("/")) contextPath = contextPath.substring(0, contextPath.length() - 1);

        uri.append(contextPath.startsWith("/") ? contextPath.substring(1) : contextPath);
        uri.append(!endPoint.startsWith("/") ? "/" + endPoint : endPoint);

        return uri.toString();
    }
}


