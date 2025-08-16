package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.requestStrategy;

public enum RequestSendType {
    BODY,          // برای ارسال با body (POST/PUT معمولاً)
    QUERY_PARAM,   // برای ارسال با query string
    PATH_PARAM,    // برای جای‌گذاری در URI
    MULTIPART,     // برای ارسال فایل یا multipart
    HEADER_ONLY;    // برای درخواست بدون body، فقط header

    public BasicExternalServiceRequestStrategy getInstanceStrategyRequest() {
        switch (this) {
            case BODY : return new BodyRequestStrategy();
            case QUERY_PARAM : return new QueryParamRequestStrategy();
            case PATH_PARAM : return new PathParamRequestStrategy();
            case MULTIPART : return new MultipartRequestStrategy();
            case HEADER_ONLY : return new HeaderOnlyRequestStrategy();
            default : return null;
        }
    }
}
