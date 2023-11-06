package io.github.funchcode.fcfs.core.subject;

import io.github.funchcode.fcfs.core.ticket.Ticket;
import io.github.funchcode.fcfs.core.ticket.TicketRepository;
import io.github.funchcode.fcfs.core.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TicketRepository ticketRepository;
    private final TicketService ticketService;

    public boolean acquireTicket(String subjectId, String clientId) {
        SubjectTickets subjectTickets = new SubjectTickets(
                subjectRepository.findById(subjectId),
                new HashSet<>(ticketRepository.findAllBySubject_Id(subjectId))
        );
        // 취득
        return ticketService.register(subjectTickets.issueTicket(clientId));
    }

}
