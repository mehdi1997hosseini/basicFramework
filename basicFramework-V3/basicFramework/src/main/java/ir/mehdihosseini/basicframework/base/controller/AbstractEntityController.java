package ir.mehdihosseini.basicframework.base.controller;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import ir.mehdihosseini.basicframework.base.service.entity.BasicEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Base controller for handling CRUD operations for entities.
 *
 * @param <ENTITY> The type of the entity extending BasicEntity
 * @param <ID>     The type of the entity ID
 * @param <SERVICE> The type of the service handling the entity
 */
public abstract class AbstractEntityController<ENTITY extends BasicEntity<ID>, ID , SERVICE extends BasicEntityService<ENTITY, ID>> {

    protected final SERVICE service;

    protected AbstractEntityController(SERVICE service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ENTITY entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    /**
     * Find all entities that are not soft deleted.
     *
     * @return list of all entities
     */
    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    /**
     * Soft delete an entity by ID.
     *
     * @param id the ID of the entity to delete
     * @return {@code true} if deletion succeeded
     */
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam ID id) {
        return new ResponseEntity<>(service.softDeleteById(id), HttpStatus.OK);
    }

}
