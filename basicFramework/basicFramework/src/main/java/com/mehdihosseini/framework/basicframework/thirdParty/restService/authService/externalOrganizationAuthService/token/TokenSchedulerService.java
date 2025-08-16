package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token;

import com.mehdihosseini.framework.basicframework.thirdParty.cache.tokenCache.TokenCacheService;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.service.TokenService;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.service.TokenServiceImpl;
import com.mehdihosseini.framework.basicframework.utils.DurationAndInstantUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@Component
public class TokenSchedulerService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20); // به تعداد سازمان‌ها یا dynamic
    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    private final TokenCacheService tokenCacheService;
    private final TokenService tokenService;

    public TokenSchedulerService(TokenCacheService tokenCacheService, TokenServiceImpl tokenService) {
        this.tokenCacheService = tokenCacheService;
        this.tokenService = tokenService;
    }

    public void initTokens(Map<String, ExternalOrganizationAuthServiceEntity> externalOrganizationEntityMap) {
        externalOrganizationEntityMap.forEach(this::scheduleRefresh);
    }

    private void scheduleRefresh(String extOrgName, ExternalOrganizationAuthServiceEntity extOrgEntity) {
        try {
            ExternalTokenDto token = tokenService.sendRestRequestForToken(extOrgEntity);
            tokenCacheService.saveOrUpdateToken(extOrgName, token);

            Duration durationFromExpiresIn = DurationAndInstantUtils.toDurationFromInstant(token.getExpiresAt());

            Runnable task = () -> refreshToken(extOrgName,extOrgEntity);
            ScheduledFuture<?> future = scheduler.schedule(task, durationFromExpiresIn.toMillis(), TimeUnit.MILLISECONDS);
            scheduledTasks.put(extOrgName, future);

        } catch (Exception ex) {
            retryLater(extOrgName, extOrgEntity);
        }
    }

    private void refreshToken(String extOrgName,ExternalOrganizationAuthServiceEntity org) {
        try {
            ExternalTokenDto newToken = tokenService.sendRestRequestForToken(org);
            tokenCacheService.saveOrUpdateToken(extOrgName, newToken);
            scheduleRefresh(extOrgName, org);
        } catch (Exception ex) {
            retryLater(extOrgName,org);
        }
    }

    private void retryLater(String extOrgName,ExternalOrganizationAuthServiceEntity org) {
        ExternalTokenDto token = tokenCacheService.getToken(extOrgName);
        if (token != null) {
            if (token.getCountTry() < 3) {
                token.setCountTry(token.getCountTry() + 1);
                scheduler.schedule(() -> scheduleRefresh(extOrgName, org), 1, TimeUnit.MINUTES);
            } else if (token.getCountTry() == 3) {
                token.setCountTry(token.getCountTry() + 1);
                scheduler.schedule(() -> scheduleRefresh(extOrgName, org), 24, TimeUnit.HOURS);
            } else {
                token.setIsValidToken(false);
                token.setCountTry(token.getCountTry() + 1);
                ScheduledFuture<?> remove = scheduledTasks.remove(extOrgName);
                if (remove != null) {
                    remove.cancel(true);
                }
            }
            tokenCacheService.saveOrUpdateToken(extOrgName, token);
        }
    }

    public Boolean resetTokenManually(ExternalOrganizationAuthServiceEntity extOrgEntity) {
        ExternalOrganizationInfoEntity externalOrganizationInfo = extOrgEntity.getExternalOrganizationInfo();
        String extOrgName = externalOrganizationInfo.getExternalOrganizationNameEn();
        try {
            ExternalTokenDto token = tokenCacheService.getToken(extOrgName);
            if (token != null) {
                token.setCountTry(0);
                token.setIsValidToken(true);
                tokenCacheService.saveOrUpdateToken(extOrgName, token);
                scheduleRefresh(extOrgName, extOrgEntity);
                log.info("Token was reset for {} and planning was re-planned.", extOrgName);
                return true;
            } else {
                scheduleRefresh(extOrgName, extOrgEntity);
                log.info("ken is not exist for the organization {} .", extOrgName);
                return true;
            }
        } catch (Exception e) {
            log.error("error when the reset token manually by organization name : {} .", extOrgName);
            return false;
        }
    }

    public Boolean shotDownManually(ExternalOrganizationAuthServiceEntity extOrgAuthServiceEntity) {
        ExternalOrganizationInfoEntity externalOrganizationInfo = extOrgAuthServiceEntity.getExternalOrganizationInfo();
        String extOrgName = externalOrganizationInfo.getExternalOrganizationNameEn();
        try {
            ExternalTokenDto token = tokenCacheService.getToken(extOrgName);
            token.setToken(null);
            token.setExpiresAt(null);
            token.setIsValidToken(false);
            token.setCountTry(0);
            ScheduledFuture<?> remove = scheduledTasks.remove(extOrgName);
            if (remove != null) {
                remove.cancel(true);
            }
            tokenCacheService.saveOrUpdateToken(extOrgName, token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


//    private final TaskScheduler scheduler = new ConcurrentTaskScheduler();
//    public void scheduleTokenRefreshForRest(ExternalOrganizationName extOrgName, ExternalOrganizationEntity extOrgEntity) {
//        ExternalTokenDto tokenInfo = tokenCacheService.getToken(extOrgName);
//        if (tokenInfo != null) {
//            Instant refreshTime = tokenInfo.getExpiresAt();
//
//            scheduler.schedule(() -> {
//                ExternalTokenDto newToken = tokenService.fetchTokenByRest(extOrgEntity);
//                System.out.println("refreshTime: +++++ " + newToken.getToken() + "\t\t" + newToken.getExpiresAt() + "  ++++++======in time===>" + LocalDateTime.now());
//                tokenCacheService.saveOrUpdateToken(extOrgName, newToken);
//                scheduleTokenRefreshForRest(extOrgName, extOrgEntity); // بازنشانی زمانبندی جدید
//            }, Date.from(refreshTime));
//        } else {
//
//            ExternalTokenDto newToken = tokenService.fetchTokenByRest(extOrgEntity);
//            scheduler.schedule(() -> {
//                System.out.println("refreshTime: " + newToken.getToken() + "\t\t" + newToken.getExpiresAt() + "  ======in time===>" + LocalDateTime.now());
//                tokenCacheService.saveOrUpdateToken(extOrgName, newToken);
//                scheduleTokenRefreshForRest(extOrgName, extOrgEntity); // بازنشانی زمانبندی جدید
//            }, Date.from(newToken.getExpiresAt()));
//        }
//    }

}
