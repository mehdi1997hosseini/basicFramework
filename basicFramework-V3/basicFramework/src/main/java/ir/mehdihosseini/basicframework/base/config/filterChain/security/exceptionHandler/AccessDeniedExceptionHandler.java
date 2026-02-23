package ir.mehdihosseini.basicframework.base.config.filterChain.security.exceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mehdihosseini.basicframework.base.exceptionHandler.BasicExceptionResponse;
import ir.mehdihosseini.basicframework.base.exceptionHandler.ExceptionMessageModel;
import ir.mehdihosseini.basicframework.base.exceptionHandler.service.BasicExceptionMessageService;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicSecurityExceptionType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class AccessDeniedExceptionHandler implements AccessDeniedHandler {
    private final BasicExceptionMessageService dynamicMessageSource;
    private final ObjectMapper mapper;

    public AccessDeniedExceptionHandler(BasicExceptionMessageService dynamicMessageSource, ObjectMapper mapper) {
        this.dynamicMessageSource = dynamicMessageSource;
        this.mapper = mapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        BasicSecurityExceptionType accessDenied = BasicSecurityExceptionType.ACCESS_DENIED;

        List<ExceptionMessageModel> responseMessage = dynamicMessageSource
                .getMessages(accessDenied.getMessageKey(), (Object) null);

        responseMessage.forEach(entity -> {
            if (entity.getStatusCode() == null)
                entity.setStatusCode(accessDenied.getErrorCode());

        });

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        mapper.writeValue(response.getOutputStream(), BasicExceptionResponse.builder()
                .exceptionMessage(responseMessage)
                .code(accessDenied.getErrorCode())
                .detailMessage(accessDeniedException.getMessage())
                .instanceURI(request.getRequestURI())
                .build());

    }
}
