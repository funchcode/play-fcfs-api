package io.github.funchcode.fcfs.core.ticket;

import io.github.funchcode.fcfs.core.mq.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public final class TicketService {

    private final TicketRedisService redisService;
    private final MessageProducer messageProducer;

    public boolean register(Ticket ticket) {
        redisService.cachingTicket(ticket);
        // * SQS Ticket 등록 요청 위에서 exception 발생 시 타면 안된다.
        messageProducer.produce();
        return true;
    }

}
