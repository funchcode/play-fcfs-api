package io.github.funchcode.fcfs.core.common;

import lombok.Getter;

@Getter
public class FcfsRuntimeException extends RuntimeException {

    private final ErrorCode errorCode;
    private String externalMessage = "";

    public FcfsRuntimeException(ErrorCode errorCode) {
        super(String.format("[%d: %s]", errorCode.getCode(), errorCode.name()));
        this.errorCode = errorCode;
    }

    public FcfsRuntimeException(ErrorCode errorCode, String message) {
        super(String.format("[%d: %s] %s", errorCode.getCode(), errorCode.name(), message));
        this.errorCode = errorCode;
    }

    public FcfsRuntimeException(ErrorCode errorCode, String message, Throwable cause) {
        super(String.format("[%d: %s] %s", errorCode.getCode(), errorCode.name(), message), cause);
        this.errorCode = errorCode;
    }

    public FcfsRuntimeException setExternalMessage(String externalMessage) {
        this.externalMessage = externalMessage;
        return this;
    }

}
