package io.github.funchcode.fcfs.core.ticket;

import io.github.funchcode.fcfs.core.mq.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public final class TicketService {

    private final StringRedisTemplate redisTemplate;
    private final MessageProducer messageProducer;

    public boolean register(Ticket ticket) {
        ticketing(ticket);
        // * SQS Ticket 등록 요청 위에서 exception 발생 시 타면 안된다.
        messageProducer.produce();
        return true;
    }

    // 트랜잭션 처리 필요
    public boolean ticketing(Ticket ticket) {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        // REDIS 이미 등록되어 있는지 체크
        // REDIS 대상 여분 있는지 체크
        // REDIS 저장
        return true;
    }

}
