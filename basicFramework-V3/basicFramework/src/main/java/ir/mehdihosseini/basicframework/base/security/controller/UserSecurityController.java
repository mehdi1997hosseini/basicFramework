package ir.mehdihosseini.basicframework.base.security.controller;

import ir.mehdihosseini.basicframework.base.security.entity.dto.UserSecurityLoginDto;
import ir.mehdihosseini.basicframework.base.security.entity.dto.UserSecurityRegistryDto;
import ir.mehdihosseini.basicframework.base.security.service.UserSecurityService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security/")
@ConditionalOnProperty(prefix = "manager.security" , name = "enable" , havingValue = "true")
public class UserSecurityController {

    private final UserSecurityService service;

    public UserSecurityController(UserSecurityService service) {
        this.service = service;
    }

    @PostMapping("registry")
    public ResponseEntity<?> registry(@RequestBody UserSecurityRegistryDto registry) {
        return new ResponseEntity<>(service.registry(registry), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserSecurityLoginDto login) {
        return new ResponseEntity<>(service.login(login), HttpStatus.CREATED);
    }

}
