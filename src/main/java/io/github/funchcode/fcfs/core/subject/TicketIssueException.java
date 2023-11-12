package io.github.funchcode.fcfs.core.subject;

import io.github.funchcode.fcfs.core.common.ErrorCode;
import io.github.funchcode.fcfs.core.common.FcfsRuntimeException;
import lombok.Getter;

@Getter
public class TicketIssueException extends FcfsRuntimeException {

    public TicketIssueException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TicketIssueException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public TicketIssueException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    @Override
    public FcfsRuntimeException setExternalMessage(String externalMessage) {
        return super.setExternalMessage(externalMessage);
    }

}
