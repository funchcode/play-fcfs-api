package io.github.funchcode.fcfs.core.ticket;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository {

    List<Ticket> findAllBySubject_Id(String subjectId);

}
