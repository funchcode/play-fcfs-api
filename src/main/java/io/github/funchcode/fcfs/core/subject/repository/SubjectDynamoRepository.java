package io.github.funchcode.fcfs.core.subject.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import io.github.funchcode.fcfs.core.subject.Subject;
import io.github.funchcode.fcfs.core.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
public class SubjectDynamoRepository implements SubjectRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public Subject save(Subject subject) {
        SubjectDao dao = new SubjectDao(subject);
        // FINDBYID
        if (findById(subject.getId()) != null) {
            dao.setUpdatedAt(LocalDateTime.now());
        } else {
            dao.setCreatedAt(LocalDateTime.now());
        }
        // NEW OR UPDATE
        dynamoDBMapper.save(new SubjectDao(subject));
        return subject;
    }

    @Override
    public Subject findById(String id) {
        // 임시
        return null;
    }

}
