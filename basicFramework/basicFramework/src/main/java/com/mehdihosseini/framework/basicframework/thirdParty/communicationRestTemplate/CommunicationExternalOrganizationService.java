package com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate;

import com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate.failed.ExternalErrorResponseIdentifiable;
import com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate.unified.ExternalUnifiedResponse;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;

import java.util.List;

/**
 * @Fa : واسطی برای مدیریت کامل ارسال درخواست به سرویس‌های خارجی،
 * شامل ارسال درخواست، مدیریت پاسخ موفق و مدیریت پاسخ خطا.
 * @En : Interface for sending requests to external services with unified handling for success and failure responses.
 */
public interface CommunicationExternalOrganizationService {
    /**
     * @param requestBody                          داده‌ای که باید ارسال شود
     * @param externalOrganizationApiServiceEntity اطلاعات API خارجی
     * @param responseAccept                       کلاس پاسخ موفق
     * @param responseFailed                       کلاس پاسخ خطا
     * @param requestBody                          the request body
     * @param externalOrganizationApiServiceEntity metadata of the external API
     * @param responseAccept                       success response class
     * @param responseFailed                       failure response class
     * @return ساختار پاسخ یکتاسازی شده
     * @return unified response
     * @Fa: ارسال درخواست به یک سرویس خارجی و تبدیل پاسخ آن به ساختار واحد.
     * @En: Sends a request to an external service and parses the response into a unified structure.
     */
    <T, R, F extends ExternalErrorResponseIdentifiable> ExternalUnifiedResponse sendRequest(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseAccept, Class<F> responseFailed);

    /**
     * @param requestBody                          داده‌ی ارسال‌شده
     * @param externalOrganizationApiServiceEntity اطلاعات API خارجی
     * @param responseAccept                       کلاس پاسخ موفق
     * @param responseFailedList                   لیستی از کلاس‌های پاسخ خطا
     * @param requestBody                          data to be sent
     * @param externalOrganizationApiServiceEntity external API info
     * @param responseAccept                       accepted response type
     * @param responseFailedList                   list of possible failure response classes
     * @return پاسخ به صورت یکتاسازی شده
     * @return unified external response
     * @Fa : ارسال درخواست به سرویس خارجی و بررسی چندین نوع مختلف از پاسخ‌های خطا.
     * @En : Sends a request and supports parsing multiple possible failure response types.
     */
    <T, R, F extends ExternalErrorResponseIdentifiable> ExternalUnifiedResponse sendRequest(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseAccept, List<Class<F>> responseFailedList);

}
