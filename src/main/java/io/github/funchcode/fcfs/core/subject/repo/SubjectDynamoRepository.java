package io.github.funchcode.fcfs.core.subject.repo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import io.github.funchcode.fcfs.core.subject.Subject;
import io.github.funchcode.fcfs.core.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Override
    public Optional<Subject> findById(String id) {
        SubjectDao subjectDao = dynamoDBMapper.load(SubjectDao.class, id);
        if (subjectDao == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(subjectDao.toDomain());
    }

}
