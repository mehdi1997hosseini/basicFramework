package ir.mehdihosseini.basicframework.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Date;
import java.util.Objects;

/**
 * -----------------------------------------------------------------------------
 * <p>English:</p>
 * BasicAuditableEntity extends BasicEntity and adds auditing capabilities
 * including createdBy, createdAt, updatedBy, updatedAt, deletedBy, and deletedAt.
 * It also includes versioning for optimistic locking.
 *<p>
 * Type Parameter:
 * - ID: The type of the primary key
 *<p>
 * Features:
 * - Tracks who created, updated, and deleted the entity
 * - Uses Hibernate annotations like @CreationTimestamp and @UpdateTimestamp
 * - Supports optimistic locking with @Version
 * <p>
 * -----------------------------------------------------------------------------
 *<p> فارسی:</p>
 * این کلاس از BasicEntity ارث‌بری می‌کند و قابلیت‌های مربوط به لاگ‌گیری و ممیزی (Auditing) را به آن اضافه می‌نماید؛
 * از جمله فیلدهای createdBy، createdAt، updatedBy، updatedAt، deletedBy، و deletedAt.
 * همچنین از نسخه‌بندی (versioning) برای پیاده‌سازی optimistic locking پشتیبانی می‌کند.
 *<p>
 * پارامتر نوعی:
 * - ID: نوع کلید اصلی موجودیت
 *<p>
 * قابلیت‌ها:
 * - ثبت اطلاعات مربوط به ایجاد، به‌روزرسانی و حذف داده‌ها
 * - استفاده از انوتیشن‌های Hibernate برای زمان‌بندی
 * - کنترل همزمانی با استفاده از فیلد version
 * <p>
 * -----------------------------------------------------------------------------
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
abstract class BasicAuditableEntity<ID> extends BasicEntity<ID> {

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @LastModifiedBy
    @Column(name = "update_by")
    private String updatedBy;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_by")
    private String deletedBy;
    @Column(name = "deleted_at")
    private Date deletedAt;
    @Version
    private Integer version;

    @PreUpdate
    /**
     * -----------------------------------------------------------------------------
     * <p>English:</p>
     * Hook method executed before any update.
     * If the entity is marked as deleted, it sets the deletedBy and deletedAt
     * fields if they are not already set.
     *<p>
     * Notes:
     * - Currently hardcoded user "mehdi" is used; to be replaced with security context.
     * <p>
     * -----------------------------------------------------------------------------
     * <p>فارسی:</p>
     * این متد پیش از هر عملیات به‌روزرسانی اجرا می‌شود.
     * اگر موجودیت به‌عنوان حذف شده علامت‌گذاری شده باشد، فیلدهای deletedBy و deletedAt را مقداردهی می‌کند (در صورتی که مقدار نداشته باشند).
     *<p>
     * نکته:
     * - مقدار کاربر حذف‌کننده به صورت "mehdi" هاردکد شده و باید در آینده با اطلاعات امنیتی جایگزین شود.
     * <p>
     * -----------------------------------------------------------------------------
     */
    public void beforeAnyUpdate() {
        if (getIsDelete() != null && getIsDelete()) {

            if (deletedBy == null) {
                // TODO:: complete later with security
                deletedBy = "mehdi";
            }

            if (getDeletedAt() == null) {
                deletedAt = new Date();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BasicAuditableEntity<?> that = (BasicAuditableEntity<?>) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
