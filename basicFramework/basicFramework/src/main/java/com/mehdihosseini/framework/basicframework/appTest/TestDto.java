package com.mehdihosseini.framework.basicframework.appTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDto implements Serializable {
    private String testName;
}
