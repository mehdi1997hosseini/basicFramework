package com.mehdihosseini.framework.basicframework.service;

import com.mehdihosseini.framework.basicframework.entity.BasicEntity;
import com.mehdihosseini.framework.basicframework.repository.BasicRepository;
import com.mehdihosseini.framework.basicframework.service.entity.BasicEntityService;
import com.mehdihosseini.framework.basicframework.utils.BeanUtilsCustom;
import org.springframework.core.GenericTypeResolver;

import javax.persistence.MappedSuperclass;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

@MappedSuperclass
public class BasicEntityServiceImpl<E extends BasicEntity<ID>, ID, R extends BasicRepository<E, ID>> extends BasicEntityManager implements BasicEntityService<E, ID> {
    protected Class<E> entityClass;
    protected final R repository;

    private BasicEntityServiceImpl(R repository) {
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    public BasicEntityServiceImpl(Class<E> entityClass, R repository) {
        this(repository);
        this.entityClass = entityClass;
    }

    @Override
    public E saveOrUpdate(ID id, E entity) {
        E targetEntity = repository.findById(id).orElse(null);
        if (targetEntity == null)
            return save(entity);

        // map current object to current object database by use (BeanUtils,ModelMapper,mapstruct)
        BeanUtilsCustom.copyDeepProperties(entity, targetEntity, BasicEntity.class);
        return save(targetEntity);
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public List<E> saveList(List<E> entities) {
        if (!entities.isEmpty()) {
            return repository.saveAll(entities);
        }
        return null;
    }

    @Override
    public E getById(ID id) {
        return repository.getById(id);
    }

    @Override
    public List<E> findById(ID id) {
        return repository.findAllById(Collections.singletonList(id));
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean softDeleteById(ID id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<E> update = cb.createCriteriaUpdate(entityClass);
        Root<E> root = update.from(entityClass);
        update.set("isDelete", Boolean.TRUE);
        update.where(cb.equal(root.get("id"), id));
        return getEntityManager().createQuery(update).executeUpdate() > 0;
    }

}
