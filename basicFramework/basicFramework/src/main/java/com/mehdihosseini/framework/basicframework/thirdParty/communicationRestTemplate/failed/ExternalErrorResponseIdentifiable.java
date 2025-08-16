package com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate.failed;


/**
 * <p>Defines a contract for identifying structured error responses from external systems.</p>
 * <p>This interface should be implemented by classes that represent business error models returned from external services.
 * These models usually contain an error code, which can be used to map to a localized message or internal error handling logic.</p>
 *
 * <p>اینترفیس برای شناسایی پاسخ‌های خطای ساختاریافته از سیستم‌های خارجی است.</p>
 * <p>کلاس‌هایی که مدل خطاهای بیزینسی بازگشتی از سازمان‌های بیرونی را نمایش می‌دهند باید این اینترفیس را پیاده‌سازی کنند.
 * معمولاً این مدل‌ها دارای یک کد خطا هستند که می‌توان آن را به پیام متناسب داخلی یا منطق مدیریت خطای سفارشی نگاشت کرد.</p>
 *
 * <h3>Usage Example / مثال استفاده:</h3>
 *
 * <pre>{@code
 * // Sample error JSON from an external system:
 * // نمونه JSON خطا از یک سیستم خارجی:
 * //
 * // {
 * //   "status": "error",
 * //   "errorCode": "ORG-401",
 * //   "message": "Unauthorized access"
 * // }
 *
 * public class OrgErrorResponse implements ExternalErrorResponseIdentifiable {
 *     private String status;
 *     private String errorCode;
 *     private String message;
 *
 *     @Override
 *     public String getErrorCode() {
 *         return errorCode;
 *     }
 * }
 *
 * // Usage in error handling logic:
 * // نحوه استفاده در منطق مدیریت خطا:
 *
 * ResponseEntity<String> response = restTemplate.postForEntity(...);
 * if (response.getStatusCode().is4xxClientError()) {
 *     OrgErrorResponse error = objectMapper.readValue(response.getBody(), OrgErrorResponse.class);
 *     String code = error.getErrorCode();
 *     String userMessage = errorCodeMessageResolver.getMessageForCode(code); // Maps ORG-401 to user-friendly message
 *     throw new ExternalBusinessException(userMessage);
 * }
 * }</pre>
 *
 * @author mehdi.hosseini
 */
public interface ExternalErrorResponseIdentifiable {

    /**
     * <p>Returns the business error code provided by the external system.</p>
     * <p>برمی‌گرداند کد خطای بیزینسی‌ای که توسط سازمان خارجی ارسال شده است.</p>
     *
     * @return error code from external system / کد خطای سازمان خارجی
     */
    String getErrorCode();
}
