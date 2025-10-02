package ir.mehdihosseini.basicframework.base.infrastructure.db;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import ir.mehdihosseini.basicframework.base.infrastructure.BasicInfrastructureService;

public interface BasicRepositoryInfrastructureService<ENTITY extends BasicEntity<ID>, ID>
        extends BasicInfrastructureService<ENTITY, ID> {

}
