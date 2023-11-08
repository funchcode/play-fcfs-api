package io.github.funchcode.fcfs.core.ticket;

import com.amazonaws.services.sqs.model.SendMessageResult;
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
        String subjectId = ticket.getSubject().getId();
        try {
            SendMessageResult messageResult = messageProducer.produce(
                    new Message<>(subjectId, ticket.getClientId(), new TicketMessage(subjectId, ticket.getClientId()))
            );
            // message 정상 여부 확인
        } catch (JsonProcessingException e) {
            log.error("티켓 메시지 마샬링 에러.");
            throw new RuntimeException(e);
        }
    }

}
