package com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.dao;

import com.mehdihosseini.framework.basicframework.repository.BasicRepository;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalOrganizationInfoRepository extends BasicRepository<ExternalOrganizationInfoEntity,String> {
}
