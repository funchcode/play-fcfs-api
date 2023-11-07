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
                new Ticket(subject, "01"),
                new Ticket(subject, "02"),
                new Ticket(subject, "03"),
                new Ticket(subject, "04"),
                new Ticket(subject, "05"),
                new Ticket(subject, "06"),
                new Ticket(subject, "07"),
                new Ticket(subject, "08"),
                new Ticket(subject, "09"),
                new Ticket(subject, "10")
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
                new Ticket(subject, "01"),
                new Ticket(subject, clientId)
        );
        Assertions.assertThrows(TicketIssueException.class, () -> new SubjectTickets(subject, new HashSet<>(tickets)).issueTicket(clientId));
        Assertions.assertDoesNotThrow(() -> new SubjectTickets(subject, new HashSet<>(tickets)).issueTicket("03"));
    }

}
