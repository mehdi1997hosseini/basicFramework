package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.requestStrategy;

import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public interface BasicExternalServiceRequestStrategy {
    <T, R> ResponseEntity<R> sendRequest(T request, ExternalOrganizationApiServiceEntity config, Class<R> responseType
            , RestTemplate restTemplate, ExternalTokenDto token);

    HttpHeaders buildHeaders(ExternalOrganizationApiServiceEntity config, ExternalTokenDto token);

    default HttpHeaders buildHeadersDefault(Map<String, String> headersRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(headersRequest);
        return headers;
    }
}
