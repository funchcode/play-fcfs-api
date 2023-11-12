package io.github.funchcode.fcfs.core.ticket;

import com.amazonaws.AmazonClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.funchcode.fcfs.core.mq.Message;
import io.github.funchcode.fcfs.core.mq.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public final class TicketMessageService {

    private final MessageProducer messageProducer;

    void sendTicketMessage(Ticket ticket) {
        String subjectId = ticket.subject().getId();
        try {
            messageProducer.produce(
                    new Message<>(subjectId, ticket.clientId(), new TicketMessage(subjectId, ticket.clientId()))
            );
        } catch (JsonProcessingException e) {
            log.error("티켓 메시지 마샬링 에러.");
            throw new IllegalArgumentException(e);
        } catch (Exception e) {
            log.error("아마존 서비스 에러.");
            throw new AmazonClientException(e);
        }
    }

}
