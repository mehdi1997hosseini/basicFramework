package com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate;

import com.mehdihosseini.framework.basicframework.exceptionHandler.exception.AppRunTimeException;
import com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate.failed.ExternalErrorResponseIdentifiable;
import com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate.parser.ExternalCommunicationParser;
import com.mehdihosseini.framework.basicframework.thirdParty.communicationRestTemplate.unified.ExternalUnifiedResponse;
import com.mehdihosseini.framework.basicframework.thirdParty.exceptionHandler.ExternalOrganizationExceptionHandler;
import com.mehdihosseini.framework.basicframework.thirdParty.externalOrganizationInfo.ExternalOrganizationInfoEntity;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommunicationExternalOrganizationServiceImpl implements CommunicationExternalOrganizationService {

    private final BasicRestTemplateCommunicationWithOtherService restTemplateCommunicationWithOtherService;
    private final Map<ExternalOrganizationInfoEntity, ExternalCommunicationParser> parserMap;

    public CommunicationExternalOrganizationServiceImpl(BasicRestTemplateCommunicationWithOtherService restTemplateCommunicationWithOtherService,
                                                        List<ExternalCommunicationParser> parsers) {
        this.restTemplateCommunicationWithOtherService = restTemplateCommunicationWithOtherService;
        this.parserMap = new HashMap<>();
        parsers.forEach(parser -> parserMap.put(parser.getExternalOrganization(), parser));
    }


    @Override
    public <T, R, F extends ExternalErrorResponseIdentifiable> ExternalUnifiedResponse sendRequest(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseAccept, Class<F> responseFailed) {
        return sendRequest(requestBody,externalOrganizationApiServiceEntity,responseAccept,List.of(responseFailed));
    }

    @Override
    public <T, R, F extends ExternalErrorResponseIdentifiable> ExternalUnifiedResponse sendRequest(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseAccept, List<Class<F>> responseFailedList) {
        try {

            ExternalOrganizationInfoEntity externalOrganizationInfo = externalOrganizationApiServiceEntity.getExternalOrganizationInfo();
            ResponseEntity<String> response = restTemplateCommunicationWithOtherService
                    .sendRequestByStrategyRequestSendType(requestBody, externalOrganizationApiServiceEntity, String.class);

            String responseBody = response.getBody();

            return parserMap.get(externalOrganizationInfo).parseResponse(responseBody, responseAccept, responseFailedList);

        } catch (Exception e) {
            throw new AppRunTimeException(ExternalOrganizationExceptionHandler.ERROR_FROM_EXTERNAL_ORGANIZATION_SERVICE, e.getMessage());
        }
    }
}
