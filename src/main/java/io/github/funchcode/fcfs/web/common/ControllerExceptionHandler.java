package io.github.funchcode.fcfs.web.common;

import io.github.funchcode.fcfs.core.common.ErrorCode;
import io.github.funchcode.fcfs.core.common.FcfsIllegalArgumentException;
import io.github.funchcode.fcfs.core.common.FcfsRuntimeException;
import io.github.funchcode.fcfs.core.subject.TicketIssueException;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import software.amazon.awssdk.http.HttpStatusCode;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(FcfsRuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRcfsRuntimeException(FcfsRuntimeException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        switch (errorCode) {
            case TICKET_POLICY -> {
                return handleTicketIssueException((TicketIssueException) exception);
            }
            case INVALID_PARAMETERS -> {
                return handleFcfsIllegalArgumentException((FcfsIllegalArgumentException) exception);
            }
            case SUBJECT_POLICY, NOTFOUND_ENTITY -> {
                return ResponseEntity.status(409).body(new ErrorResponse(exception.getErrorCode(), exception.getExternalMessage()));
            }
            default -> {
                return ResponseEntity.status(HttpStatusCode.INTERNAL_SERVER_ERROR).body(new ErrorResponse(exception.getErrorCode(), exception.getExternalMessage()));
            }
        }
    }

    @ExceptionHandler(FcfsIllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleFcfsIllegalArgumentException(FcfsIllegalArgumentException exception) {
        return ResponseEntity.status(422).body(new ErrorResponse(exception.getErrorCode(), exception.getExternalMessage()));
    }

    @ExceptionHandler(TicketIssueException.class)
    public ResponseEntity<ErrorResponse> handleTicketIssueException(TicketIssueException exception) {
        return ResponseEntity.status(409).body(new ErrorResponse(exception.getErrorCode(), exception.getExternalMessage()));
    }

    @Getter
    public static final class ErrorResponse {
        private final int code;
        private final String name;
        private String message = "";

        public ErrorResponse(ErrorCode errorCode, String message) {
            this.code = errorCode.getCode();
            this.name = errorCode.name();
            this.message = message;
        }
    }

}
