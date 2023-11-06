package io.github.funchcode.fcfs.core.ticket;

import io.github.funchcode.fcfs.core.subject.Subject;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class TicketRedisService {

    private final RedissonClient redissonClient;

    // 트랜잭션 처리 필요
    public void cachingTicket(Ticket ticket) {
        // LOCK 실행 (기준 subject id)
        Subject subject = ticket.getSubject();
        RLock rlock = redissonClient.getLock(subject.getId());
        try {
            // Lock 획득 요청
            boolean locked = rlock.tryLock(3, TimeUnit.SECONDS);
            if (!locked) {
                // 락 획득 실패 Throws
            }
            RSet<String> subjectRSet = redissonClient.getSet(subject.getId());
            // REDIS 이미 등록되어 있는지 체크
            if (subjectRSet.contains(ticket.getClientId())) {
                // 이미 등록한 상태
            }
            // REDIS 대상 여분 있는지 체크
            if (subject.getLimitedQuantityOf() <= subjectRSet.size()) {
                // 여분 없음 에러
            }
            // REDIS 저장
            subjectRSet.add(ticket.getClientId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // Lock 해제
            rlock.unlock();
        }
    }

}
