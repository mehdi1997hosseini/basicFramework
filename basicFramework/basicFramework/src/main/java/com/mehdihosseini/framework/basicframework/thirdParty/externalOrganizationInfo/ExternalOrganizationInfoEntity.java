package com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo;

import com.mehdihosseini.framework.basicframework.entity.BasicEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExternalOrganizationInfoEntity extends BasicEntity<String> {

    @Column(name = "EXTERNAL_ORGANIZATION_NAME_EN" , nullable = false , unique = true)
    private String externalOrganizationNameEn;
    @Column(name = "EXTERNAL_ORGANIZATION_NAME_FA", nullable = false , unique = true)
    private String externalOrganizationNameFa;
    @Column(name = "EXTERNAL_ORGANIZATION_CODE", nullable = false)
    private Long externalOrganizationCode;

}
