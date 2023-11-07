package io.github.funchcode.fcfs.core.subject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class SubjectTest {

    @Test
    @DisplayName("발급 가능 상태 확인 (기간 정책)")
    void checkIssueable_InvalidPeriod() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime pastOpenDate = today.minusDays(7L);
        LocalDateTime pastDeadlineDate = today.minusDays(1L);
        LocalDateTime willOpenDate = today.plusDays(1L);
        LocalDateTime willDeadlineDate = today.plusDays(2L);
        LocalDateTime ongoingOpenDate = today.minusDays(1L);
        LocalDateTime ongoingDeadlineDate = today.plusDays(2L);

        Assertions.assertThrows(TicketIssueException.class, () -> new Subject("test-id", 10, pastOpenDate, pastDeadlineDate, Status.ONGOING).checkIssueable());
        Assertions.assertThrows(TicketIssueException.class, () -> new Subject("test-id", 10, willOpenDate, willDeadlineDate, Status.ONGOING).checkIssueable());
        Assertions.assertDoesNotThrow(() -> new Subject("test-id", 10, ongoingOpenDate, ongoingDeadlineDate, Status.ONGOING).checkIssueable());
    }

    @Test
    @DisplayName("발급 가능 상태 확인 (상태 정책)")
    void checkIssueable_InvalidStatus() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime ongoingOpenDate = today.minusDays(1L);
        LocalDateTime ongoingDeadlineDate = today.plusDays(2L);
        Assertions.assertThrows(TicketIssueException.class, () -> new Subject("test-id", 10, ongoingOpenDate, ongoingDeadlineDate, Status.WAIT).checkIssueable());
        Assertions.assertThrows(TicketIssueException.class, () -> new Subject("test-id", 10, ongoingOpenDate, ongoingDeadlineDate, Status.END).checkIssueable());
        Assertions.assertThrows(TicketIssueException.class, () -> new Subject("test-id", 10, ongoingOpenDate, ongoingDeadlineDate, Status.CANCEL).checkIssueable());
        Assertions.assertDoesNotThrow(() -> new Subject("test-id", 10, ongoingOpenDate, ongoingDeadlineDate, Status.ONGOING).checkIssueable());
    }

}
