package ir.mehdihosseini.basicframework.base.controller;

import ir.mehdihosseini.basicframework.base.service.dto.BasicDtoService;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Base controller for handling operations with DTOs.
 *
 * @param <DTO>     The type of DTO
 * @param <SERVICE> The type of the service handling DTOs
 */
public abstract class AbstractDtoController<DTO, SERVICE extends BasicDtoService<DTO>> {

    protected final String SAVE = "save";

    protected final SERVICE service;

    protected AbstractDtoController(SERVICE service) {
        this.service = service;
    }

    @PostMapping(SAVE)
    public ResponseEntity<?> save(@RequestBody DTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    /**
     * Find all DTOs is not Deleted.
     *
     * @return list of all DTOs
     */
    @Description(value = "find all category")
    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

}
