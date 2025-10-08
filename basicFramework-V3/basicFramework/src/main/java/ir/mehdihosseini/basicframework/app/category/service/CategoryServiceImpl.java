package ir.mehdihosseini.basicframework.app.category.service;

import ir.mehdihosseini.basicframework.app.category.CategoryEntity;
import ir.mehdihosseini.basicframework.app.category.dto.CategoryDto;
import ir.mehdihosseini.basicframework.app.category.infrastructure.CategoryInfrastructureService;
import ir.mehdihosseini.basicframework.app.category.mapper.CategoryMapper;
import ir.mehdihosseini.basicframework.base.service.AbstractDtoService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl
        extends AbstractDtoService<CategoryEntity, Long, CategoryDto, CategoryMapper, CategoryInfrastructureService>
        implements CategoryService {

    public CategoryServiceImpl(CategoryMapper mapper, CategoryInfrastructureService infrastructureService) {
        super(mapper, infrastructureService);
    }

    @Override
    public CategoryDto findById(Long id) {
        return mapper.toDto(infrastructureService.findById(id));
    }

    @Override
    public CategoryDto findByCategoryCode(Integer code) {
        return mapper.toDto(infrastructureService.findByCategoryCode(code));
    }

}
