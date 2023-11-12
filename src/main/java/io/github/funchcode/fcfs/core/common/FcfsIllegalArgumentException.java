package io.github.funchcode.fcfs.core.common;

import lombok.Getter;

@Getter
public class FcfsIllegalArgumentException extends FcfsRuntimeException {

    public FcfsIllegalArgumentException(ErrorCode errorCode) {
        super(errorCode);
    }

    public FcfsIllegalArgumentException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public FcfsIllegalArgumentException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    @Override
    public FcfsRuntimeException setExternalMessage(String externalMessage) {
        return super.setExternalMessage(externalMessage);
    }

}
