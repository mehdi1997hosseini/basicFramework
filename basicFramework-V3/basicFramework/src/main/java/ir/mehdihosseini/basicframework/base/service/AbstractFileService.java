package ir.mehdihosseini.basicframework.base.service;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import ir.mehdihosseini.basicframework.base.infrastructure.file.AbstractFileInfrastructureService;
import ir.mehdihosseini.basicframework.base.service.file.BasicFileService;

import java.util.List;

public abstract class AbstractFileService<ENTITY extends BasicEntity<ID>, ID, INFRA extends AbstractFileInfrastructureService<ENTITY, ID>>
        implements BasicFileService<ENTITY, ID> {

    protected final INFRA infrastructure;

    public AbstractFileService(INFRA infrastructure) {
        this.infrastructure = infrastructure;
    }

    @Override
    public ENTITY add(ENTITY entity) {
        return infrastructure.save(entity);
    }

    @Override
    public ENTITY findById(ID id) {
        return infrastructure.findById(id);
    }

    @Override
    public List<ENTITY> findAll() {
        return infrastructure.findAll();
    }

}
