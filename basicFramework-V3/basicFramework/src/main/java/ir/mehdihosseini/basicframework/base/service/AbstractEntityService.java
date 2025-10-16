package ir.mehdihosseini.basicframework.base.service;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import ir.mehdihosseini.basicframework.base.repository.BasicRepository;
import ir.mehdihosseini.basicframework.base.service.entity.BasicEntityService;
import ir.mehdihosseini.basicframework.base.utils.BeanUtilsCustom;
import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * پیاده‌سازی پایه‌ای سرویس برای Entityها.
 * این کلاس تمام عملیات پایه‌ای CRUD را با استفاده از Repository مدیریت می‌کند.
 * شامل متد saveOrUpdate برای به‌روزرسانی یا ذخیره موجودیت و همچنین saveList برای ذخیره چند موجودیت است.
 * می‌توان متدهای خاص Entityها را در کلاس‌های فرزند اضافه کرد.
 *<p></p>
 * Base implementation of service for Entities.
 * Handles basic CRUD operations using a Repository.
 * Includes saveOrUpdate for updating or saving an entity and saveList for saving multiple entities.
 * Entity-specific methods can be added in child classes.
 *
 * @param <ENTITY> نوع Entity
 * @param <ID> نوع شناسه Entity
 * @param <REPO> نوع Repository مرتبط با Entity
 */
public abstract class AbstractEntityService<ENTITY extends BasicEntity<ID>, ID ,
        REPO extends BasicRepository<ENTITY,ID>>
        implements BasicEntityService<ENTITY, ID> {

    protected final REPO repository;

    protected AbstractEntityService(REPO repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ENTITY saveOrUpdate(ID id, ENTITY entity) {
        ENTITY targetEntity = repository.findById(id).orElse(null);
        if (targetEntity == null)
            return save(entity);

        // map current object to current object database by use (BeanUtils,ModelMapper,mapstruct)
        BeanUtilsCustom.copyDeepProperties(entity, targetEntity, BasicEntity.class);
        return save(targetEntity);
    }

    @Override
    @Transactional
    public ENTITY save(ENTITY entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public List<ENTITY> saveList(List<ENTITY> entities) {
        if (!entities.isEmpty()) {
            return repository.saveAll(entities);
        }
        return List.of();
    }

    @Override
    public ENTITY getById(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ENTITY> findById(ID id) {
        return repository.findAllById(Collections.singletonList(id));
    }

    @Override
    public List<ENTITY> findAll() {
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
