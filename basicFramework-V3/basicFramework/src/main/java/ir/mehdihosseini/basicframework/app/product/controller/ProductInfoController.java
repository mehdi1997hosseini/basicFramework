package ir.mehdihosseini.basicframework.app.product.controller;

import ir.mehdihosseini.basicframework.app.product.dto.ProductInfoDto;
import ir.mehdihosseini.basicframework.app.product.service.ProductInfoService;
import ir.mehdihosseini.basicframework.base.controller.AbstractDtoController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product-info/")
public class ProductInfoController extends AbstractDtoController<ProductInfoDto, ProductInfoService> {

    protected ProductInfoController(ProductInfoService service) {
        super(service);
    }

}
