package com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExternalOrganizationInfoDto implements Serializable {
    @NotNull
    @NotBlank
    private String externalOrganizationNameEn;
    @NotNull
    @NotBlank
    private String externalOrganizationNameFa;
    @NotNull
    @NotBlank
    private Long externalOrganizationCode;

}
