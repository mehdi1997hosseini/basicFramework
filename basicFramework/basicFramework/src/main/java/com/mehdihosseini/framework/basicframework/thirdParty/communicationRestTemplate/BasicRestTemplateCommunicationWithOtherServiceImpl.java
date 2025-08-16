package com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate;

import com.mehdihosseini.framework.basicframework.exceptionHandler.exception.AppRunTimeException;
import com.mehdihosseini.framework.basicframework.thirdParty.cache.tokenCache.TokenCacheService;
import com.mehdihosseini.framework.basicframework.thirdParty.exceptionHandler.ExternalOrganizationExceptionHandler;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.requestStrategy.BasicExternalServiceRequestStrategy;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.TokenHeaderName;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.TokenType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Component
@AllArgsConstructor
class BasicRestTemplateCommunicationWithOtherServiceImpl implements BasicRestTemplateCommunicationWithOtherService {

    private final RestTemplate restTemplate;
    private final TokenCacheService tokenCache;

    @Override
    public <T, R> ResponseEntity<R> sendRequest(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity,
                                                Class<R> responseBody) {

        HttpEntity<Object> objectHttpEntity = generatedHttpEntityForHeader(requestBody,
                externalOrganizationApiServiceEntity, responseBody);
        return restTemplate.exchange(
                externalOrganizationApiServiceEntity.getFullApiUri(),
                externalOrganizationApiServiceEntity.getHttpMethod(),
                objectHttpEntity,
                responseBody
        );
    }

    private <E, R> HttpEntity<Object> generatedHttpEntityForHeader(E objectRequest, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseTypeClass) {
        HttpHeaders httpHeader = new HttpHeaders();
        RequestHeaderApiConfigEntity requestHeader = externalOrganizationApiServiceEntity.getRequestHeader();
        ExternalOrganizationInfoEntity externalOrganizationInfo = externalOrganizationApiServiceEntity.getExternalOrganizationInfo();

        httpHeader.set(requestHeader.getContentTypeParamName(), requestHeader.getContentType().getValue());
        ExternalTokenDto token = tokenCache.getToken(externalOrganizationInfo.getExternalOrganizationNameEn());
        setTokenToHeader(requestHeader, token.getToken(), httpHeader);

        Map<String, String> defaultHeaders = requestHeader.getDefaultHeaders();
        if (!defaultHeaders.isEmpty()) {
            for (Map.Entry<String, String> entry : defaultHeaders.entrySet()) {
                httpHeader.set(entry.getKey(), entry.getValue());
            }
        }

        return new HttpEntity<>(objectRequest, httpHeader);
    }

    @Override
    public <T, R> ResponseEntity<R> sendRequestByStrategyRequestSendType(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> objectResponseType) {
        ExternalTokenDto token = getToken(externalOrganizationApiServiceEntity);
        BasicExternalServiceRequestStrategy instanceStrategyRequest = externalOrganizationApiServiceEntity.getRequestSendType().getInstanceStrategyRequest();
        assert instanceStrategyRequest != null;
        return instanceStrategyRequest.sendRequest(requestBody, externalOrganizationApiServiceEntity, objectResponseType, restTemplate, token);
    }

    private void setTokenToHeader(RequestHeaderApiConfigEntity requestHeader, String token, HttpHeaders httpHeader) {
        String tokenHeaderName = requestHeader.getTokenHeaderName().equals(TokenHeaderName.CUSTOM_NAME_DEFINITION) ?
                requestHeader.getTokenHeaderCustomName() :
                requestHeader.getTokenHeaderName().getValue();

        String tokenType = requestHeader.getTokenType().equals(TokenType.CUSTOM) ?
                (fullTokenRequest(requestHeader.getTokenTypeCustom(), token)) :
                fullTokenRequest(requestHeader.getTokenType().getType(), token);

        httpHeader.set(tokenHeaderName, tokenType);

    }

    private ExternalTokenDto getToken(ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity) {
        ExternalOrganizationInfoEntity externalOrganizationInfo = externalOrganizationApiServiceEntity.getExternalOrganizationInfo();
        ExternalTokenDto token = tokenCache.getToken(externalOrganizationInfo.getExternalOrganizationNameEn());
        if (token == null || token.getIsValidToken().equals(Boolean.FALSE) || token.getToken() == null)
            throw new AppRunTimeException(ExternalOrganizationExceptionHandler.TOKEN_IS_NOT_VALID, HttpStatus.BAD_REQUEST);

        return token;
    }

    private String fullTokenRequest(String tokenType, String token) {
        return (tokenType == null || tokenType.isBlank()) ? token :
                (tokenType.toLowerCase().startsWith("token:") ? tokenType + token : tokenType + " " + token);
    }

}
