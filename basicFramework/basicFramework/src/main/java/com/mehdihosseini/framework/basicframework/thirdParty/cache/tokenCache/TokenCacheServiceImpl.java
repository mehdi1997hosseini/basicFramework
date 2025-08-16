package com.mehdihosseini.framework.basicframework.thirdParty.cache.tokenCache;

import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.structure.ExternalOrganizationInfoStructure;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenCacheServiceImpl implements TokenCacheService {

    private final Map<String, ExternalTokenDto> tokenMap = new ConcurrentHashMap<>();

    public void saveOrUpdateToken(String extOrgName, ExternalTokenDto tokenInfo) {
        // update new token for organization name
        if (tokenMap.containsKey(extOrgName)) {
            ExternalTokenDto tokenDto = tokenMap.get(extOrgName);
            if (tokenInfo.getCountTry() != null && tokenInfo.getCountTry() != 0)
                tokenDto.setCountTry(tokenInfo.getCountTry());

            if (Boolean.TRUE.equals(tokenDto.getIsValidToken() && Boolean.FALSE.equals(tokenInfo.getIsValidToken())) && tokenInfo.getCountTry() > 3) {
                tokenDto.setIsValidToken(false);
                tokenDto.setToken(tokenInfo.getToken());
                tokenDto.setExpiresAt(tokenInfo.getExpiresAt());
            } else if (Boolean.FALSE.equals(tokenDto.getIsValidToken()) && Boolean.TRUE.equals(tokenInfo.getIsValidToken())) {
                tokenDto.setIsValidToken(true);
                tokenDto.setToken(tokenInfo.getToken());
                tokenDto.setExpiresAt(tokenInfo.getExpiresAt());
                tokenDto.setCountTry(tokenInfo.getCountTry());
            } else if (Boolean.TRUE.equals(tokenInfo.getIsValidToken() && tokenInfo.getToken() != null) && tokenInfo.getExpiresAt() != null) {
                tokenDto.setIsValidToken(true);
                tokenDto.setToken(tokenInfo.getToken());
                tokenDto.setExpiresAt(tokenInfo.getExpiresAt());
                tokenDto.setCountTry(tokenInfo.getCountTry());
            }

            tokenMap.replace(extOrgName, tokenDto);
        }
        // add new token for organization name
        else {
            ExternalTokenDto newTokenInfo = new ExternalTokenDto(tokenInfo.getToken(), tokenInfo.getExpiresAt(), tokenInfo.getIsValidToken(), tokenInfo.getCountTry() == null
                    ? 0 : tokenInfo.getCountTry());
            tokenMap.put(extOrgName, newTokenInfo);
        }
    }

    @Override
    public <E extends ExternalOrganizationInfoStructure> void saveOrUpdateToken(E extOrgName, ExternalTokenDto tokenInfo) {
        saveOrUpdateToken(extOrgName.getExternalOrganizationNameEn(),tokenInfo);
    }

    public ExternalTokenDto getToken(String extOrgName) {
        if (tokenMap.containsKey(extOrgName)) {
            return tokenMap.get(extOrgName);
        }
        return null;
    }

    @Override
    public <E extends ExternalOrganizationInfoStructure> ExternalTokenDto getToken(E extOrgName) {
        return getToken(extOrgName.getExternalOrganizationNameEn());
    }

    public void clearDataTokens() {
        tokenMap.clear();
    }

    @Override
    public Map<String, ExternalTokenDto> getAll() {
        return tokenMap;
    }


}
