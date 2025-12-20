package ir.mehdihosseini.basicframework.base.exceptionHandler;

import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppRunTimeException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppSqlException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.lang.ResponseMessageDto;
import ir.mehdihosseini.basicframework.base.exceptionHandler.service.BasicExceptionHandlingMessageService;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicRequestExceptionType;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicInternalSystemExceptionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class BasicGlobalExceptionHandler {

    private final BasicExceptionHandlingMessageService dynamicMessageSource;

    public BasicGlobalExceptionHandler(BasicExceptionHandlingMessageService dynamicMessageSource) {
        this.dynamicMessageSource = dynamicMessageSource;
    }

    @ExceptionHandler(AppRunTimeException.class)
    public ResponseEntity<?> HandlerException(AppRunTimeException ex, HttpServletRequest request) {
        BasicSpecificationException error = ex.getError();
        List<ExceptionHandlingModelResponse> responseMessage =
                dynamicMessageSource.getMessages(error.getMessageKey(), ex.getDigits());

        return buildResponse(BasicExceptionResponse.builder().responseMessage(responseMessage)
                .code(error.getErrorCode())
                .detailMessage(ex.getDetail())
                .instanceURI(request.getRequestURI())
                .build(), ex.getHttpStatus() == null ? HttpStatus.OK : ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        BasicRequestExceptionType isNotValid = BasicRequestExceptionType.ENTERED_VALUE_IS_NOT_VALID;

        List<ExceptionHandlingModelResponse> responseMessage = dynamicMessageSource.getMessages(isNotValid.getMessageKey(), errorMessage);

        return buildResponse(BasicExceptionResponse.builder().responseMessage(responseMessage)
                .code(isNotValid.getErrorCode())
                .detailMessage(ex.getBody().getDetail())
                .instanceURI(request.getRequestURI())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        String digits = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        BasicRequestExceptionType isNotValid = BasicRequestExceptionType.ENTERED_VALUE_IS_NOT_VALID;
        List<ExceptionHandlingModelResponse> responseMessage = dynamicMessageSource
                .getMessages(isNotValid.getMessageKey(), digits);

        return buildResponse(BasicExceptionResponse.builder().responseMessage(responseMessage)
                .code(isNotValid.getErrorCode())
                .detailMessage(ex.getMessage())
                .instanceURI(request.getRequestURI())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> handleSQLExceptions(SQLException ex, HttpServletRequest request) {
        AppSqlException appSqlException = AppSqlException.doJob(ex);
        BasicSpecificationException error = appSqlException.getError();

        List<ExceptionHandlingModelResponse> responseMessage = dynamicMessageSource.getMessages(error.getMessageKey(), (Object) null);
        return buildResponse(BasicExceptionResponse.builder()
                .responseMessage(responseMessage)
                .code(error.getErrorCode())
                .detailMessage(appSqlException.getDetail())
                .instanceURI(request.getRequestURI())
                .build(), appSqlException.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex, HttpServletRequest request) {
        BasicInternalSystemExceptionType internalServerError = BasicInternalSystemExceptionType.INTERNAL_SERVER_ERROR;
        List<ExceptionHandlingModelResponse> responseMessage = dynamicMessageSource
                .getMessages(internalServerError.getMessageKey(), (Object) null);

        return buildResponse(BasicExceptionResponse.builder()
                .responseMessage(responseMessage)
                .code(internalServerError.getErrorCode())
                .detailMessage(ex.getMessage())
                .instanceURI(request.getRequestURI())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {

        BasicRequestExceptionType pageNotFound = BasicRequestExceptionType.PAGE_NOT_FOUND;

        List<ExceptionHandlingModelResponse> responseMessage = dynamicMessageSource
                .getMessages(pageNotFound.getMessageKey(), (Object) null);

        return buildResponse(BasicExceptionResponse.builder()
                .responseMessage(responseMessage)
                .code(pageNotFound.getErrorCode())
                .detailMessage(ex.getBody().getDetail())
                .instanceURI(request.getRequestURI())
                .build(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildResponse(BasicExceptionResponse responseException, HttpStatus status) {
        return new ResponseEntity<>(responseException, status);
    }

}
