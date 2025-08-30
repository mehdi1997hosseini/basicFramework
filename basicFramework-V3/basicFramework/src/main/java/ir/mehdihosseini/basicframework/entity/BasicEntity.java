package ir.mehdihosseini.basicframework.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.io.Serializable;

/**
 * -----------------------------------------------------------------------------
 * <p>English:</p>
 * BasicEntity is an abstract base class for all entities with a generic ID type.
 * It includes automatic ID generation for common ID types (String, Long, Integer)
 * using UUIDs during persistence (via @PrePersist).
 * <p>
 * Type Parameter:
 * - ID: The type of the primary key (e.g., String, Long, Integer)
 * <p>
 * Fields:
 * - id: Primary key of the entity
 * - isDelete: Soft delete flag to indicate logical deletion
 * <p>
 * Notes:
 * - Automatically generates ID if null during persist time.
 * - Throws exception if the ID type is unsupported.
 * <p>
 * -----------------------------------------------------------------------------
 * <p> فارسی:</p>
 * این کلاس پایه انتزاعی برای تمامی موجودیت‌ها با نوع شناسه (ID) جنریک است.
 * شامل تولید خودکار شناسه برای نوع‌های رایج (String، Long، Integer) با استفاده از UUID در زمان ثبت (Persist) است.
 * <p>
 * پارامتر نوعی:
 * - ID: نوع کلید اصلی (مثلاً String، Long یا Integer)
 * <p>
 * فیلدها:
 * - id: شناسه اصلی موجودیت
 * - isDelete: فلگ حذف منطقی برای پیاده‌سازی Soft Delete
 * <p>
 * نکات:
 * - اگر مقدار ID خالی باشد، به‌صورت خودکار مقداردهی می‌شود.
 * - در صورت عدم پشتیبانی از نوع ID، خطا پرتاب خواهد شد.
 * <p>
 * -----------------------------------------------------------------------------
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
public abstract class BasicEntity<ID> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private ID id;
    private Boolean isDelete = false;

}


