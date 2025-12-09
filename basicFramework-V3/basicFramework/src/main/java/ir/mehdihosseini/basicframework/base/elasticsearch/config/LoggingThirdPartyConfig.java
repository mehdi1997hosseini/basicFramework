package ir.mehdihosseini.basicframework.base.elasticsearch.config;

import ir.mehdihosseini.basicframework.base.elasticsearch.entity.ProviderElasticEntity;
import ir.mehdihosseini.basicframework.base.elasticsearch.entity.StatusPerRequestType;
import ir.mehdihosseini.basicframework.base.elasticsearch.repository.ProviderElasticRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.stream.Collectors;


@Slf4j
@Component
@ConditionalOnProperty(prefix = "manager.elastic-search.trace.third-party", name = "isEnable", havingValue = "true")
public class LoggingThirdPartyConfig implements ClientHttpRequestInterceptor {

    @Value("${server.current.host:127.0.0.1}")
    private String SERVER_HOST;
    private final ProviderElasticRepository providerRepository;

    public LoggingThirdPartyConfig(ProviderElasticRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        ProviderElasticEntity providerElasticModel = new ProviderElasticEntity();
        providerElasticModel.setCreateTimeDate(Instant.now());
        providerElasticModel.setHostIp(SERVER_HOST);
        ClientHttpResponse response = null;
        logRequest(body, providerElasticModel, request.getURI().toString());
        try {
            providerElasticModel.setMethod(request.getMethod().name());
            providerElasticModel.setAddress(request.getURI().toString());
            response = execution.execute(request, body);
            logResponse(response, providerElasticModel, request.getURI().toString());
            try {
                providerRepository.save(providerElasticModel);
            } catch (Exception e) {
                log.error("Error in saved data into elk");
                e.printStackTrace();
            }
        } catch (ResourceAccessException | UnknownHostException | ConnectException e) {
            log.warn("EXCEPTION------------------- " + e.getMessage());
            providerElasticModel.setResponseBody(e.getLocalizedMessage());
            providerElasticModel.setStatus(StatusPerRequestType.FAILED);
            providerRepository.save(providerElasticModel);
            throw e;
        } catch (Exception e) {
            log.warn("EXCEPTION------------------- " + e.getMessage());
            providerElasticModel.setResponseBody(e.getMessage());
            providerElasticModel.setStatus(StatusPerRequestType.FAILED);
            providerRepository.save(providerElasticModel);
            throw e;
        }

        return response;
    }

    private void logRequest(byte[] body, ProviderElasticEntity providerElasticModel, String uri) throws IOException {
        String requestBody = new String(body, "UTF-8");
        if (uri.contains("login")) {
            providerElasticModel.setRequestBody(requestBody.trim().replaceAll("\"password\":\".*?\"", "\"password\":\"****\""));
        } else {
            providerElasticModel.setRequestBody(requestBody);
        }
    }

    private void logResponse(ClientHttpResponse response, ProviderElasticEntity providerElasticModel, String uri) throws IOException {

        log.info("===log response start===");
        String text = null;
        try {
            text = new BufferedReader(
                    new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            try {
                text = new BufferedReader(
                        new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));

            } catch (Exception e1) {
                log.error(e1.getMessage());
            }

        }
        if (uri.contains("login")) {
            log.info("TEST TEXT :------->>>>>>>>> " + text);
            assert text != null;
            providerElasticModel.setResponseBody(text.replaceAll("\"token\":\".*?\"", "\"token\":\"****\""));

            providerElasticModel.setStatus(response.getStatusCode().is2xxSuccessful() ?
                    StatusPerRequestType.SUCCESS :
                    StatusPerRequestType.UN_SUCCESS);
        } else {
            log.info("TEST TEXT :------->>>>>>>>> " + text);
            providerElasticModel.setResponseBody(text);
            providerElasticModel.setStatus(!response.getStatusCode().is2xxSuccessful() ?
                    StatusPerRequestType.SUCCESS :
                    StatusPerRequestType.UN_SUCCESS);

        }

        log.info("Response body: {}", text);
        log.info("===log response end===");

    }

}