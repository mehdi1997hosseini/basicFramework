package ir.mehdihosseini.basicframework.base.exceptionHandler;

import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppRunTimeException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppSqlException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.DatabaseExceptionUtilities;
import ir.mehdihosseini.basicframework.base.exceptionHandler.service.BasicExceptionMessageService;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicInternalSystemExceptionType;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicRequestExceptionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class BasicGlobalExceptionHandler {

    private final BasicExceptionMessageService dynamicMessageSource;
    private final DatabaseExceptionUtilities databaseExceptionUtilities;
    private final DataSource dataSource;

    public BasicGlobalExceptionHandler(BasicExceptionMessageService dynamicMessageSource, DatabaseExceptionUtilities databaseExceptionUtilities, DataSource dataSource) {
        this.dynamicMessageSource = dynamicMessageSource;
        this.databaseExceptionUtilities = databaseExceptionUtilities;
        this.dataSource = dataSource;
    }

    private void executeStatement(String sql) {
        try {
            dataSource.getConnection().createStatement().execute(sql);
        } catch (SQLException e) {
            handleDatabaseException(e);
        } catch (Exception e) {
            if (ExceptionUtils.hasCause(e, SQLException.class)) {
                Throwable sqlException = ExceptionUtils.getThrowableList(e).get(ExceptionUtils.indexOfType(e, SQLException.class));
                handleDatabaseException((SQLException) sqlException);
            }
        }
    }

    protected void handleDatabaseException(SQLException exception)
    {
        if (databaseExceptionUtilities.isExceptionBadGrammerSQL(exception))
            System.out.println("Bad Grammar Exception: " + exception.toString());

        else if (databaseExceptionUtilities.isExceptionADuplicate(exception))
            System.out.println("Duplicate Exception: " + exception.toString());

        else if (databaseExceptionUtilities.isExceptionADeadlock(exception))
            System.out.println("Deadlock Exception: " + exception.toString());

        else if (databaseExceptionUtilities.isExceptionADataIntegrityViolation(exception))
            System.out.println("Data Integrity Violation Exception: " + exception.toString());

    }


    @ExceptionHandler(AppRunTimeException.class)
    public ResponseEntity<?> HandlerException(AppRunTimeException ex, HttpServletRequest request) {
        BasicSpecificationException error = ex.getError();
        List<ExceptionMessageModel> responseMessage =
                dynamicMessageSource.getMessages(error.getMessageKey(), ex.getDigits());

        responseMessage.forEach(entity -> {
            if (entity.getStatusCode().isBlank())
                entity.setStatusCode(error.getErrorCode());

        });

        return buildResponse(BasicExceptionResponse.builder().exceptionMessage(responseMessage)
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

        List<ExceptionMessageModel> responseMessage = dynamicMessageSource.getMessages(isNotValid.getMessageKey(), errorMessage);

        responseMessage.forEach(entity -> {
            if (entity.getStatusCode().isBlank())
                entity.setStatusCode(isNotValid.getErrorCode());

        });

        return buildResponse(BasicExceptionResponse.builder().exceptionMessage(responseMessage)
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
        List<ExceptionMessageModel> responseMessage = dynamicMessageSource
                .getMessages(isNotValid.getMessageKey(), digits);

        responseMessage.forEach(entity -> {
            if (entity.getStatusCode().isBlank())
                entity.setStatusCode(isNotValid.getErrorCode());

        });

        return buildResponse(BasicExceptionResponse.builder().exceptionMessage(responseMessage)
                .code(isNotValid.getErrorCode())
                .detailMessage(ex.getMessage())
                .instanceURI(request.getRequestURI())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppSqlException.class)
    protected ResponseEntity<Object> handleSQLExceptions(AppSqlException ex, HttpServletRequest request) {
        BasicSpecificationException error = ex.getError();

        List<ExceptionMessageModel> responseMessage = dynamicMessageSource.getMessages(error.getMessageKey(), (Object) null);
        responseMessage.forEach(entity -> {
            if (entity.getStatusCode().isBlank())
                entity.setStatusCode(error.getErrorCode());

        });

        return buildResponse(BasicExceptionResponse.builder()
                .exceptionMessage(responseMessage)
                .code(error.getErrorCode())
                .detailMessage(ex.getDetail())
                .instanceURI(request.getRequestURI())
                .build(), ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex, HttpServletRequest request) {

        if (ExceptionUtils.hasCause(ex, SQLException.class)) {
            Throwable sqlException = ExceptionUtils.getThrowableList(ex).get(ExceptionUtils.indexOfType(ex, SQLException.class));
            handleDatabaseException((SQLException) sqlException);
        }

        BasicInternalSystemExceptionType internalServerError = BasicInternalSystemExceptionType.INTERNAL_SERVER_ERROR;
        List<ExceptionMessageModel> responseMessage = dynamicMessageSource
                .getMessages(internalServerError.getMessageKey(), (Object) null);

        responseMessage.forEach(entity -> {
            if (entity.getStatusCode().isBlank())
                entity.setStatusCode(internalServerError.getErrorCode());

        });

        return buildResponse(BasicExceptionResponse.builder()
                .exceptionMessage(responseMessage)
                .code(internalServerError.getErrorCode())
                .detailMessage(ex.getMessage())
                .instanceURI(request.getRequestURI())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {

        BasicRequestExceptionType pageNotFound = BasicRequestExceptionType.PAGE_NOT_FOUND;

        List<ExceptionMessageModel> responseMessage = dynamicMessageSource
                .getMessages(pageNotFound.getMessageKey(), (Object) null);

        responseMessage.forEach(entity -> {
            if (entity.getStatusCode() == null)
                entity.setStatusCode(pageNotFound.getErrorCode());

        });

        return buildResponse(BasicExceptionResponse.builder()
                .exceptionMessage(responseMessage)
                .code(pageNotFound.getErrorCode())
                .detailMessage(ex.getBody().getDetail())
                .instanceURI(request.getRequestURI())
                .build(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildResponse(BasicExceptionResponse responseException, HttpStatus status) {
        return new ResponseEntity<>(responseException, status);
    }

}
