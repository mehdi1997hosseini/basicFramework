package ir.mehdihosseini.basicframework.app.category.dto;

import java.io.Serializable;

public record CategoryDto(String categoryName, Integer categoryCode,
                          String categoryDescription) implements Serializable {

}
