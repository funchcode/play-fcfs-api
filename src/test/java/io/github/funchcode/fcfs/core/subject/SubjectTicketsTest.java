package io.github.funchcode.fcfs.core.subject;

import io.github.funchcode.fcfs.core.ticket.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

public class SubjectTicketsTest {

    @Test
    @DisplayName("티켓 발급 (수량 정책)")
    void issueTicket_InvalidQuantityLeft() {
        int limitedQuantityOf = 10;
        LocalDateTime today = LocalDateTime.now();
        Subject subject = new Subject("test-subject-id", limitedQuantityOf, today.minusDays(1L), today.plusDays(2L), Status.ONGOING);
        List<Ticket> fullTickets = Arrays.asList(
                new Ticket("test-ticket-id01", subject, "01"),
                new Ticket("test-ticket-id02", subject, "02"),
                new Ticket("test-ticket-id03", subject, "03"),
                new Ticket("test-ticket-id04", subject, "04"),
                new Ticket("test-ticket-id05", subject, "05"),
                new Ticket("test-ticket-id06", subject, "06"),
                new Ticket("test-ticket-id07", subject, "07"),
                new Ticket("test-ticket-id08", subject, "08"),
                new Ticket("test-ticket-id09", subject, "09"),
                new Ticket("test-ticket-id10", subject, "10")
        );
        Assertions.assertThrows(TicketIssueException.class, () -> new SubjectTickets(subject, new HashSet<>(fullTickets)).issueTicket("11"));
        Assertions.assertDoesNotThrow(() -> new SubjectTickets(subject, new HashSet<>()).issueTicket("11"));
    }

    @Test
    @DisplayName("티켓 발급 (1인 1티켓 정책)")
    void issueTicket_InvalidSameClient() {
        String clientId = "02";
        int limitedQuantityOf = 3;
        LocalDateTime today = LocalDateTime.now();
        Subject subject = new Subject("test-subject-id", limitedQuantityOf, today.minusDays(1L), today.plusDays(2L), Status.ONGOING);
        List<Ticket> tickets = Arrays.asList(
                new Ticket("test-ticket-id01", subject, "01"),
                new Ticket("test-ticket-id02", subject, clientId)
        );
        Assertions.assertThrows(TicketIssueException.class, () -> new SubjectTickets(subject, new HashSet<>(tickets)).issueTicket(clientId));
        Assertions.assertDoesNotThrow(() -> new SubjectTickets(subject, new HashSet<>(tickets)).issueTicket("03"));
    }

}
