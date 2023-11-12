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

    @Test
    @DisplayName("수량을 0이하로 초기화할 수 없음")
    void newInstance_InvalidLimitedQuantity() {
        int zeroLimitedQuantity = 0;
        int minusLimitedQuantity = -1;
        int validLimitedQuantity = 1;
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime ongoingOpenDate = today.minusDays(1L);
        LocalDateTime ongoingDeadlineDate = today.plusDays(2L);
        Assertions.assertThrows(IllegalArgumentException.class, () -> Subject.newInstance(zeroLimitedQuantity, ongoingOpenDate, ongoingDeadlineDate));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Subject.newInstance(minusLimitedQuantity, ongoingOpenDate, ongoingDeadlineDate));
        Assertions.assertDoesNotThrow(() -> Subject.newInstance(validLimitedQuantity, ongoingOpenDate, ongoingDeadlineDate));
    }

    @Test
    @DisplayName("오픈 날짜는 마감 날짜 이후로 초기화할 수 없음")
    void newInstance_InvalidOpenDeadlineDateSet() {
        int validLimitedQuantity = 1;
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime afterFiveDays = today.plusDays(5L);
        LocalDateTime afterFourDays = today.plusDays(4L);
        Assertions.assertThrows(IllegalArgumentException.class, () -> Subject.newInstance(validLimitedQuantity, afterFiveDays, afterFourDays));
        Assertions.assertDoesNotThrow(() -> Subject.newInstance(validLimitedQuantity, afterFourDays, afterFiveDays));
    }

    @Test
    @DisplayName("마감 날짜는 과거로 초기화할 수 없음")
    void newInstance_InvalidPastDeadlineSet() {
        int validLimitedQuantity = 1;
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime beforeFiveDays = today.minusDays(5L);
        LocalDateTime beforeFourDays = today.minusDays(4L);
        Assertions.assertThrows(IllegalArgumentException.class, () -> Subject.newInstance(validLimitedQuantity, beforeFourDays, beforeFiveDays));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Subject.newInstance(validLimitedQuantity, beforeFiveDays, beforeFourDays));
    }

}
