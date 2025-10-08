package ir.mehdihosseini.basicframework.base.service.file;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;

import java.util.List;

public interface BasicFileService<ENTITY extends BasicEntity<ID>, ID> {

    ENTITY add(ENTITY entity);

    ENTITY findById(ID id);

    List<ENTITY> findAll();

}
