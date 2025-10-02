package ir.mehdihosseini.basicframework.base.infrastructure.db;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import ir.mehdihosseini.basicframework.base.repository.BasicRepository;

import java.util.List;

/**
 * پیاده‌سازی پایه‌ای Infrastructure با استفاده از JPA.
 * تمام عملیات پایه مانند save, findById, findAll و softDelete را مدیریت می‌کند.
 * متدهای خاص موجودیت‌ها می‌توانند در کلاس‌های فرزند اضافه شوند.
 * <p></p>
 * Base JPA infrastructure implementation.
 * Handles basic operations like save, findById, findAll and softDelete.
 * Entity-specific methods can be added in child classes.
 *
 * @param <ENTITY> نوع موجودیت
 * @param <ID>     نوع شناسه
 * @param <REPO>   نوع Repository مورد استفاده
 */
public abstract class AbstractRepositoryInfrastructureService<ENTITY extends BasicEntity<ID>, ID
        , REPO extends BasicRepository<ENTITY, ID>>
        extends BasicEntityManager
        implements BasicRepositoryInfrastructureService<ENTITY, ID> {

    protected final REPO repository;

    protected AbstractRepositoryInfrastructureService(REPO repository) {
        this.repository = repository;
    }

    @Override
    public ENTITY save(ENTITY entity) {
        return repository.save(entity);
    }

    @Override
    public ENTITY findById(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ENTITY> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean softDeleted(ENTITY entity) {
        return repository.softDeleteById(entity.getId()) > 0;
    }

}
