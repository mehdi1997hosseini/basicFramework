package com.mehdihosseini.framework.basicframework.service.entity;

import com.mehdihosseini.framework.basicframework.entity.BasicEntity;

import java.util.List;

public interface BasicEntityService<E extends BasicEntity<ID>, ID> {
    @Deprecated
    E getById(ID id);

    List<E> findById(ID id);

    List<E> findAll();

    E saveOrUpdate(ID id, E entity);

    E save(E entity);

    List<E> saveList(List<E> entities);

    Boolean softDeleteById(ID id);
}
