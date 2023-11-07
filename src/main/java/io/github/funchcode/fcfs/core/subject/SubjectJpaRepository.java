package io.github.funchcode.fcfs.core.subject;

import org.springframework.stereotype.Repository;

@Repository
public class SubjectJpaRepository implements SubjectRepository {

    @Override
    public Subject findById(String id) {
        // 임시
        return null;
    }
}
