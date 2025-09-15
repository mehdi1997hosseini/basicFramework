package ir.mehdihosseini.basicframework.service;

import ir.mehdihosseini.basicframework.entity.BasicEntity;
import ir.mehdihosseini.basicframework.repository.BasicRepository;
import ir.mehdihosseini.basicframework.service.entity.BasicEntityService;
import ir.mehdihosseini.basicframework.utils.BeanUtilsCustom;
import jakarta.persistence.MappedSuperclass;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@MappedSuperclass
public class BasicEntityServiceImpl<E extends BasicEntity<ID>, ID, R extends BasicRepository<E, ID>> extends BasicEntityManager implements BasicEntityService<E, ID> {
    protected final R repository;
    private E entity;

    protected BasicEntityServiceImpl(R repository) {
        this.repository = repository;
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
        return repository.softDeleteById(id) > 0;
    }

//    @Override
//    public Boolean softDeleteById(ID id) {
//        Class<?> entityClass = entity.getEntityClass();
//        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//        CriteriaUpdate<?> update = cb.createCriteriaUpdate(entityClass);
//        update.set("isDelete", Boolean.TRUE);
//        update.where(cb.equal((Expression<?>) entity.getParentClass().getId(), id));
//        return getEntityManager().createQuery(update).executeUpdate() > 0;
//    }

}
