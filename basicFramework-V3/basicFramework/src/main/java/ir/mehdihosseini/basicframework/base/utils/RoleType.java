package ir.mehdihosseini.basicframework.base.utils;

import java.util.Arrays;

public enum RoleType {

    ROLE_SUPPER_ADMIN,
    ROLE_MANAGER,
    ROLE_ADMIN,
    ROLE_USER;


    public RoleType of(String value) {
        return Arrays.stream(RoleType.values()).filter(val -> val.name().equalsIgnoreCase(value))
                .findFirst().orElseThrow(() -> new RuntimeException("value is not valid for role type"));
    }

}
