package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.dto;

import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.dto.ExternalOrganizationInfoDto;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaveOrUpdateExternalOrganizationAuthServiceDto implements Serializable {
    private ExternalOrganizationInfoDto externalOrganizationInfo;
    private ExternalOrganizationAuthServiceDto externalOrganizationAuthService;

}
