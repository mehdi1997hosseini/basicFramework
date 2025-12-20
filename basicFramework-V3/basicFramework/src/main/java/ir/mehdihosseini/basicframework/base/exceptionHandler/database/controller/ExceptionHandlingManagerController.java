package ir.mehdihosseini.basicframework.base.exceptionHandler.database.controller;

import ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity.dto.ExceptionHandlingManagerDto;
import ir.mehdihosseini.basicframework.base.exceptionHandler.database.service.ExceptionHandlingManagerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception-handling-manager/")
@ConditionalOnProperty(prefix = "manager.exception-handling", name = "type", havingValue = "DATABASE")
public class ExceptionHandlingManagerController {

    private final ExceptionHandlingManagerService service;

    public ExceptionHandlingManagerController(ExceptionHandlingManagerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ExceptionHandlingManagerDto dto) {
        return new ResponseEntity<>(service.add(dto), HttpStatus.CREATED);
    }

}
