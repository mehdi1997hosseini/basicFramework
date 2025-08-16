package com.mehdihosseini.framework.basicframework.utils;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum FileType {
    JPG("jpg", "image/jpeg"),
    JPEG("jpeg", "image/jpeg"),
    PNG("png", "image/png"),
    GIF("gif", "image/gif"),
    BMP("bmp", "image/bmp"),
    SVG("svg", "image/svg+xml"),

    TEXT("txt", "text/plain"),
    CSV("csv", "text/csv"),
    HTML("html", "text/html"),
    XML("xml", "application/xml"),

    PDF("pdf", "application/pdf"),
    DOC("doc", "application/msword"),
    DOCX("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    XLS("xls", "application/vnd.ms-excel"),
    XLSX("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    PPT("ppt", "application/vnd.ms-powerpoint"),
    PPTX("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),

    ZIP("zip", "application/zip"),
    RAR("rar", "application/x-rar-compressed"),
    GZ("gz", "application/gzip"),
    TAR("tar", "application/x-tar"),

    MP3("mp3", "audio/mpeg"),
    WAV("wav", "audio/wav"),
    OGG("ogg", "audio/ogg"),

    MP4("mp4", "video/mp4"),
    AVI("avi", "video/x-msvideo"),
    MOV("mov", "video/quicktime"),
    WMV("wmv", "video/x-ms-wmv");
    private final String value;
    private final String mimeType;

    FileType(String value, String mimeType) {
        this.value = value;
        this.mimeType = mimeType;
    }

    // ==== Static lookup map ====
    private static final Map<String, FileType> LOOKUP = new HashMap<>();

    static {
        for (FileType type : FileType.values()) {
            LOOKUP.put(type.value.toLowerCase(), type);
        }
    }

    // ==== Lookup method ====
    public static FileType fromExtension(String extension) {
        if (extension == null) return null;
        return LOOKUP.get(extension.toLowerCase());
    }

}
