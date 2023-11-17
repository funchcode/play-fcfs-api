package io.github.funchcode.fcfs.core.ticket;

import io.github.funchcode.fcfs.core.subject.Status;
import io.github.funchcode.fcfs.core.subject.Subject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@ActiveProfiles("mytest")
@SpringBootTest
public class TicketMessageServiceTest {

    @Autowired
    private TicketMessageService messageService;

    @Test
    @DisplayName("AABBCCDD")
    void abcdefg_as() {
        String clientId = "01";
        LocalDateTime today = LocalDateTime.now();
        Subject subject = new Subject("test-subject-id", 10, today.minusDays(1L), today.plusDays(2L), Status.ONGOING);
        Ticket ticket = new Ticket(subject, clientId);
        messageService.sendTicketMessage(ticket);
    }

}
