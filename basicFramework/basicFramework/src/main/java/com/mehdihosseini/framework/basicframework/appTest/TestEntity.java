package com.mehdihosseini.framework.basicframework.appTest;

import com.mehdihosseini.framework.basicframework.entity.BasicEntity;
import com.mehdihosseini.framework.basicframework.utils.NumberUtils;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_TEST")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestEntity extends BasicEntity<String> {
    private String testName;
    private final String testCode = String.valueOf(NumberUtils.generateUniqueNumber());

}
