package ir.mehdihosseini.basicframework.base.service.entity;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;

import java.util.List;

/**
 * اینترفیس پایه‌ای برای سرویس‌های مرتبط با Entityها.
 * شامل عملیات پایه‌ای مانند save, saveOrUpdate, findAll, findById و softDeleteById می‌باشد.
 * متد getById قدیمی است و بهتر است از findById استفاده شود.
 * <p></p>
 * Base interface for services related to Entities.
 * Provides basic operations such as save, saveOrUpdate, findAll, findById, and softDeleteById.
 * The getById method is deprecated; it is recommended to use findById instead.
 *
 * @param <ENTITY> نوع Entity
 * @param <ID>     نوع شناسه Entity
 */
public interface BasicEntityService<ENTITY extends BasicEntity<ID>, ID> {

    @Deprecated
    ENTITY getById(ID id);

    List<ENTITY> findById(ID id);

    List<ENTITY> findAll();

    ENTITY saveOrUpdate(ID id, ENTITY entity);

    ENTITY save(ENTITY entity);

    List<ENTITY> saveList(List<ENTITY> entities);

    Boolean softDeleteById(ID id);

}
