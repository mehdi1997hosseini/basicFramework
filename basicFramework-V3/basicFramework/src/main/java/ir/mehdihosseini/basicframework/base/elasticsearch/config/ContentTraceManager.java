package ir.mehdihosseini.basicframework.base.elasticsearch.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mehdihosseini.basicframework.base.elasticsearch.entity.RequestElasticEntity;
import ir.mehdihosseini.basicframework.base.elasticsearch.repository.RequestElasticRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;

@Component
@ConditionalOnProperty(prefix = "manager.elastic-search.trace.input-request", name = "isEnable", havingValue = "true")
@RequestScope
@Slf4j
public class ContentTraceManager {
    private final RequestElasticRepository elkService;
    private final ObjectMapper objectMapper;

    public ContentTraceManager(RequestElasticRepository elkService, ObjectMapper objectMapper) {
        this.elkService = elkService;
        this.objectMapper = objectMapper;
    }


    public void updateBody(ContentCachingRequestWrapper wrappedRequest,
                           ContentCachingResponseWrapper wrappedResponse) {
        RequestElasticEntity model = new RequestElasticEntity();
        String requestBody = getEffectiveRequest(wrappedRequest);

        String responseBody = "";
        try {
            responseBody = getResponseBody(wrappedResponse);
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
        }

        if (wrappedRequest.getRequestURI().contains("login")
                && requestBody != null
                || wrappedRequest.getRequestURI().contains("fetch")) {

            model.setRequestBody(requestBody.trim()
                    .replaceAll("\"password\":\".*?\""
                            , "\"password\":\"****\""));
            assert responseBody != null;
            model.setResponseBody(responseBody.replaceAll("\"accessToken\":\".*?\"", "\"accessToken\":\"****\""));
        } else {
            model.setRequestBody(requestBody);
            model.setResponseBody(responseBody);
        }

        model.setCreateTimeDate(Instant.now());

        elkService.save(model);


    }

    private String getRequestBody(ContentCachingRequestWrapper wrappedRequest) {
        try {
            if (wrappedRequest.getContentLength() <= 0) {
                return null;
            }
            wrappedRequest.setCharacterEncoding("utf-8");
            return new String(wrappedRequest.getContentAsByteArray(),
                    StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            log.error(
                    "Could not read cached request body: " + e.getMessage());
            return null;
        }

    }

    private String getResponseBody(ContentCachingResponseWrapper wrappedResponse) {

        if (wrappedResponse.getContentSize() <= 0) {
            return null;
        }
        wrappedResponse.setCharacterEncoding("UTF-8");
        return new String(wrappedResponse.getContentAsByteArray(),
                StandardCharsets.UTF_8);
    }

    private String getEffectiveRequest(ContentCachingRequestWrapper wrappedRequest) {

        try {
            String requestBody = getRequestBody(wrappedRequest);

            if (requestBody != null)
                return requestBody;

            if (wrappedRequest.getQueryString() != null) {
                return objectMapper.writeValueAsString(wrappedRequest.getQueryString());
            }

            Object attribute = wrappedRequest
                    .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            if (attribute != null) {
                return objectMapper.writeValueAsString(attribute);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;

    }

}
