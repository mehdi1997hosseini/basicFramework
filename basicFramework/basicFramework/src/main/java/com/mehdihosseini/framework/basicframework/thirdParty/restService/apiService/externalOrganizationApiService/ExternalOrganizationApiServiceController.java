package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService;

import com.mehdihosseini.framework.basicframework.controller.BasicDtoController;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.dto.ExternalOrganizationApiServiceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("external-organization-api-service-config/")
public class ExternalOrganizationApiServiceController extends BasicDtoController<ExternalOrganizationApiServiceDto, ExternalOrganizationApiService> {

    public ExternalOrganizationApiServiceController(ExternalOrganizationApiService service) {
        super(service);
    }

    @PostMapping("add-new-service")
    public ResponseEntity<?> addNewService(@RequestBody ExternalOrganizationApiServiceDto dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PostMapping("refresh-manually-cache")
    public ResponseEntity<?> refreshManuallyAllCache() {
        service.refreshManuallyAllCache();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
