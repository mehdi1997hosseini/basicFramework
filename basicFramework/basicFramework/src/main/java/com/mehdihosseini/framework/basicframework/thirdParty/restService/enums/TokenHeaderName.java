package com.mehdihosseini.framework.basicframework.thirdParty.restService.enums;

public enum TokenHeaderName {
    AUTHORIZATION("Authorization"),
    API_KEY("X-API-Key"),
    API_KEY_LOWER("Api-Key"),
    X_ACCESS_TOKEN("X-Access-Token"),
    ACCESS_TOKEN("Access-Token"),
    TOKEN("Token"),
    X_AUTH_TOKEN("X-Auth-Token"),
    CUSTOM_NAME_DEFINITION("custom-name-definition");

    private final String value;

    TokenHeaderName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static TokenHeaderName fromString(String type) {
        if (type == null)
            throw new IllegalArgumentException("content type is Null ... ");

        for (TokenHeaderName tokenHeaderName : values()) {
            if (tokenHeaderName.name().equalsIgnoreCase(type) || tokenHeaderName.value.equalsIgnoreCase(type)) {
                return tokenHeaderName;
            }
        }
        throw new IllegalArgumentException("Unknown Content type: " + type);
    }

}
