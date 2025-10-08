package ir.mehdihosseini.basicframework.app.category;

import ir.mehdihosseini.basicframework.base.entity.BasicAuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TBL_CATEGORY")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity extends BasicAuditEntity<Long> {

    @Column(name = "CATEGORY_NAME", unique = true)
    private String categoryName;
    @Column(name = "CATEGORY_CODE", unique = true)
    private Integer categoryCode;
    @Column(name = "CATEGORY_DESCRIPTION")
    private String categoryDescription;

}
