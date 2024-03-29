package io.github.funchcode.fcfs.core.subject;

import io.github.funchcode.fcfs.core.common.ErrorCode;
import io.github.funchcode.fcfs.core.common.FcfsIllegalArgumentException;
import io.github.funchcode.fcfs.core.common.FcfsRuntimeException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Getter
public final class Subject {

    private final String id;
    private final int limitedQuantityOf;
    private final LocalDateTime openDate;
    private final LocalDateTime deadlineDate;
    private final Status status;

    public Subject(String id, int limitedQuantityOf, LocalDateTime openDate, LocalDateTime deadlineDate, Status status) {
        if (limitedQuantityOf <= 0) {
            throw new FcfsIllegalArgumentException(ErrorCode.SUBJECT_POLICY).setExternalMessage("0이하의 수량을 설정할 수 없습니다.");
        }
        if (openDate.isAfter(deadlineDate)) {
            throw new FcfsIllegalArgumentException(ErrorCode.SUBJECT_POLICY).setExternalMessage("오픈 날짜는 마감 날짜 이후로 설정할 수 없습니다.");
        }
        if (deadlineDate.isBefore(LocalDateTime.now())) {
            throw new FcfsIllegalArgumentException(ErrorCode.SUBJECT_POLICY).setExternalMessage("마감 날짜는 과거로 설정할 수 없습니다.");
        }
        this.id = id;
        this.limitedQuantityOf = limitedQuantityOf;
        this.openDate = openDate;
        this.deadlineDate = deadlineDate;
        this.status = status;
    }

    public static Subject newInstance(int limitedQuantityOf, LocalDateTime openDate, LocalDateTime deadlineDate) {
        return new Subject(UUID.randomUUID().toString(), limitedQuantityOf, openDate, deadlineDate, openDate.isAfter(LocalDateTime.now()) ? Status.WAIT : Status.ONGOING);
    }

    /**
     * 발급 가능한 기간인지 확인
     */
    private boolean issueablePeriod() {
        LocalDateTime today = LocalDateTime.now();
        return openDate.isBefore(today) && deadlineDate.isAfter(today);
    }

    /**
     * 발급 가능한 Subject 상태인지 확인
     */
    private boolean issueableStatus() {
        return Status.ONGOING.equals(status);
    }

    void checkIssueable() {
        if (!issueableStatus()) {
            throw new TicketIssueException(ErrorCode.TICKET_POLICY).setExternalMessage("발급할 수 없는 상태입니다.");
        }
        if (!issueablePeriod()) {
            throw new TicketIssueException(ErrorCode.TICKET_POLICY).setExternalMessage("발급할 수 없는 기간입니다.");
        }
    }

}
