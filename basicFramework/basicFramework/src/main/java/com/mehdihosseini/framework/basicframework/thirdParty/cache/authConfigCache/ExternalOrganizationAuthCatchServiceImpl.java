package com.mehdihosseini.framework.basicframework.thirdParty.cache.authConfigCache;

import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.structure.ExternalOrganizationInfoStructure;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.TokenSchedulerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ExternalOrganizationAuthCatchServiceImpl implements ExternalOrganizationAuthCatchService {
    private final Map<String, ExternalOrganizationAuthServiceEntity> extOrgCatch = new ConcurrentHashMap<>();

    private final TokenSchedulerService tokenSchedulerService;

    public ExternalOrganizationAuthCatchServiceImpl(TokenSchedulerService tokenSchedulerService) {
        this.tokenSchedulerService = tokenSchedulerService;
    }

    @Override
    public Boolean saveOrUpdate(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity) {
        try {
            if (externalOrganizationAuthServiceEntity == null || externalOrganizationAuthServiceEntity.getId() == null ||
                    externalOrganizationAuthServiceEntity.getExternalOrganizationInfo() == null || externalOrganizationAuthServiceEntity.getExternalOrganizationInfo().getId() == null)
                return false;

            ExternalOrganizationInfoEntity externalOrganizationInfo = externalOrganizationAuthServiceEntity.getExternalOrganizationInfo();
            if (Boolean.TRUE.equals(isExternalOrganizationExist(externalOrganizationInfo.getExternalOrganizationNameEn()))) {
                update(externalOrganizationInfo.getExternalOrganizationNameEn(), externalOrganizationAuthServiceEntity);
            } else {
                extOrgCatch.put(externalOrganizationInfo.getExternalOrganizationNameEn(), externalOrganizationAuthServiceEntity);
                resetManually(externalOrganizationAuthServiceEntity);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean refreshExternalOrganizationByEntity(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity) {
        ExternalOrganizationInfoEntity externalOrganizationInfo = externalOrganizationAuthServiceEntity.getExternalOrganizationInfo();
        if (Boolean.FALSE.equals(isExternalOrganizationExist(externalOrganizationInfo.getExternalOrganizationNameEn())))
            return saveOrUpdate(externalOrganizationAuthServiceEntity);
        else
            update(externalOrganizationInfo.getExternalOrganizationNameEn(), externalOrganizationAuthServiceEntity);

        return resetManually(externalOrganizationAuthServiceEntity);
    }

    private Boolean resetManually(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity) {
        if (Boolean.TRUE.equals(externalOrganizationAuthServiceEntity.getIsDelete()))
            return tokenSchedulerService.shotDownManually(externalOrganizationAuthServiceEntity);

        return tokenSchedulerService.resetTokenManually(externalOrganizationAuthServiceEntity);
    }

    @Override
    public Boolean removeExternalOrganizationFromCatch(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity) {
        ExternalOrganizationInfoEntity externalOrganizationInfo = externalOrganizationAuthServiceEntity.getExternalOrganizationInfo();
        extOrgCatch.remove(externalOrganizationInfo.getExternalOrganizationNameEn());
        if (Boolean.FALSE.equals(isExternalOrganizationExist(externalOrganizationInfo.getExternalOrganizationNameEn())))
            return tokenSchedulerService.shotDownManually(externalOrganizationAuthServiceEntity);

        return false;
    }

    @Override
    public ExternalOrganizationAuthServiceEntity findExternalOrganizationByExtOrgName(String extOrgName) {
        return extOrgCatch.get(extOrgName);
    }

    @Override
    public <E extends ExternalOrganizationInfoStructure> ExternalOrganizationAuthServiceEntity findExternalOrganizationByExtOrgName(E extOrgName) {
        return extOrgCatch.get(extOrgName.getExternalOrganizationNameEn());
    }

    @Override
    public Boolean isExternalOrganizationExist(String extOrgName) {
        return extOrgCatch
                .containsKey(extOrgName);
    }

    @Override
    public <E extends ExternalOrganizationInfoStructure> Boolean isExternalOrganizationExist(E extOrgName) {
        return extOrgCatch.containsKey(extOrgName.getExternalOrganizationNameEn());
    }

    @Override
    public void refreshAllCatch(List<ExternalOrganizationAuthServiceEntity> findAllExtOrg) {
        if (findAllExtOrg == null || findAllExtOrg.isEmpty()) return;

        if (extOrgCatch.isEmpty()) {
            findAllExtOrg.forEach(externalOrganizationEntity -> {
                extOrgCatch.put(externalOrganizationEntity.getExternalOrganizationInfo().getExternalOrganizationNameEn(), externalOrganizationEntity);
            });
        } else {
            findAllExtOrg.forEach(this::saveOrUpdate);
        }
    }

    @Override
    public Map<String, ExternalOrganizationAuthServiceEntity> findAllExternalOrganization() {
        return extOrgCatch;
    }

    private void update(String extOrgName , ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity) {
        extOrgCatch.replace(extOrgName, externalOrganizationAuthServiceEntity);
    }

}
