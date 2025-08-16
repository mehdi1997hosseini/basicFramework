package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService;

import com.mehdihosseini.framework.basicframework.controller.BasicEntityController;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.dto.SaveOrUpdateExternalOrganizationAuthServiceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("external-organization-auth-service-config/")
public class ExternalOrganizationAuthServiceController extends BasicEntityController<ExternalOrganizationAuthServiceEntity, String, ExternalOrganizationAuthService> {
    private final ExternalOrganizationAuthOperationService externalOrganizationAuthOperationService;

    public ExternalOrganizationAuthServiceController(ExternalOrganizationAuthService service, ExternalOrganizationAuthOperationService externalOrganizationAuthOperationService) {
        super(service);
        this.externalOrganizationAuthOperationService = externalOrganizationAuthOperationService;
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody @NotNull SaveOrUpdateExternalOrganizationAuthServiceDto externalOrganization) {
        return new ResponseEntity<>(service.save(externalOrganization), HttpStatus.CREATED);
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody @NotNull SaveOrUpdateExternalOrganizationAuthServiceDto externalOrganization) {
        return new ResponseEntity<>(service.update(externalOrganization), HttpStatus.OK);
    }

    @PostMapping("refresh-manually")
    public ResponseEntity<?> refreshManually(@RequestParam @NotBlank String organizationName) {
        externalOrganizationAuthOperationService.refreshManuallyExternalOrganizationByExtOrgName(organizationName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("shot-down-manually")
    public ResponseEntity<?> shotDownManually(@RequestParam @NotBlank String organizationName) {
        externalOrganizationAuthOperationService.shotDownManuallyExternalOrganizationForGetToken(organizationName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("refresh-manually")
//    public <E extends ExternalOrganizationInfoStructure>ResponseEntity<?> refreshManually(@RequestParam @NotBlank E organizationName) {
//        externalOrganizationAuthOperationService.refreshManuallyExternalOrganizationByExtOrgName(organizationName);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PostMapping("shot-down-manually")
//    public <E extends ExternalOrganizationInfoStructure>ResponseEntity<?> shotDownManually(@RequestParam @NotBlank E organizationName) {
//        externalOrganizationAuthOperationService.shotDownManuallyExternalOrganizationForGetToken(organizationName);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
