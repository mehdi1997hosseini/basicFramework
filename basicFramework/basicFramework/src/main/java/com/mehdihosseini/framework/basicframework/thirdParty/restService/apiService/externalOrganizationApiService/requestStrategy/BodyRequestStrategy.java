package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.requestStrategy;

import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class BodyRequestStrategy implements BasicExternalServiceRequestStrategy {
    @Override
    public <T, R> ResponseEntity<R> sendRequest(T request, ExternalOrganizationApiServiceEntity config, Class<R> responseType,
                                                RestTemplate restTemplate, ExternalTokenDto token) {
        HttpHeaders headers = buildHeaders(config, token);
        return restTemplate.exchange(
                config.getFullApiUri(),
                config.getHttpMethod(),
                new HttpEntity<>(request, headers),
                responseType
        );
    }

    @Override
    public HttpHeaders buildHeaders(ExternalOrganizationApiServiceEntity config, ExternalTokenDto token) {
        HttpHeaders headers = new HttpHeaders();
        RequestHeaderApiConfigEntity headerConfig = config.getRequestHeader();
        headers.set(headerConfig.getContentTypeParamName(), headerConfig.getContentType().getValue());
        headers.set(headerConfig.getTokenHeaderName().getValue(), headerConfig.getTokenType().getType() + " " + token.getToken());
        headers.setAll(headerConfig.getDefaultHeaders());
        return headers;
    }

}
