package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.dao;

import com.mehdihosseini.framework.basicframework.exceptionHandler.exception.AppRunTimeException;
import com.mehdihosseini.framework.basicframework.exceptionHandler.exceptionType.BasicSystemExceptionType;
import com.mehdihosseini.framework.basicframework.service.BasicInfrastructureServiceImpl;
import com.mehdihosseini.framework.basicframework.thirdParty.exceptionHandler.ExternalOrganizationExceptionHandler;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalOrganizationApiServiceInfrastructureServiceImpl extends BasicInfrastructureServiceImpl<ExternalOrganizationApiServiceEntity, String, ExternalOrganizationApiServiceRepository> implements ExternalOrganizationApiServiceInfrastructureService {

    @Autowired
    public ExternalOrganizationApiServiceInfrastructureServiceImpl(ExternalOrganizationApiServiceRepository repository) {
        super(repository);
    }

    public ExternalOrganizationApiServiceInfrastructureServiceImpl(Class<ExternalOrganizationApiServiceEntity> entityClass, ExternalOrganizationApiServiceRepository repository) {
        super(entityClass, repository);
    }


    @Override
    public List<ExternalOrganizationApiServiceEntity> findAllByExternalOrgName(String extOrgName) {
        try {
            return getEntityManager().createQuery("select e from ExternalOrganizationApiServiceEntity e where e.externalOrganizationInfo.externalOrganizationNameEn = :extOrgName", ExternalOrganizationApiServiceEntity.class)
                    .setParameter("extOrgName", extOrgName)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ExternalOrganizationApiServiceEntity findByExtOrgNameAndServiceName(String extOrgName, String serviceName) {
        try {
            List<ExternalOrganizationApiServiceEntity> resultList = getEntityManager().createQuery("select e from ExternalOrganizationApiServiceEntity e where e.externalOrganizationInfo.externalOrganizationNameEn = :extOrgName and e.serviceName = :serviceName", ExternalOrganizationApiServiceEntity.class)
                    .setParameter("extOrgName", extOrgName)
                    .setParameter("serviceName", serviceName)
                    .getResultList();

            if (resultList == null || resultList.isEmpty())
                return null;
            else if (resultList.size() == 1)
                return resultList.get(0);
            else
                throw new AppRunTimeException(ExternalOrganizationExceptionHandler.INTERNAL_ERROR_ON_GET_INFORMATION_EXTERNAL_ORGANIZATION_FROM_DB, new Object[]{extOrgName, serviceName});
        } catch (Exception e) {
            throw new AppRunTimeException(BasicSystemExceptionType.INTERNAL_SERVER_ERROR, "in service get External Organization api service by external organization name : " + extOrgName + " and service name : " + serviceName);
        }
    }

}
