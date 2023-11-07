package io.github.funchcode.fcfs.core.ticket;

import io.github.funchcode.fcfs.core.subject.Subject;
import io.github.funchcode.fcfs.core.subject.TicketIssueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisException;
import org.springframework.stereotype.Service;

import javax.cache.CacheException;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class TicketRedisService {

    private final RedissonClient redissonClient;

    public void cachingTicket(Ticket ticket) {
        // LOCK 실행 (기준 subject id)
        Subject subject = ticket.getSubject();
        RLock rlock = redissonClient.getLock(subject.getId());
        try {
            // Lock 획득 요청
            boolean locked = rlock.tryLock(3, TimeUnit.SECONDS);
            if (!locked) {
                throw new RedisException("락 획득 Timeout 실패");
            }
            RSet<String> subjectRSet = redissonClient.getSet(String.format("Set-%s", subject.getId()));
            // REDIS 이미 등록되어 있는지 체크
            if (subjectRSet.contains(ticket.getClientId())) {
                // 이미 등록한 상태
                log.error("이미 등록된 요청자");
                throw new TicketIssueException("이미 등록된 요청자");
            }
            // REDIS 대상 여분 있는지 체크
            if (subject.getLimitedQuantityOf() <= subjectRSet.size()) {
                // 여분 없음 에러
                log.error("여분 티켓 없음");
                throw new TicketIssueException("여분 없음");
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
