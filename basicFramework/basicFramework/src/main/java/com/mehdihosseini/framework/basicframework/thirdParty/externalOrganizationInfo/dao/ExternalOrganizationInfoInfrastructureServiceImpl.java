package com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.dao;

import com.mehdihosseini.framework.basicframework.exceptionHandler.exception.AppRunTimeException;
import com.mehdihosseini.framework.basicframework.service.BasicInfrastructureServiceImpl;
import com.mehdihosseini.framework.basicframework.thirdParty.exceptionHandler.ExternalOrganizationExceptionHandler;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoEntity;
import org.springframework.stereotype.Service;

@Service
public class ExternalOrganizationInfoInfrastructureServiceImpl extends BasicInfrastructureServiceImpl<ExternalOrganizationInfoEntity, String, ExternalOrganizationInfoRepository> implements ExternalOrganizationInfoInfrastructureService {

    public ExternalOrganizationInfoInfrastructureServiceImpl(ExternalOrganizationInfoRepository repository) {
        super(repository);
    }

    @Override
    public ExternalOrganizationInfoEntity findAllByExternalOrganizationName(String extOrgName) {
        try {
            return getEntityManager().createQuery("select e from ExternalOrganizationInfoEntity e where e.externalOrganizationNameEn = :extOrgName", ExternalOrganizationInfoEntity.class)
                    .setParameter("extOrgName", extOrgName)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new AppRunTimeException(ExternalOrganizationExceptionHandler.EXTERNAL_ORGANIZATION_NAME_IS_NOT_EXIST, (Object) extOrgName);
        }
    }
}
