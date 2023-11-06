package io.github.funchcode.fcfs.core.subject;

import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository {

    Subject findById(String id);

}
