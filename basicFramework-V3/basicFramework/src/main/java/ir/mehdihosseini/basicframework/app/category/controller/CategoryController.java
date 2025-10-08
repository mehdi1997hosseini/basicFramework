package ir.mehdihosseini.basicframework.app.category.controller;

import ir.mehdihosseini.basicframework.app.category.service.CategoryService;
import ir.mehdihosseini.basicframework.app.category.dto.CategoryDto;
import ir.mehdihosseini.basicframework.base.controller.AbstractDtoController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category/")
public class CategoryController extends AbstractDtoController<CategoryDto, CategoryService> {

    protected CategoryController(CategoryService service) {
        super(service);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("find-by-category-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable Integer code) {
        return new ResponseEntity<>(service.findByCategoryCode(code), HttpStatus.OK);
    }

}
