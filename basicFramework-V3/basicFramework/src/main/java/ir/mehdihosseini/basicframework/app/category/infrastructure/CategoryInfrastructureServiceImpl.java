package ir.mehdihosseini.basicframework.app.category.infrastructure;

import ir.mehdihosseini.basicframework.app.category.CategoryEntity;
import ir.mehdihosseini.basicframework.app.category.repository.CategoryRepository;
import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppRunTimeException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicInternalSystemExceptionType;
import ir.mehdihosseini.basicframework.base.infrastructure.db.AbstractRepositoryInfrastructureService;
import ir.mehdihosseini.basicframework.base.utils.NumberUtils;
import org.springframework.stereotype.Service;

@Service
public class CategoryInfrastructureServiceImpl extends AbstractRepositoryInfrastructureService<CategoryEntity, Long, CategoryRepository>
        implements CategoryInfrastructureService {

    protected CategoryInfrastructureServiceImpl(CategoryRepository repository) {
        super(repository);
    }

    @Override
    public CategoryEntity save(CategoryEntity entity) {
        entity.setCategoryCode(Math.toIntExact(NumberUtils.generateUniqueShortNumber()));
        return super.save(entity);
    }

    @Override
    public CategoryEntity findByCategoryCode(Integer code) {
        CategoryEntity categoryEntity = repository.findCategoryEntityByCategoryCode(code);
        if (categoryEntity == null)
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INTERNAL_SERVER_ERROR);

        return categoryEntity;
    }

}
