package com.mehdihosseini.framework.basicframework.thirdParty.restService.enums;

public enum TokenType {

    BEARER("Bearer", "Authorization header with Bearer token"),
    JWT("JWT", "JSON Web Token, used in stateless authentication"),
    OAUTH("OAuth", "OAuth 2.0 token, used for secure authorization"),
    API_KEY("API_KEY", "API Key used for identifying clients"),
    BASIC("Basic", "Basic Authentication token"),
    SAML("SAML", "Security Assertion Markup Language token"),
    CUSTOM("Custom", "Custom token defined by the service provider");

    private final String type;
    private final String description;

    // Constructor to set the type and description for each token type
    TokenType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    // Getter for the type
    public String getType() {
        return type;
    }

    // Getter for the description of the token
    public String getDescription() {
        return description;
    }

    // Method to get token type from string (if needed for dynamic configuration)
    public static TokenType fromString(String type) {
        if (type == null)
            throw new IllegalArgumentException("Token type is Null : ");

        for (TokenType tokenType : TokenType.values()) {
            if (tokenType.getType().equalsIgnoreCase(type)) {
                return tokenType;
            }
        }
        throw new IllegalArgumentException("Unknown token type: " + type);
    }

    // Method to check if the token type requires a client ID and secret
    public boolean requiresClientCredentials() {
        return this == OAUTH || this == API_KEY || this == BASIC;
    }
}
