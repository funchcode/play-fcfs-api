package io.github.funchcode.fcfs.core.ticket;

import io.github.funchcode.fcfs.core.subject.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository {

    List<Ticket> findAllBySubjectId(String subjectId);
    Ticket save(Ticket ticket);
    Optional<Ticket> findBySubjectIdAndClientId(String subjectId, String clientId);

}
