package ir.mehdihosseini.basicframework.app.category.repository;

import ir.mehdihosseini.basicframework.app.category.CategoryEntity;
import ir.mehdihosseini.basicframework.base.repository.BasicRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BasicRepository<CategoryEntity, Long> {

    CategoryEntity findCategoryEntityByCategoryCode(Integer categoryCode);

}
