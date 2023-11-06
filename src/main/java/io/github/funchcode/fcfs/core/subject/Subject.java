package io.github.funchcode.fcfs.core.subject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Subject {

    private final String id;
    private final int limitedQuantityOf;
    private final Status status;

    public Subject(String id, int limitedQuantityOf, Status status) {
        this.id = id;
        this.limitedQuantityOf = limitedQuantityOf;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public int getLimitedQuantityOf() {
        return limitedQuantityOf;
    }

    // 발급 가능한 기간인지 확인
    private boolean issueablePeriod() {
        return false;
    }

    private boolean issueableStatus() {
        return false;
    }

    // SUBJECT 상태
    void checkIssueable() {
        // 취득 가능한 상태인지 확인 (진행전, 진행중, 종료, 취소)
        if (!issueableStatus()) {
            throw new TicketIssueException("상태 에러");
        }
        // 취득할 수 있는 기간인지 확인 23.11.10 13:30:00 ~ 23.11.20 13:35:00
        if (!issueablePeriod()) {
            throw new TicketIssueException("기간 에러");
        }
    }

}
