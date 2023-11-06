package io.github.funchcode.fcfs.core.subject;

import io.github.funchcode.fcfs.core.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TicketService ticketService;

    public boolean acquireTicket(String subjectId) {
        String clientId = "client-010101";
        Subject subject = subjectRepository.findById(subjectId);
        // 취득
        return ticketService.register(subject.issueTicket(clientId));
    }

}
