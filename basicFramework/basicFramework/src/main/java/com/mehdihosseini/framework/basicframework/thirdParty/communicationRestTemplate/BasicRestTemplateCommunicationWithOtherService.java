package com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate;

import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import org.springframework.http.ResponseEntity;

/**
 * @Fa : واسطی برای ارسال درخواست HTTP به سرویس‌های خارجی از طریق RestTemplate :
 * وظیفه‌ی اینترفیس تنها ارسال درخواست است و مسئولیتی در قبال مدیریت پاسخ ندارد.
 * <p>
 * @En : Interface for sending HTTP requests to external services via RestTemplate.
 * This interface only handles request dispatching and delegates response handling elsewhere.
 */
interface BasicRestTemplateCommunicationWithOtherService {
    /**
     * @param requestBody                          محتوای درخواستی که باید ارسال شود
     * @param externalOrganizationApiServiceEntity اطلاعات API مقصد خارجی
     * @param responseBody                         کلاس نوع داده‌ی مورد انتظار در پاسخ
     * @param requestBody                          the request body to be sent
     * @param externalOrganizationApiServiceEntity external API endpoint metadata
     * @param responseBody                         expected response body class
     *                                             <p>
     * @return پاسخ دریافتی به صورت ResponseEntity
     * @return response wrapped in a ResponseEntity
     * <p>
     * @Fa: ارسال یک درخواست به سرویس خارجی با استفاده از RestTemplate و دریافت پاسخ آن.
     * @En: Sends a request to an external service using RestTemplate and returns the response.
     */
    <T, R> ResponseEntity<R> sendRequest(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseBody);

    /**
     * @param requestBody                          محتوای درخواستی
     * @param externalOrganizationApiServiceEntity اطلاعات API خارجی
     * @param objectResponseType                   کلاس پاسخ مورد انتظار
     * @param requestBody                          the request to send
     * @param externalOrganizationApiServiceEntity external API information
     * @param objectResponseType                   expected response type
     * @return پاسخ دریافتی از سرویس خارجی
     * @return response entity
     * @Fa : ارسال درخواست به سرویس خارجی با در نظر گرفتن استراتژی نوع ارسال (POST, GET و غیره).
     * @En : Sends a request considering the request strategy (e.g., POST, GET).
     */
    <T, R> ResponseEntity<R> sendRequestByStrategyRequestSendType(T requestBody,
                                                                  ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity,
                                                                  Class<R> objectResponseType);
}
