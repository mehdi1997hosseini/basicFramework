package com.mehdihosseini.framework.basicframework.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass

/**
 * -----------------------------------------------------------------------------
 * <p>English:</p>
 * BasicEntity is an abstract base class for all entities with a generic ID type.
 * It includes automatic ID generation for common ID types (String, Long, Integer)
 * using UUIDs during persistence (via @PrePersist).
 *<p>
 * Type Parameter:
 * - ID: The type of the primary key (e.g., String, Long, Integer)
 *<p>
 * Fields:
 * - id: Primary key of the entity
 * - isDelete: Soft delete flag to indicate logical deletion
 *<p>
 * Notes:
 * - Automatically generates ID if null during persist time.
 * - Throws exception if the ID type is unsupported.
 * <p>
 * -----------------------------------------------------------------------------
 *<p> فارسی:</p>
 * این کلاس پایه انتزاعی برای تمامی موجودیت‌ها با نوع شناسه (ID) جنریک است.
 * شامل تولید خودکار شناسه برای نوع‌های رایج (String، Long، Integer) با استفاده از UUID در زمان ثبت (Persist) است.
 *<p>
 * پارامتر نوعی:
 * - ID: نوع کلید اصلی (مثلاً String، Long یا Integer)
 *<p>
 * فیلدها:
 * - id: شناسه اصلی موجودیت
 * - isDelete: فلگ حذف منطقی برای پیاده‌سازی Soft Delete
 *<p>
 * نکات:
 * - اگر مقدار ID خالی باشد، به‌صورت خودکار مقداردهی می‌شود.
 * - در صورت عدم پشتیبانی از نوع ID، خطا پرتاب خواهد شد.
 * <p>
 * -----------------------------------------------------------------------------
 */
public abstract class BasicEntity<ID> implements Serializable {
    @Id
    private ID id;
    private Boolean isDelete = false;

    @PrePersist
    private void generateIdIfNeeded() {
        if (id == null) {
            Class<?> idType = getIdType();
            if (idType.equals(String.class)) {
                id = (ID) UUID.randomUUID().toString();
            } else if (idType.equals(Long.class)) {
                id = (ID) Long.valueOf(UUID.randomUUID().getMostSignificantBits() ^ UUID.randomUUID().getLeastSignificantBits());
            } else if (idType.equals(Integer.class)) {
                id = (ID) Integer.valueOf((int) UUID.randomUUID().getMostSignificantBits() & 0xFFFFFFFF);
            } else
                throw new IllegalArgumentException("type of primary key is not valid ... checking the primary key...");
        }
    }

    private Class<?> getIdType() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            if (typeArguments.length > 0) {
                return (Class<?>) typeArguments[0];
            }
        }
        return String.class;
    }

}


