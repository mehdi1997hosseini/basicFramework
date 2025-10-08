package ir.mehdihosseini.basicframework.base.exceptionHandler.exception;

import ir.mehdihosseini.basicframework.base.exceptionHandler.BasicSpecificationException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppRunTimeException extends RuntimeException {

    private final BasicSpecificationException error;
    private final HttpStatus httpStatus;
    private String detail;
    private Object[] digits;

    /************************************************************/
    /********** IS NOT HAVE HTTP-STATUS IN CONSTRUCTOR *********/
    /***********************************************************/

    public AppRunTimeException(BasicSpecificationException error) {
        super(error.getMessageKey());
        this.error = error;
        this.httpStatus = HttpStatus.OK;
    }

    public AppRunTimeException(BasicSpecificationException error, String detail) {
        super(error.getMessageKey());
        this.error = error;
        this.detail = detail;
        this.httpStatus = HttpStatus.OK;
    }

    public AppRunTimeException(BasicSpecificationException error, String detail, Object... digits) {
        super(error.getMessageKey());
        this.error = error;
        this.detail = detail;
        this.digits = digits;
        this.httpStatus = HttpStatus.OK;
    }

    public AppRunTimeException(BasicSpecificationException error, Object... digits) {
        super(error.getMessageKey());
        this.error = error;
        this.digits = digits;
        this.httpStatus = HttpStatus.OK;
    }

    /*******************************************************/
    /********** IS HAVE HTTP-STATUS IN CONSTRUCTOR *********/
    /*******************************************************/

    public AppRunTimeException(BasicSpecificationException error, HttpStatus httpStatus) {
        super(error.getMessageKey());
        this.error = error;
        this.httpStatus = httpStatus;
    }

    public AppRunTimeException(BasicSpecificationException error, HttpStatus httpStatus, Object... digits) {
        super(error.getMessageKey());
        this.error = error;
        this.digits = digits;
        this.httpStatus = httpStatus;
    }

    public AppRunTimeException(BasicSpecificationException error, String detail, HttpStatus httpStatus) {
        super(error.getMessageKey());
        this.error = error;
        this.detail = detail;
        this.httpStatus = httpStatus;
    }

    public AppRunTimeException(BasicSpecificationException error, String detail, HttpStatus httpStatus, Object... digits) {
        super(error.getMessageKey());
        this.error = error;
        this.detail = detail;
        this.digits = digits;
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "{" +
                "message=" + error.getMessageKey() +
                ", code='" + error.getErrorCode() + '\'' +
                ", detail='" + detail + '\'' +
                ", httpStatus=" + httpStatus +
                '}';
    }

}
