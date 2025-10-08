package ir.mehdihosseini.basicframework.app.category.infrastructure;

import ir.mehdihosseini.basicframework.app.category.CategoryEntity;
import ir.mehdihosseini.basicframework.base.infrastructure.BasicInfrastructureService;

public interface CategoryInfrastructureService extends BasicInfrastructureService<CategoryEntity, Long> {

    CategoryEntity findByCategoryCode(Integer code);

}
