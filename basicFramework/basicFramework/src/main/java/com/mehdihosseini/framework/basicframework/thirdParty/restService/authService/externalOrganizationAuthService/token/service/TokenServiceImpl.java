package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.service;

import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import com.mehdihosseini.framework.basicframework.utils.DurationAndInstantUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Map;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    private final RestTemplate restTemplate;

    public TokenServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ExternalTokenDto sendRestRequestForToken(ExternalOrganizationAuthServiceEntity extOrgEntity) {
        HttpEntity<Map<String, String>> httpEntity = extOrgEntity.getAuthType().getHttpEntity(extOrgEntity);
        ResponseEntity<Map> response = restTemplate.exchange(extOrgEntity.getAuthUri(), extOrgEntity.getHttpMethod(), httpEntity, Map.class);
        return parseResponse(response, extOrgEntity);
    }

    public ExternalTokenDto sendSoapRequestForToken(ExternalOrganizationAuthServiceEntity extOrgEntity) {
        return new ExternalTokenDto(null, null, true, 0);
    }

    private ExternalTokenDto parseResponse(ResponseEntity<Map> response, ExternalOrganizationAuthServiceEntity externalOrganizationAuthService) {
        if (!response.getStatusCode().is2xxSuccessful() && response.getBody() == null) {
            return ExternalTokenDto.builder().token(null).expiresAt(null).isValidToken(false).build();
        }

        String token = (String) response.getBody().get(externalOrganizationAuthService.getResponseTokenConfig().getTokenFieldName());

        Instant expiresAt = null;

        if (externalOrganizationAuthService.getResponseTokenConfig().getTimeUnitType().name().contains("CUSTOM_")) {
            expiresAt = customTimeExpires(externalOrganizationAuthService);
        } else {
            expiresAt = getExpiresFromField(response, externalOrganizationAuthService);
        }


        // اگر اطلاعات ناقص بود، هیچ توکنی برنگردونه
        if (token == null || expiresAt == null) {
            log.error("request id : {} - Token or Expiry Time is not valid ", MDC.get("requestId"));

            return ExternalTokenDto.builder()
                    .token(null)
                    .expiresAt(null)
                    .isValidToken(false).build();
        }

        log.info("GET TOKEN FROM SERVER - EXPIRE IN TIME : {} ", expiresAt);
        return new ExternalTokenDto(token, expiresAt, true, 0);
    }

    private Instant customTimeExpires(ExternalOrganizationAuthServiceEntity externalOrganizationAuthService) {
        return DurationAndInstantUtils.calculateExpiry(
                externalOrganizationAuthService.getResponseTokenConfig().getExpiresIn(), externalOrganizationAuthService.getResponseTokenConfig().getTimeUnitType(), 80);
    }

    private Instant getExpiresFromField(ResponseEntity<Map> response, ExternalOrganizationAuthServiceEntity externalOrganization) {
        Object expiresInRes = response.getBody().get(externalOrganization.getResponseTokenConfig().getExpireTimeFieldName());
        Object expiresAtRes = response.getBody().get(externalOrganization.getResponseTokenConfig().getExpireTimeFieldName());


        if (expiresInRes != null) {
            return DurationAndInstantUtils.calculateExpiry(
                    (Integer) expiresInRes, externalOrganization.getResponseTokenConfig().getTimeUnitType(), 50);
        } else if (expiresAtRes != null) {
            String expiresAtStr = (String) expiresAtRes;
            return DurationAndInstantUtils.calculateExpiry(
                    Instant.parse(expiresAtStr), 50);
        } else
            return null;
    }


}
