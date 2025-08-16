package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService;

import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.structure.ExternalOrganizationInfoStructure;

public interface ExternalOrganizationAuthOperationService {
    <E extends ExternalOrganizationInfoStructure>void refreshManuallyExternalOrganizationByExtOrgName(E externalOrganizationInfo);

    <E extends ExternalOrganizationInfoStructure>void shotDownManuallyExternalOrganizationForGetToken(E externalOrganizationInfo);

    void refreshManuallyExternalOrganizationByExtOrgName(String externalOrganizationName);
    void shotDownManuallyExternalOrganizationForGetToken(String externalOrganizationName);

}
