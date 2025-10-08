package ir.mehdihosseini.basicframework.app.category.service;

import ir.mehdihosseini.basicframework.app.category.dto.CategoryDto;
import ir.mehdihosseini.basicframework.base.service.dto.BasicDtoService;

public interface CategoryService extends BasicDtoService<CategoryDto> {

    CategoryDto findById(Long id);
    CategoryDto findByCategoryCode(Integer code);
}
