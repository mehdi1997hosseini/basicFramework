package ir.mehdihosseini.basicframework.app.product.infrastructure;

import ir.mehdihosseini.basicframework.app.product.ProductInfoEntity;
import ir.mehdihosseini.basicframework.app.product.repository.ProductInfoRepository;
import ir.mehdihosseini.basicframework.base.infrastructure.db.AbstractRepositoryInfrastructureService;
import org.springframework.stereotype.Service;

@Service
public class ProductInfoInfrastructureServiceImpl extends AbstractRepositoryInfrastructureService<ProductInfoEntity,Long, ProductInfoRepository>
        implements ProductInfoInfrastructureService {

    protected ProductInfoInfrastructureServiceImpl(ProductInfoRepository repository) {
        super(repository);
    }

}
