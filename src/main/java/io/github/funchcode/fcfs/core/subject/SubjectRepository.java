package io.github.funchcode.fcfs.core.subject;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository {

    Subject save(Subject subject);
    Optional<Subject> findById(String id);

}
