package com.mehdihosseini.framework.basicframework.thirdParty.restService.enums;

public enum ContentType {
    APPLICATION_ATOM_XML_VALUE("application/atom+xml", "atom-xml"),
    APPLICATION_CBOR_VALUE("application/cbor", "cbor"),
    APPLICATION_FORM_URLENCODED_VALUE("application/x-www-form-urlencoded", "form-urlencoded"),
    APPLICATION_GRAPHQL_VALUE("application/graphql+json", "graphql+json"),
    APPLICATION_GRAPHQL_RESPONSE_VALUE("application/graphql-response+json", "graphql-response+json"),
    APPLICATION_JSON_VALUE("application/json", "json"),
    APPLICATION_JSON_UTF8_VALUE("application/json;charset=UTF-8", "json;UTF-8"),
    APPLICATION_OCTET_STREAM_VALUE("application/octet-stream", "octet-stream"),
    APPLICATION_PDF_VALUE("application/pdf", "pdf"),
    APPLICATION_PROBLEM_JSON_UTF8_VALUE("application/problem+json;charset=UTF-8", "problem+json;UTF-8"),
    APPLICATION_PROBLEM_XML_VALUE("application/problem+xml", "problem+xml"),
    TEXT_XML_VALUE("text/xml", "xml"),
    TEXT_PLAIN_VALUE("text/plain", "plain"),
    TEXT_MARKDOWN_VALUE("text/markdown", "markdown"),
    TEXT_HTML_VALUE("text/html", "html"),
    MULTIPART_RELATED_VALUE("multipart/related", "related"),
    MULTIPART_MIXED_VALUE("multipart/mixed", "mixed"),
    MULTIPART_FORM_DATA_VALUE("multipart/form-data", "form-data"),
    APPLICATION_STREAM_JSON_VALUE("application/stream+json", "stream-json"),
    APPLICATION_NDJSON_VALUE("application/ndjson", "ndjson"),
    APPLICATION_XML_VALUE("application/xml", "xml"),
    ;

    private final String value;
    private final String title;

    ContentType(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public static ContentType fromString(String type) {
        if (type == null)
            throw new IllegalArgumentException("content type is Null ... ");

        for (ContentType contentType : ContentType.values()) {
            if (contentType.name().equalsIgnoreCase(type) || contentType.value.equalsIgnoreCase(type) || contentType.title.equalsIgnoreCase(type)) {
                return contentType;
            }
        }
        throw new IllegalArgumentException("Unknown Content type: " + type);
    }

    public String getValue() {
        return value;
    }
}
