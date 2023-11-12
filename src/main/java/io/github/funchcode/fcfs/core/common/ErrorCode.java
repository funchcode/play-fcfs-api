package io.github.funchcode.fcfs.core.common;

public enum ErrorCode {

    NOTFOUND_ENTITY(5001),
    INVALID_PARAMETERS(5100),
    SUBJECT_POLICY(1100),
    TICKET_POLICY(1200);

    private final int code;
    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
