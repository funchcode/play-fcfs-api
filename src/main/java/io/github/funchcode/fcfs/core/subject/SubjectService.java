package io.github.funchcode.fcfs.core.subject;

import io.github.funchcode.fcfs.core.ticket.TicketRepository;
import io.github.funchcode.fcfs.core.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TicketRepository ticketRepository;
    private final TicketService ticketService;

    public Subject register(int limitedQuantityOf, LocalDateTime openDate, LocalDateTime deadlineDate) {
        return subjectRepository.save(Subject.newInstance(limitedQuantityOf, openDate, deadlineDate));
    }

    public boolean acquireTicket(String subjectId, String clientId) {
        SubjectTickets subjectTickets = new SubjectTickets(
                subjectRepository.findById(subjectId).orElseThrow(() -> new IllegalArgumentException("등록된 SUBJECT가 없음.")),
                new HashSet<>(ticketRepository.findAllBySubject_Id(subjectId))
        );
        return ticketService.register(subjectTickets.issueTicket(clientId));
    }

}
