package ir.mehdihosseini.basicframework.controller;

import ir.mehdihosseini.basicframework.entity.BasicEntity;
import ir.mehdihosseini.basicframework.service.entity.BasicEntityService;
import jakarta.persistence.MappedSuperclass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

/**
 * -----------------------------------------------------------------------------
 * <p>English:</p>
 * BasicEntityController is an abstract REST controller that provides basic CRUD
 * endpoints for entities in a generic and reusable manner.
 * It supports save, update, getById, findById, and findAll operations by default.
 * <p>
 * Type Parameters:
 * - E: Entity type extending BasicEntity<ID>
 * - ID: Type of the entity identifier (must be Serializable)
 * - S: A service extending BasicEntityService<E, ID>
 * <p>
 * Notes:
 * - The controller is generic and should be extended by specific entity controllers.
 * - Each method corresponds to a typical RESTful operation.
 * - All routes are pre-defined as constants.
 * <p>
 * -----------------------------------------------------------------------------
 * <p> فارسی:</p>
 * این کنترلر یک کنترلر REST انتزاعی است که عملیات پایه CRUD را برای موجودیت‌ها به‌صورت جنریک و قابل استفاده مجدد فراهم می‌کند.
 * شامل عملیات ذخیره، به‌روزرسانی، دریافت بر اساس ID، جستجو بر اساس ID، و دریافت همه داده‌ها می‌باشد.
 * <p>
 * پارامترهای نوعی:
 * - E: نوع موجودیتی که از BasicEntity<ID> ارث می‌برد
 * - ID: نوع شناسه موجودیت (باید Serializable باشد)
 * - S: سرویسی که از BasicEntityService<E, ID> ارث می‌برد
 * <p>
 * نکات:
 * - این کنترلر جنریک است و باید توسط کنترلرهای خاص موجودیت‌ها گسترش داده شود.
 * - هر متد نماینده یکی از عملیات‌های رایج REST است.
 * - تمامی مسیرهای REST به‌صورت ثابت تعریف شده‌اند.
 * <p>
 * -----------------------------------------------------------------------------
 */
@MappedSuperclass
public abstract class BasicEntityController<E extends BasicEntity<ID>, ID extends Serializable, S extends BasicEntityService<E, ID>> {

    protected S service;

    protected BasicEntityController(S service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody E entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

}
