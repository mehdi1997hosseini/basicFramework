package com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo;

import com.mehdihosseini.framework.basicframework.controller.BasicDtoController;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.dto.ExternalOrganizationInfoDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("external-organization-system/")
public class ExternalOrganizationInfoController extends BasicDtoController<ExternalOrganizationInfoDto, ExternalOrganizationInfoService> {

    protected ExternalOrganizationInfoController(ExternalOrganizationInfoService service) {
        super(service);
    }

}
