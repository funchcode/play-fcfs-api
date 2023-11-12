package io.github.funchcode.fcfs.core.subject;

import io.github.funchcode.fcfs.core.common.ErrorCode;
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

    /**
     * SUBJECT 발급 가능 상태 확인
     */
    private void checkIssueable() {
        subject.checkIssueable();
        if (quantityLeft() <= 0) {
            throw new TicketIssueException(ErrorCode.TICKET_POLICY).setExternalMessage("티켓 여분이 없습니다.");
        }
    }

    /**
     * 티켓 남은 여분
     */
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

    /**
     * 티켓 발급
     */
    public Ticket issueTicket(String clientId) {
        checkIssueable();
        if (getTicketByClientId(clientId).isPresent()) {
            throw new TicketIssueException(ErrorCode.TICKET_POLICY, String.format("clientId = %s", clientId)).setExternalMessage("이미 등록된 요청입니다.");
        }
        Ticket ticket = new Ticket(subject, clientId);
        tickets.add(ticket);
        return ticket;
    }

}
