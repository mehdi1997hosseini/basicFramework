package com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate.failed.ExternalErrorResponseIdentifiable;
import com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate.unified.ExternalUnifiedResponse;
import com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate.unified.ResponseType;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoEntity;

import java.util.Collections;
import java.util.List;

/**
 * Utility class responsible for parsing, handling, and resolving error responses
 * received from external systems during API communication.
 *
 * <p>
 * کلاس ابزار (Utility) برای تحلیل، مدیریت و استخراج خطاهای دریافتی از سامانه‌های خارجی
 * در زمان ارتباط بین سرویس‌ها از طریق API.
 * </p>
 *
 * <p>
 * This class provides a structured mechanism to:
 * <ul>
 *     <li>Register and store external business error codes for each organization</li>
 *     <li>Map error codes to failure response objects that implement {@link ExternalErrorResponseIdentifiable}</li>
 *     <li>Provide type-safe and type-flexible access to mapped error definitions</li>
 * </ul>
 * </p>
 *
 * <p>
 * این کلاس مکانیسمی ساختاریافته برای موارد زیر ارائه می‌دهد:
 * <ul>
 *     <li>ثبت و نگهداری کدهای خطای بیزینسی مربوط به هر سازمان</li>
 *     <li>نگاشت این کدها به آبجکت‌هایی از جنس {@code ExternalUnifiedResponseFailed}</li>
 *     <li>امکان استخراج خطاها به صورت تایپ‌دار یا عمومی برای تحلیل دقیق‌تر</li>
 * </ul>
 * </p>
 *
 * <h2>Example - نمونه استفاده</h2>
 *
 * <pre>{@code
 * // Example usage when you know the specific error type
 * Optional<ResponseFailedBankOrganization> failure =
 *     ExternalCommunicationParser.getFailedResponse(
 *         ExternalOrganizationName.BANK,
 *         "0401_BANK",
 *         ResponseFailedBankOrganization.class
 *     );
 * failure.ifPresent(f -> System.out.println("Bank message: " + f.getBankMessage()));
 *
 * // Example usage when error type is not known or not needed
 * Optional<ExternalUnifiedResponseFailed> generalFailure =
 *     ExternalCommunicationParser.getFailedResponse(
 *         ExternalOrganizationName.BANK,
 *         "0401_BANK"
 *     );
 * generalFailure.ifPresent(f -> System.out.println("Error Code: " + f.getErrorCode()));
 * }</pre>
 *
 * <b>Thread-safe</b> – ایمن برای استفاده در محیط‌های هم‌زمان (Multi-thread) به کمک {@code ConcurrentHashMap}.
 */
public interface ExternalCommunicationParser {

    /**
     * @En : <p>Shared ObjectMapper instance for JSON deserialization.</p>
     * @Fa : <p>نمونه‌ی مشترک برای تبدیل رشته‌های JSON به اشیاء</p>
     */
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @return اطلاعات سازمان خارجی
     * @return external organization entity
     * @Fa : بازیابی اطلاعات مربوط به سازمان خارجی مرتبط با این پارسر.
     * @En : Retrieves metadata about the external organization this parser relates to.
     */
    ExternalOrganizationInfoEntity getExternalOrganization();

    default <A, F extends ExternalErrorResponseIdentifiable>

    /**
     * @param responseBody          بدنه‌ی پاسخ
     * @param responseAcceptObject  کلاس موفقیت
     * @param responseFailureObject لیست کلاس‌های شکست
     * @param responseBody          raw response
     * @param responseAcceptObject  success response class
     * @param responseFailureObject list of failure response classes
     * @return پاسخ یکتاسازی‌شده
     * @return unified external response
     * @Fa : نسخه‌ی پیشرفته‌تر که چندین نوع مختلف پاسخ شکست را بررسی می‌کند.
     * @En : Advanced version to check for multiple possible failure response classes.
     */
    ExternalUnifiedResponse parseResponse(String responseBody, Class<A> responseAcceptObject,
                                          Class<F> responseFailureObject) {
        return parseResponse(responseBody, responseAcceptObject, wrapSingleFailureClass(responseFailureObject));
    }

    /**
     * @param responseBody          بدنه‌ی پاسخ
     * @param responseAcceptObject  کلاس پاسخ موفق
     * @param responseFailureObject کلاس پاسخ شکست
     * @param responseBody          raw response
     * @param responseAcceptObject  class of successful response
     * @param responseFailureObject class of failure response
     * @return ساختار یکتاسازی‌شده‌ی پاسخ
     * @return unified response
     * @Fa : پردازش پاسخ دریافتی و تلاش برای تبدیل آن به نوع موفق یا شکست.
     * اگر تبدیل موفقیت‌آمیز نبود، تلاش می‌کند آن را به یکی از انواع خطا تبدیل کند.
     * @En : Parses the response body into a success or failure object.
     * If parsing as success fails, attempts to parse as failure.
     */
    default <A, F extends ExternalErrorResponseIdentifiable>
    ExternalUnifiedResponse parseResponse(String responseBody, Class<A> responseAcceptObject,
                                          List<Class<F>> responseFailureObject) {
        try {
            A responseSuccess = objectMapper.readValue(responseBody, responseAcceptObject);
            return new ExternalUnifiedResponse(true, getExternalOrganization().getExternalOrganizationNameEn(), responseSuccess, ResponseType.ACCEPTED);
        } catch (Exception e1) {
            return handleFailureResponse(responseBody, responseFailureObject);
        }
    }

    private <F extends ExternalErrorResponseIdentifiable> ExternalUnifiedResponse handleFailureResponse(String responseBody, List<Class<F>> responseFailureObject) {
        for (Class<F> failure : responseFailureObject) {
            try {
                F failureResponseObject = objectMapper.readValue(responseBody, failure);
                return new ExternalUnifiedResponse(false, getExternalOrganization().getExternalOrganizationNameEn(), ResponseCommunicationErrorHandler.getFailedResponse(getExternalOrganization(),
                        failureResponseObject.getErrorCode(), failure), ResponseType.REJECTED);
            } catch (Exception ignored) {
            }
        }
        return new ExternalUnifiedResponse(false, getExternalOrganization().getExternalOrganizationNameEn(), "Unknown error from ExternalOrganization server ....", ResponseType.ERROR);
    }

    private <F extends ExternalErrorResponseIdentifiable> List<Class<F>> wrapSingleFailureClass(Class<F> failureClass) {
        return Collections.singletonList(failureClass);
    }

}
