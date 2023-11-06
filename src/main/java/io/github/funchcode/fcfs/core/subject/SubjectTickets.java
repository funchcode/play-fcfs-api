package io.github.funchcode.fcfs.core.subject;

import io.github.funchcode.fcfs.core.ticket.Ticket;

import java.util.Optional;
import java.util.Set;

public final class SubjectTickets {

    private final Subject subject;
    private final Set<Ticket> tickets;

    public SubjectTickets(Subject subject, Set<Ticket> tickets) {
        this.subject = subject;
        this.tickets = tickets;
    }

    // SUBJECT 발급 가능 상태 확인
    private void checkIssueable() {
        // 취득할 수 있는 기간인지 확인 23.11.10 13:30:00 ~ 23.11.20 13:35:00
        subject.checkIssueable();
        // 대상 여분 있는지 체크
        if (quantityLeft() <= 0) {
            throw new TicketIssueException("여분 없음");
        }
    }

    // 티켓 남은 여분
    public int quantityLeft() {
        return subject.getLimitedQuantityOf() - tickets.size();
    }

    private Optional<Ticket> getTicketByClientId(String clientId) {
        for (Ticket ticket : tickets) {
            if (clientId.equals(ticket.getClientId())) {
                return Optional.of(ticket);
            }
        }
        return Optional.empty();
    }

    // 티켓 발급
    public Ticket issueTicket(String clientId) {
        checkIssueable();
        // 이미 등록되어 있는지 체크
        if (getTicketByClientId(clientId).isPresent()) {
            throw new TicketIssueException("이미 발급");
        }
        Ticket ticket = new Ticket(subject, clientId);
        tickets.add(ticket);
        return ticket;
    }

}
