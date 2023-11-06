package io.github.funchcode.fcfs.core.ticket;

import io.github.funchcode.fcfs.core.mq.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public final class TicketService {

    private final StringRedisTemplate redisTemplate;
    private final RedissonClient redissonClient;
    private final MessageProducer messageProducer;

    public boolean register(Ticket ticket) {
        ticketing(ticket);
        // * SQS Ticket 등록 요청 위에서 exception 발생 시 타면 안된다.
        messageProducer.produce();
        return true;
    }

    // 트랜잭션 처리 필요
    public boolean ticketing(Ticket ticket) {
        // LOCK 실행 (기준 subject id)
        RLock rlock = redissonClient.getLock(ticket.getSubjectId());
        try {
            // Lock 획득 요청
            boolean locked = rlock.tryLock(3, TimeUnit.SECONDS);
            if (!locked) {
                // 락 획득 실패 Throws
            }

            // REDIS 이미 등록되어 있는지 체크
            // REDIS 대상 여분 있는지 체크
            // REDIS 저장

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // Lock 해제
            rlock.unlock();
        }
        return true;
    }

}
