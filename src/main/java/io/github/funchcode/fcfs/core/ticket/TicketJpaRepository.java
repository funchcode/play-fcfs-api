package io.github.funchcode.fcfs.core.ticket;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketJpaRepository implements TicketRepository {

    @Override
    public List<Ticket> findAllBySubject_Id(String subjectId) {
        // 임시
        return null;
    }
}
