package com.mehdihosseini.framework.basicframework.exceptionHandler;

import com.mehdihosseini.framework.basicframework.exceptionHandler.dynamicMessageSource.DynamicMessageSourceService;
import com.mehdihosseini.framework.basicframework.exceptionHandler.exception.AppRunTimeException;
import com.mehdihosseini.framework.basicframework.exceptionHandler.exception.AppSqlException;
import com.mehdihosseini.framework.basicframework.exceptionHandler.exceptionType.BasicRequestExceptionType;
import com.mehdihosseini.framework.basicframework.exceptionHandler.exceptionType.BasicSystemExceptionType;
import com.mehdihosseini.framework.basicframework.exceptionHandler.lang.ResponseMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class BasicGlobalExceptionHandler {

    private final DynamicMessageSourceService dynamicMessageSource;

    public BasicGlobalExceptionHandler(DynamicMessageSourceService dynamicMessageSource) {
        this.dynamicMessageSource = dynamicMessageSource;
    }

    @ExceptionHandler(AppRunTimeException.class)
    public ResponseEntity<?> HandlerException(AppRunTimeException ex, HttpServletRequest request) {
        List<ResponseMessageDto> responseMessage = dynamicMessageSource.getMessageResponse(ex.getError().getMessage(), ex.getDigits());
        BasicResponseException build = BasicResponseException.builder().responseMessage(responseMessage)
                .detailMessage(ex.getDetail())
                .code(ex.getError().getErrorCode())
                .instanceURI(request.getRequestURI())
                .build();
        return buildResponse(build, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        BasicRequestExceptionType isNotValid = BasicRequestExceptionType.ENTERED_VALUE_IS_NOT_VALID;
        List<ResponseMessageDto> responseMessage = dynamicMessageSource.getMessageResponse(isNotValid.getMessage(), errorMessage);
        BasicResponseException basicResponseException = BasicResponseException.builder().responseMessage(responseMessage)
                .code(isNotValid.getErrorCode())
                .detailMessage(errorMessage)
                .instanceURI(request.getRequestURI())
                .build();
        return buildResponse(basicResponseException, HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        BasicRequestExceptionType isNotValid = BasicRequestExceptionType.ENTERED_VALUE_IS_NOT_VALID;
        List<ResponseMessageDto> responseMessage = dynamicMessageSource.getMessageResponse(isNotValid.getMessage(), errorMessage);

        BasicResponseException basicResponseException = BasicResponseException.builder().responseMessage(responseMessage)
                .code(isNotValid.getErrorCode())
                .detailMessage(errorMessage)
                .instanceURI(request.getRequestURI())
                .build();
        return buildResponse(basicResponseException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppSqlException.class)
    protected ResponseEntity<Object> handleSQLExceptions(AppSqlException appSqlException, HttpServletRequest request) {
        List<ResponseMessageDto> responseMessage = dynamicMessageSource.getMessageResponse(appSqlException.getError().getMessage(), appSqlException.getDigits());
        BasicResponseException responseException = BasicResponseException.builder().responseMessage(responseMessage)
                .code(appSqlException.getError().getErrorCode())
                .detailMessage(appSqlException.getDetail())
                .instanceURI(request.getRequestURI())
                .build();
        return buildResponse(responseException, appSqlException.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex, HttpServletRequest request) {
        BasicSystemExceptionType internalServerError = BasicSystemExceptionType.INTERNAL_SERVER_ERROR;
        List<ResponseMessageDto> responseMessage = dynamicMessageSource.getMessageResponse(internalServerError.getMessage(), (Object[]) null);

        BasicResponseException responseException = BasicResponseException.builder().responseMessage(responseMessage)
                .code(internalServerError.getErrorCode())
                .detailMessage(ex.getLocalizedMessage())
                .instanceURI(request.getRequestURI()).build();
        return buildResponse(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {

        BasicRequestExceptionType pageNotFound = BasicRequestExceptionType.PAGE_NOT_FOUND;
        List<ResponseMessageDto> responseMessage = dynamicMessageSource.getMessageResponse(pageNotFound.getMessage(), (Object[]) null);
        BasicResponseException responseException = BasicResponseException.builder().responseMessage(responseMessage)
                .code(pageNotFound.getErrorCode())
                .detailMessage(ex.getMessage())
                .instanceURI(request.getRequestURI())
                .build();


        return buildResponse(responseException, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildResponse(BasicResponseException responseException, HttpStatus status) {
        return new ResponseEntity<>(responseException, status);
    }

}
