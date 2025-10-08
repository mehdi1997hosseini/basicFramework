package ir.mehdihosseini.basicframework.app.product;

import ir.mehdihosseini.basicframework.app.category.CategoryEntity;
import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TBL_PRODUCT")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoEntity extends BasicEntity<Long> {

    private String productName;
    private Integer productCode;
    private String productDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", updatable = false)
    private CategoryEntity category;

}
