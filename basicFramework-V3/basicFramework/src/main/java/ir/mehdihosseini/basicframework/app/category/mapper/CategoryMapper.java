package ir.mehdihosseini.basicframework.app.category.mapper;

import ir.mehdihosseini.basicframework.app.category.CategoryEntity;
import ir.mehdihosseini.basicframework.app.category.dto.CategoryDto;
import ir.mehdihosseini.basicframework.base.mapper.BasicMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BasicMapper<CategoryEntity, CategoryDto> {

}
