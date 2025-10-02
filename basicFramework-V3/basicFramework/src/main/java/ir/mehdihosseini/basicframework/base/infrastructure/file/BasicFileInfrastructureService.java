package ir.mehdihosseini.basicframework.base.infrastructure.file;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import ir.mehdihosseini.basicframework.base.infrastructure.BasicInfrastructureService;

/**
 * اینترفیس پایه‌ای برای سرویس‌های Infrastructure که از فایل استفاده می‌کنند.
 * متدهای پایه‌ای همانند BasicInfrastructureService ارائه می‌شوند.
 * اینترفیس برای پیاده‌سازی خدمات ذخیره‌سازی بر اساس فایل طراحی شده است.
 *<p></p>
 * Base interface for file-based infrastructure services.
 * Provides basic methods similar to BasicInfrastructureService.
 * Designed for implementing services that store data in files.
 *
 * @param <ENTITY> نوع Entity
 * @param <ID> نوع شناسه Entity
 */
public interface BasicFileInfrastructureService<ENTITY extends BasicEntity<ID>, ID>
        extends BasicInfrastructureService<ENTITY, ID> {}
