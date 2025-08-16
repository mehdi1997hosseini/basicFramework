package com.mehdihosseini.framework.basicframework.service;

import com.mehdihosseini.framework.basicframework.entity.BasicEntity;
import com.mehdihosseini.framework.basicframework.repository.BasicRepository;
import org.springframework.core.GenericTypeResolver;

import java.util.List;

/**
 * ===================================================
 * <p>Basic Infrastructure Service Implementation</p>
 * <p> پیاده‌سازی پایه‌ای سرویس زیرساخت برای عملیات مرتبط با Entity</p>
 * ===================================================
 *
 * @param <E>  The entity type (must extend BasicEntity<ID>)
 *             نوع موجودیت که باید از BasicEntity ارث‌بری کند
 * @param <ID> The type of the entity's unique identifier
 *             نوع شناسه یکتای موجودیت
 * @param <R>  The repository used to access the database
 *             ریپازیتوری‌ای که برای دسترسی به دیتابیس استفاده می‌شود
 * @Description :
 * This class provides a generic and reusable implementation of {@link BasicInfrastructureService}
 * for handling basic data operations (CRUD) on entities using a Spring Data repository.
 * <p>
 * این کلاس یک پیاده‌سازی عمومی و قابل استفاده مجدد از {@link BasicInfrastructureService} برای
 * انجام عملیات پایه‌ای دیتایی (CRUD) بر روی موجودیت‌ها با استفاده از Repositoryهای Spring Data ارائه می‌دهد.
 * <p>
 * Generic Parameters:
 * پارامترهای جنریک:
 */
public class BasicInfrastructureServiceImpl<E extends BasicEntity<ID>, ID, R extends BasicRepository<E, ID>> extends BasicEntityManager implements BasicInfrastructureService<E, ID> {
    protected Class<E> entityClass;
    protected R repository;

    public BasicInfrastructureServiceImpl(R repository) {
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    public BasicInfrastructureServiceImpl(Class<E> entityClass, R repository) {
        this(repository);

        this.entityClass = (Class<E>) GenericTypeResolver.resolveTypeArgument(getClass(), BasicInfrastructureServiceImpl.class);
        if (this.entityClass == null)
            throw new IllegalStateException("Cannot determine entity class (E) from generic type.");
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public E findById(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean softDeleted(E entity) {
        try {
            entity.setIsDelete(true);
            save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
