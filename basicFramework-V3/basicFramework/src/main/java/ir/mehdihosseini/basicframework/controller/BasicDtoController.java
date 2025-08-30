package ir.mehdihosseini.basicframework.controller;

import ir.mehdihosseini.basicframework.service.dto.BasicDtoService;
import jakarta.persistence.MappedSuperclass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * -----------------------------------------------------------------------------
 * <p>English:</p>
 * BasicDtoController is an abstract base controller designed for handling
 * simple DTO-based requests in a RESTful service.
 * It provides a basic implementation for saving a DTO using a generic service.
 * <p>
 * Type Parameters:
 * - D: DTO type
 * - S: A service extending BasicDtoService<D>
 * <p>
 * Notes:
 * - Only includes the "save" operation by default.
 * - Can be extended by other controllers to inherit basic save logic.
 * <p>
 * -----------------------------------------------------------------------------
 * <p>فارسی:</p>
 * این کلاس یک کنترلر انتزاعی پایه برای مدیریت درخواست‌های مبتنی بر DTO در سرویس‌های RESTful است.
 * پیاده‌سازی اولیه‌ای از عملیات ذخیره‌سازی (save) را با استفاده از یک سرویس جنریک فراهم می‌کند.
 * <p>
 * پارامترهای نوعی:
 * - D: نوع DTO
 * - S: سرویسی که از BasicDtoService<D> ارث می‌برد
 * <p>
 * نکات:
 * - تنها عملیات "ذخیره" را به‌صورت پیش‌فرض دارد.
 * - سایر کنترلرها می‌توانند از این کلاس ارث ببرند تا منطق ذخیره‌سازی پایه را داشته باشند.
 * <p>
 * -----------------------------------------------------------------------------
 */
@MappedSuperclass
public abstract class BasicDtoController<D, S extends BasicDtoService<D>> {
    protected S service;

    protected final String SAVE = "save";

    protected BasicDtoController(S service) {
        this.service = service;
    }

    @PostMapping(SAVE)
    public ResponseEntity<?> save(@RequestBody D dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

}
