package ir.mehdihosseini.basicframework.app.product.service;

import ir.mehdihosseini.basicframework.app.category.CategoryEntity;
import ir.mehdihosseini.basicframework.app.category.infrastructure.CategoryInfrastructureService;
import ir.mehdihosseini.basicframework.app.product.ProductInfoEntity;
import ir.mehdihosseini.basicframework.app.product.dto.ProductInfoDto;
import ir.mehdihosseini.basicframework.app.product.infrastructure.ProductInfoInfrastructureService;
import ir.mehdihosseini.basicframework.app.product.mapper.ProductInfoMapper;
import ir.mehdihosseini.basicframework.base.service.AbstractDtoService;
import ir.mehdihosseini.basicframework.base.utils.NumberUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductInfoServiceImpl extends AbstractDtoService<ProductInfoEntity, Long, ProductInfoDto
        , ProductInfoMapper, ProductInfoInfrastructureService> implements ProductInfoService {

    private final CategoryInfrastructureService categoryInfrastructureService;

    public ProductInfoServiceImpl(ProductInfoMapper mapper,
                                  ProductInfoInfrastructureService infrastructureService,
                                  CategoryInfrastructureService categoryInfrastructureService) {
        super(mapper, infrastructureService);
        this.categoryInfrastructureService = categoryInfrastructureService;
    }

    @Override
    public ProductInfoDto save(ProductInfoDto dto) {
        if (dto.category() == null || dto.category().categoryCode() == null)
            throw new IllegalArgumentException("Category code is invalid");

        ProductInfoEntity productInfo = mapper.toEntity(dto);
        CategoryEntity categoryEntity = categoryInfrastructureService.findByCategoryCode(dto.category().categoryCode());
        productInfo.setCategory(categoryEntity);
        productInfo.setProductCode(NumberUtils.generateUniqueShortNumber());
        return mapper.toDto(infrastructureService.save(productInfo));
    }

}
