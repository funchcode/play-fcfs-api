package io.github.funchcode.fcfs.core.ticket;

import io.github.funchcode.fcfs.AbstractContainerBaseTest;
import io.github.funchcode.fcfs.core.subject.Status;
import io.github.funchcode.fcfs.core.subject.Subject;
import io.github.funchcode.fcfs.core.subject.TicketIssueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@ActiveProfiles("mytest")
@SpringBootTest
public class TicketRedisServiceTest extends AbstractContainerBaseTest {

    @Autowired
    private TicketRedisService ticketRedisService;

    @Test
    @DisplayName("티켓 정보 캐싱 (동일 요청자 정책)")
    void cachingTicket_ExistClient() {
        String clientId = "01";
        LocalDateTime today = LocalDateTime.now();
        Subject subject = new Subject("test-subject-id", 10, today.minusDays(1L), today.plusDays(2L), Status.ONGOING);
        Ticket ticket = new Ticket("test-ticket-id", subject, clientId);

        Assertions.assertDoesNotThrow(() -> ticketRedisService.cachingTicket(ticket));
        Assertions.assertThrows(TicketIssueException.class, () -> ticketRedisService.cachingTicket(ticket));
    }

    @Test
    @DisplayName("티켓 정보 캐싱 (수량 정책)")
    void cachingTicket_InvalidQuantityLeft() {
        int limitedQuantityOf = 3;
        LocalDateTime today = LocalDateTime.now();
        Subject subject = new Subject("test-subject-id", limitedQuantityOf, today.minusDays(1L), today.plusDays(2L), Status.ONGOING);
        Ticket firstTicket = new Ticket("test-ticket-id01", subject, "01");
        Ticket secondTicket = new Ticket("test-ticket-id02", subject, "02");
        Ticket thirdTicket = new Ticket("test-ticket-id03", subject, "03");
        Ticket exceedTicket = new Ticket("test-ticket-id04", subject, "04");

        Assertions.assertDoesNotThrow(() -> ticketRedisService.cachingTicket(firstTicket));
        Assertions.assertDoesNotThrow(() -> ticketRedisService.cachingTicket(secondTicket));
        Assertions.assertDoesNotThrow(() -> ticketRedisService.cachingTicket(thirdTicket));
        Assertions.assertThrows(TicketIssueException.class, () -> ticketRedisService.cachingTicket(exceedTicket));
    }

}
