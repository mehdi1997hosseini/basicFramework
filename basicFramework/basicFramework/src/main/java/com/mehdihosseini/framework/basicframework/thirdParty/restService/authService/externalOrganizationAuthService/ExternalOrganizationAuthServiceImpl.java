package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService;

import com.mehdihosseini.framework.basicframework.exceptionHandler.exception.AppRunTimeException;
import com.mehdihosseini.framework.basicframework.exceptionHandler.exceptionType.BasicSqlExceptionType;
import com.mehdihosseini.framework.basicframework.exceptionHandler.exceptionType.BasicSystemExceptionType;
import com.mehdihosseini.framework.basicframework.service.BasicEntityServiceImpl;
import com.mehdihosseini.framework.basicframework.thirdParty.cache.authConfigCache.ExternalOrganizationAuthCatchService;
import com.mehdihosseini.framework.basicframework.thirdParty.exceptionHandler.ExternalOrganizationExceptionHandler;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoService;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.dto.ExternalOrganizationInfoDto;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.structure.ExternalOrganizationInfoStructure;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.dto.ExternalOrganizationAuthServiceDto;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.dto.SaveOrUpdateExternalOrganizationAuthServiceDto;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.mapper.ExternalOrganizationAuthMapper;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestAuthConfig.RequestAuthConfigService;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.requestTemplateAuthConfig.RequestTemplateAuthConfigService;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig.ResponseTokenConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalOrganizationAuthServiceImpl extends BasicEntityServiceImpl<ExternalOrganizationAuthServiceEntity, String, ExternalOrganizationAuthServiceRepository> implements ExternalOrganizationAuthService, ExternalOrganizationAuthOperationService {
    private final ExternalOrganizationAuthMapper mapper;
    private final ExternalOrganizationInfoService externalOrganizationInfoService;
    private final ExternalOrganizationAuthCatchService externalOrganizationAuthCatchService;
    private final RequestTemplateAuthConfigService requestTemplateAuthConfigService;
    private final RequestAuthConfigService requestAuthConfigService;
    private final ResponseTokenConfigService responseTokenConfigService;

    public ExternalOrganizationAuthServiceImpl(ExternalOrganizationAuthServiceRepository repository,
                                               ExternalOrganizationAuthMapper mapper,
                                               ExternalOrganizationInfoService externalOrganizationInfoService,
                                               ExternalOrganizationAuthCatchService externalOrganizationAuthCatchService,
                                               RequestTemplateAuthConfigService requestTemplateAuthConfigService,
                                               RequestAuthConfigService requestAuthConfigService,
                                               ResponseTokenConfigService responseTokenConfigService) {
        super(ExternalOrganizationAuthServiceEntity.class, repository);
        this.mapper = mapper;
        this.externalOrganizationInfoService = externalOrganizationInfoService;
        this.externalOrganizationAuthCatchService = externalOrganizationAuthCatchService;
        this.requestTemplateAuthConfigService = requestTemplateAuthConfigService;
        this.requestAuthConfigService = requestAuthConfigService;
        this.responseTokenConfigService = responseTokenConfigService;
    }


    @Override
    @Transactional
    public ExternalOrganizationAuthServiceDto save(SaveOrUpdateExternalOrganizationAuthServiceDto externalOrganization) {
        ExternalOrganizationInfoEntity externalOrganizationInfoEntity = externalOrganizationInfoService
                .findByDto(externalOrganization.getExternalOrganizationInfo());
        ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity = mapper
                .toEntity(externalOrganization.getExternalOrganizationAuthService());
        if (externalOrganizationInfoEntity == null) {
            ExternalOrganizationInfoEntity externalOrganizationInfo = externalOrganizationInfoService.create(externalOrganization.getExternalOrganizationInfo());
            externalOrganizationAuthServiceEntity.setExternalOrganizationInfo(externalOrganizationInfo);

            return saveAndFlush(externalOrganizationAuthServiceEntity);
        }

        try {
            List<ExternalOrganizationAuthServiceEntity> existByExternalOrganizationInfo = getEntityManager()
                    .createQuery("select e from ExternalOrganizationAuthServiceEntity e where e.externalOrganizationInfo.id = :extOrgInfId and e.isDelete = false ", ExternalOrganizationAuthServiceEntity.class)
                    .setParameter("extOrgInfId", externalOrganizationInfoEntity.getId()).getResultList();

            ExternalOrganizationAuthServiceDto externalOrganizationAuthService = externalOrganization.getExternalOrganizationAuthService();
            if ((existByExternalOrganizationInfo == null || existByExternalOrganizationInfo.isEmpty()) &&
                    externalOrganizationInfoEntity.getIsDelete().equals(Boolean.FALSE)) {
                externalOrganizationAuthServiceEntity.setExternalOrganizationInfo(externalOrganizationInfoEntity);
                return saveAndFlush(externalOrganizationAuthServiceEntity);
            }

            throw new AppRunTimeException(BasicSqlExceptionType.SQL_DUPLICATE_DATA);

        } catch (Exception ex) {
            throw new AppRunTimeException(BasicSystemExceptionType.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @Override
    public ExternalOrganizationAuthServiceDto update(SaveOrUpdateExternalOrganizationAuthServiceDto externalOrganization) {
        ExternalOrganizationInfoDto externalOrganizationInfo = externalOrganization.getExternalOrganizationInfo();
        ExternalOrganizationInfoEntity externalOrganizationInfoEntity = externalOrganizationInfoService.findByDto(externalOrganizationInfo);
        if (externalOrganizationInfoEntity == null)
            throw new AppRunTimeException(ExternalOrganizationExceptionHandler.EXTERNAL_ORGANIZATION_INFORMATION_IS_NOT_EXIST, externalOrganizationInfo.getExternalOrganizationNameEn());

        ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity = mapper.toEntity(externalOrganization.getExternalOrganizationAuthService());
        try {
            List<ExternalOrganizationAuthServiceEntity> existByExternalOrganizationInfo = getEntityManager()
                    .createQuery("select e from ExternalOrganizationAuthServiceEntity e where e.externalOrganizationInfo.id = :extOrgInfId and e.isDelete = false ", ExternalOrganizationAuthServiceEntity.class)
                    .setParameter("extOrgInfId", externalOrganizationInfoEntity.getId()).getResultList();

            if (existByExternalOrganizationInfo != null && !existByExternalOrganizationInfo.isEmpty()) {
                ExternalOrganizationAuthServiceEntity activeExternalOrganizationAuthServiceEntity = existByExternalOrganizationInfo.get(0);
                activeExternalOrganizationAuthServiceEntity.setIsDelete(true);
                saveAndFlush(activeExternalOrganizationAuthServiceEntity);
            }

            externalOrganizationAuthServiceEntity.setExternalOrganizationInfo(externalOrganizationInfoEntity);

            return saveAndFlush(externalOrganizationAuthServiceEntity);

        } catch (Exception ex) {
            throw new AppRunTimeException(BasicSystemExceptionType.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    private ExternalOrganizationAuthServiceDto saveAndFlush(ExternalOrganizationAuthServiceEntity externalOrganizationAuthService) {
        externalOrganizationAuthService.setRequestTemplateAuthConfig(requestTemplateAuthConfigService.save(externalOrganizationAuthService.getRequestTemplateAuthConfig()));
        externalOrganizationAuthService.setRequestAuthConfig(requestAuthConfigService.save(externalOrganizationAuthService.getRequestAuthConfig()));
        externalOrganizationAuthService.setResponseTokenConfig(responseTokenConfigService.save(externalOrganizationAuthService.getResponseTokenConfig()));
        ExternalOrganizationAuthServiceEntity save = repository.save(externalOrganizationAuthService);
        if (save.getId() == null)
            throw new AppRunTimeException(ExternalOrganizationExceptionHandler.INTERNAL_SERVER_ERROR_WHEN_SAVE_DATA);

        externalOrganizationAuthCatchService.saveOrUpdate(save);
        return mapper.toDto(save);
    }

    @Override
    public List<ExternalOrganizationAuthServiceDto> findAllToDto() {
        List<ExternalOrganizationAuthServiceEntity> allExtOrg = findAll().stream()
                .filter(entity -> !entity.getIsDelete()).collect(Collectors.toList());

        return allExtOrg.isEmpty() ? null : mapper.toDto(allExtOrg);
    }

    @Override
    public <E extends ExternalOrganizationInfoStructure> ExternalOrganizationAuthServiceEntity findExternalOrganizationByOrgName(E extOrgName) {
        return findAllByExternalOrganizationName(extOrgName.getExternalOrganizationNameEn());
    }

    private ExternalOrganizationAuthServiceEntity findAllByExternalOrganizationName(String extOrgName) {
        try {
            List<ExternalOrganizationAuthServiceEntity> externalOrganizationAuthServiceList = getEntityManager().createQuery("select e from ExternalOrganizationAuthServiceEntity e where e.externalOrganizationInfo.externalOrganizationNameEn = :extOrgName and e.isDelete = false ", ExternalOrganizationAuthServiceEntity.class)
                    .setParameter("extOrgName", extOrgName)
                    .getResultList();

            if (externalOrganizationAuthServiceList == null || externalOrganizationAuthServiceList.isEmpty())
                throw new AppRunTimeException(ExternalOrganizationExceptionHandler.EXTERNAL_ORGANIZATION_NAME_IS_NOT_EXIST, (Object) extOrgName);

            else if (externalOrganizationAuthServiceList.size() == 1)
                return externalOrganizationAuthServiceList.get(0);

            else
                throw new AppRunTimeException(ExternalOrganizationExceptionHandler.INTERNAL_ERROR_ON_GET_INFORMATION_EXTERNAL_ORGANIZATION_FROM_DB, (Object) extOrgName);

        } catch (Exception ex) {
            throw new AppRunTimeException(BasicSystemExceptionType.INTERNAL_SERVER_ERROR, "in service get information External Organization by external organization name : " + extOrgName);
        }
    }

    @Override
    public <E extends ExternalOrganizationInfoStructure> void refreshManuallyExternalOrganizationByExtOrgName(E extOrgName) {
        ExternalOrganizationAuthServiceEntity externalOrganizationAuthService = findExternalOrganizationByOrgName(extOrgName);
        refreshManually(extOrgName.getExternalOrganizationNameEn(), externalOrganizationAuthService);
    }

    @Override
    public <E extends ExternalOrganizationInfoStructure> void shotDownManuallyExternalOrganizationForGetToken(E extOrgName) {
        ExternalOrganizationAuthServiceEntity externalOrganizationAuthService = findExternalOrganizationByOrgName(extOrgName);
        shotDownManually(extOrgName.getExternalOrganizationNameEn(), externalOrganizationAuthService);
    }

    @Override
    public void refreshManuallyExternalOrganizationByExtOrgName(String extOrgName) {
        ExternalOrganizationAuthServiceEntity externalOrganizationAuthService = findAllByExternalOrganizationName(extOrgName);
        refreshManually(extOrgName, externalOrganizationAuthService);
    }

    @Override
    public void shotDownManuallyExternalOrganizationForGetToken(String extOrgName) {
        ExternalOrganizationAuthServiceEntity externalOrganizationAuthService = findAllByExternalOrganizationName(extOrgName);
        shotDownManually(extOrgName, externalOrganizationAuthService);
    }

    private void refreshManually(String extOrgName,
                                 ExternalOrganizationAuthServiceEntity externalOrganizationAuthService) {
        if (externalOrganizationAuthService == null || externalOrganizationAuthService.getIsDelete().equals(Boolean.TRUE))
            throw new AppRunTimeException(ExternalOrganizationExceptionHandler.EXTERNAL_ORGANIZATION_NAME_IS_NOT_EXIST, (Object) extOrgName);

        Boolean isFinishedOperation = externalOrganizationAuthCatchService.refreshExternalOrganizationByEntity(externalOrganizationAuthService);
        if (isFinishedOperation.equals(Boolean.FALSE))
            throw new AppRunTimeException(ExternalOrganizationExceptionHandler.FAILED_PROCESS_RESTART_MANUALLY_EXTERNAL_ORGANIZATION);
    }


    private void shotDownManually(String extOrgName,
                                  ExternalOrganizationAuthServiceEntity externalOrganizationAuthService) {
        if (externalOrganizationAuthService == null)
            throw new AppRunTimeException(ExternalOrganizationExceptionHandler.EXTERNAL_ORGANIZATION_NAME_IS_NOT_EXIST, (Object) extOrgName);

        Boolean isFinishedOperation = externalOrganizationAuthCatchService.removeExternalOrganizationFromCatch(externalOrganizationAuthService);
        if (Boolean.FALSE.equals(isFinishedOperation))
            throw new AppRunTimeException(ExternalOrganizationExceptionHandler.FAILED_PROCESS_SHOT_DOWN_MANUALLY_EXTERNAL_ORGANIZATION);
    }


}
