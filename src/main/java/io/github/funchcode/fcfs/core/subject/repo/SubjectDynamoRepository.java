package io.github.funchcode.fcfs.core.subject.repo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import io.github.funchcode.fcfs.core.db.TicketDao;
import io.github.funchcode.fcfs.core.subject.Subject;
import io.github.funchcode.fcfs.core.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class SubjectDynamoRepository implements SubjectRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public Subject save(Subject subject) {
        SubjectDao dao = new SubjectDao(subject);
        if (findById(subject.getId()).isPresent()) {
            dao.setUpdatedAt(LocalDateTime.now());
        } else {
            dao.setCreatedAt(LocalDateTime.now());
        }
        dynamoDBMapper.save(dao);
        return subject;
    }

    public Subject saveTest(Subject subject) {
        TicketDao dao = new TicketDao();
        dao.setPk("subject#" + UUID.randomUUID());
        dao.setSk("info");
        dao.setLimitedQuantityOf(0);
        dynamoDBMapper.save(dao);
        return subject;
    }

    @Override
    public Optional<Subject> findById(String id) {
        SubjectDao subjectDao = dynamoDBMapper.load(SubjectDao.class, id);
        if (subjectDao == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(subjectDao.toDomain());
    }

}
