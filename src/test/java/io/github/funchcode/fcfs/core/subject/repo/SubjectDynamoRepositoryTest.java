package io.github.funchcode.fcfs.core.subject.repo;

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
public class SubjectDynamoRepositoryTest {

    @Autowired
    private SubjectDynamoRepository repository;

    @Test
    @DisplayName("Dynamo 연동 테스트 - 저장")
    void save_Subject() {
        LocalDateTime today = LocalDateTime.now();
        Subject subject = new Subject("test-subject-id", 10, today.minusDays(1L), today.plusDays(2L), Status.ONGOING);
        repository.save(subject);
    }

    @Test
    @DisplayName("Dynamo 연동 테스트 - 저장")
    void saveTest() {
        LocalDateTime today = LocalDateTime.now();
        Subject subject = new Subject("test-subject-id", 10, today.minusDays(1L), today.plusDays(2L), Status.ONGOING);
        repository.saveTest(subject);
    }

}
