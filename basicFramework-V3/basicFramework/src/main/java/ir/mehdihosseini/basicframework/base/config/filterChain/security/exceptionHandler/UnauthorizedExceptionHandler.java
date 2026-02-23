package ir.mehdihosseini.basicframework.base.config.filterChain.security.exceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mehdihosseini.basicframework.base.exceptionHandler.BasicExceptionResponse;
import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionMessageModel;
import ir.mehdihosseini.basicframework.base.exceptionHandler.service.BasicExceptionMessageService;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicSecurityExceptionType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class UnauthorizedExceptionHandler implements AuthenticationEntryPoint {

    private final BasicExceptionMessageService dynamicMessageSource;
    private final ObjectMapper mapper;

    public UnauthorizedExceptionHandler(BasicExceptionMessageService dynamicMessageSource, ObjectMapper mapper) {
        this.dynamicMessageSource = dynamicMessageSource;

        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        BasicSecurityExceptionType unauthorized = BasicSecurityExceptionType.UNAUTHORIZED;

        List<ExceptionMessageModel> responseMessage = dynamicMessageSource
                .getMessages(unauthorized.getMessageKey(), (Object) null);

        responseMessage.forEach(entity -> {
            if (entity.getStatusCode() == null)
                entity.setStatusCode(unauthorized.getErrorCode());

        });

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        mapper.writeValue(response.getOutputStream(), BasicExceptionResponse.builder()
                .exceptionMessage(responseMessage)
                .code(unauthorized.getErrorCode())
                .detailMessage(authException.getMessage())
                .instanceURI(request.getRequestURI())
                .build());
    }

}
