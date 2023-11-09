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
        try {
            messageService.sendTicketMessage(ticket);
            return true;
        } catch (Exception e) {
            redisService.removeCache(ticket);
            throw e;
        }
    }

}
