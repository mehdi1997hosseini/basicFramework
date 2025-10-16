package ir.mehdihosseini.basicframework.base.config.filterChain.globalResponse;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public final class GlobalApisResponse<B> {

    private GlobalApisResponse() {
    }

    private String status;
    private B body;
    private HttpStatus httpStatus;
    private String url;

    public static <B> GlobalApisResponse<B> success(B body, String url) {
        return new GlobalApisResponse<>("success", body, HttpStatus.OK, url);
    }

    public static <B> GlobalApisResponse<B> failure(B body, String url) {
        return new GlobalApisResponse<>("failure", body, HttpStatus.BAD_REQUEST, url);
    }

    public static <B> GlobalApisResponse<B> custom(String status, B body, HttpStatus httpStatus, String url) {
        return new GlobalApisResponse<>(status, body, httpStatus, url);
    }

}
