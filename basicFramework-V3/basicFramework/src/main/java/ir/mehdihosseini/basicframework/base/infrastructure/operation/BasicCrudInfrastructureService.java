package ir.mehdihosseini.basicframework.base.infrastructure.operation;

import java.util.List;

public interface BasicCrudInfrastructureService<ENTITY, ID> {

    ENTITY save(ENTITY entity);

    ENTITY findById(ID id);

    List<ENTITY> findAll();

    Boolean softDeleted(ENTITY entity);

}
