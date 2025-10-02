package ir.mehdihosseini.basicframework.base.infrastructure;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import ir.mehdihosseini.basicframework.base.infrastructure.operation.BasicCrudInfrastructureService;
import ir.mehdihosseini.basicframework.base.infrastructure.operation.BasicPagingAndSortingInfrastructureService;

/**
 * اینترفیس پایه‌ای برای لایه میانی (Infrastructure) پروژه است.
 * وظیفه این لایه مدیریت دسترسی به داده‌ها با استفاده از Repository یا فایل و ... است.
 * متدهای پایه‌ای مانند save, findById, findAll و softDelete ارائه می‌شود.
 *<p></p>
 * Base interface for the infrastructure layer.
 * Responsible for managing data access using repositories or files and ... .
 * Provides basic methods like save, findById, findAll and softDelete.
 *
 * @param <ENTITY> نوع موجودیت
 * @param <ID> نوع شناسه موجودیت
 */
public interface BasicInfrastructureService<ENTITY extends BasicEntity<ID>, ID> extends
        BasicCrudInfrastructureService<ENTITY, ID>,
        BasicPagingAndSortingInfrastructureService<ENTITY, ID> {

}
