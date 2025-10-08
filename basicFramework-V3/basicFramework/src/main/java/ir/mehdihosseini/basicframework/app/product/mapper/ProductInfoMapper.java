package ir.mehdihosseini.basicframework.app.product.mapper;

import ir.mehdihosseini.basicframework.app.category.mapper.CategoryMapper;
import ir.mehdihosseini.basicframework.app.product.ProductInfoEntity;
import ir.mehdihosseini.basicframework.app.product.dto.ProductInfoDto;
import ir.mehdihosseini.basicframework.base.mapper.BasicMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductInfoMapper extends BasicMapper<ProductInfoEntity, ProductInfoDto> {

}
