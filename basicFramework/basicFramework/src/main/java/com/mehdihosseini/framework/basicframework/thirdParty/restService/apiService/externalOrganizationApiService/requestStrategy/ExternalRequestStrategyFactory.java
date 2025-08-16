package com.mehdihosseini.framework.basicframework.thirdParty.restService.apiService.externalOrganizationApiService.requestStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class ExternalRequestStrategyFactory {
    private final Map<RequestSendType, BasicExternalServiceRequestStrategy> strategies;

    public BasicExternalServiceRequestStrategy getStrategy(RequestSendType type) {
        return strategies.get(type);
    }

//    @PostConstruct
//    public void init() {
//        // Optional logging or validation
//    }

    @Autowired
    public ExternalRequestStrategyFactory(List<BasicExternalServiceRequestStrategy> strategyList) {
        this.strategies = new EnumMap<>(RequestSendType.class);
        for (BasicExternalServiceRequestStrategy strategy : strategyList) {
            RequestSendType type = resolveType(strategy);
            this.strategies.put(type, strategy);
        }
    }

    private RequestSendType resolveType(BasicExternalServiceRequestStrategy strategy) {
        if (strategy instanceof BodyRequestStrategy) return RequestSendType.BODY;
        if (strategy instanceof QueryParamRequestStrategy) return RequestSendType.QUERY_PARAM;
        if (strategy instanceof PathParamRequestStrategy) return RequestSendType.PATH_PARAM;
        if (strategy instanceof MultipartRequestStrategy) return RequestSendType.MULTIPART;
        if (strategy instanceof HeaderOnlyRequestStrategy) return RequestSendType.HEADER_ONLY;
        throw new IllegalArgumentException("Unknown strategy type: " + strategy.getClass().getName());
    }
}
