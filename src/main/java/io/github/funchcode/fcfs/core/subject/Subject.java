package io.github.funchcode.fcfs.core.subject;

import io.github.funchcode.fcfs.core.ticket.Ticket;

import java.util.Optional;
import java.util.Set;

public final class Subject {

    // final로 변경
    private int limitedQuantityOf = 100;
    // final로 변경
    private Set<Ticket> tickets;

    private int quantityLeft() {
        return 0;
    }

    private Optional<Ticket> getTicketByClientId(String clientId) {
        for (Ticket ticket : tickets) {
            if (clientId.equals(ticket.getClientId())) {
                return Optional.of(ticket);
            }
        }
        return Optional.empty();
    }

    // 취득 가능한 기간인지 확인
    private boolean issueablePeriod() {
        return false;
    }

    // 티켓 상태
    public boolean issueable() {
        // 취득할 수 있는 기간인지 확인 23.11.10 13:30:00 ~ 23.11.20 13:35:00
        // 대상 여분 있는지 체크
        if (!this.issueablePeriod()
            || quantityLeft() <= 0
        ) {
            return false;
        }
        return false;
    }

    // 티켓 발급
    public Ticket issueTicket(String clientId) {
        // 취득 가능한 상태인지 확인 (진행전, 진행중, 종료, 취소)
        if (this.issueable()) {
            // throw 취득 불가능한 상태
        }
        // 이미 등록되어 있는지 체크
        if (this.getTicketByClientId(clientId).isPresent()) {
            // throw 이미 등록된 사람
        }
        // local add ticket
        return new Ticket();
    }

}
