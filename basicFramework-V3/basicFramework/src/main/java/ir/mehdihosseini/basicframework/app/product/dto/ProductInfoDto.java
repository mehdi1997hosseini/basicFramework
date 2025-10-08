package ir.mehdihosseini.basicframework.app.product.dto;

import ir.mehdihosseini.basicframework.app.category.dto.CategoryDto;

public record ProductInfoDto(String productName,
                             Integer productCode,
                             String productDescription , CategoryDto category) {

}
