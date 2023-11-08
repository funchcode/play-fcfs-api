package io.github.funchcode.fcfs.core.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public final class TicketService {

    private final TicketRedisService redisService;
    private final TicketMessageService messageService;

    public boolean register(Ticket ticket) {
        redisService.cachingTicket(ticket);
        messageService.sendTicketMessage(ticket);
        // caching에는 성공했지만 reducer에서 실패한 경우 데이터 동기화(NoSQL <> REDIS) 필요
        return true;
    }

}
