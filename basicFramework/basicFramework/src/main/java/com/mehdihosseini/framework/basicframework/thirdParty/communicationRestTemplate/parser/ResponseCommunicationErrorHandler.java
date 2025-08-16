package com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate.parser;

import com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate.failed.ExternalErrorResponseIdentifiable;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public interface ResponseCommunicationErrorHandler {
    static final Map<ExternalOrganizationInfoEntity, Map<String, Object>> errorRegistryMap = new ConcurrentHashMap<>();


    /**
     * <p>Register a list of error response objects for a specific organization.</p>
     *
     * <p>ثبت لیستی از شیءهای خطا برای یک سازمان مشخص.</p>
     *
     * @param externalOrganizationInfo name of the external organization
     *                                 نام سازمان خارجی
     * @param fList                    list of objects implementing {@code ExternalUnifiedResponseFailed}
     *                                 لیستی از اشیاء پیاده‌ساز {@code ExternalUnifiedResponseFailed}
     * @param <F>                      type of failure objects
     *                                 نوع عمومی اشیاء خطا
     */
    public static <F extends ExternalErrorResponseIdentifiable> void registerErrorsMap(
            ExternalOrganizationInfoEntity externalOrganizationInfo,
            List<F> fList
    ) {
        Map<String, Object> errorsMap = new HashMap<>();
        for (F f : fList) {
            errorsMap.put(f.getErrorCode(), f);
        }
        errorRegistryMap.put(externalOrganizationInfo, new ConcurrentHashMap<>(errorsMap));
    }

    /**
     * Retrieve an error response object with explicit casting to a specific type.
     * <p>
     * دریافت شیء خطا با تعیین نوع خروجی به‌صورت امن.
     *
     * <p>
     * Useful when you want to access type-specific fields or methods of the failure response.
     * <p>
     * زمانی کاربرد دارد که بخواهید به فیلدها یا متدهای اختصاصی نوع خاصی از خطا دسترسی داشته باشید.
     *
     * <h3>Usage Example - مثال استفاده:</h3>
     * <pre>{@code
     * Optional<ResponseFailed{ExtOrgName}Organization> failure =
     *     ResponseCommunicationErrorHandler.getFailedResponse(
     *         externalOrganizationInfo {enum class with implements ExternalOrganizationInfoStructure},
     *         "error-code",
     *         ResponseFailed{ExtOrgName}Organization.class
     *     );
     * failure.ifPresent(f -> System.out.println("ExtOrgName Msg: " + f.getMessageEn() + f.getMessageFa() ));
     * }</pre>
     *
     * @param externalOrganizationInfo the name of the external organization
     * @param errorCode                the returned error code
     * @param failureClass             the expected failure class
     * @param <F>                      type of expected failure class
     * @return Optional of the expected failure object, if available
     */
    @SuppressWarnings("unchecked")
    public static <F extends ExternalErrorResponseIdentifiable> Optional<F> getFailedResponse(
            ExternalOrganizationInfoEntity externalOrganizationInfo,
            String errorCode,
            Class<F> failureClass
    ) {
        Object obj = errorRegistryMap
                .getOrDefault(externalOrganizationInfo, Map.of())
                .get(errorCode);

        if (failureClass.isInstance(obj)) {
            return Optional.of((F) obj);
        }
        return Optional.empty();
    }

}
