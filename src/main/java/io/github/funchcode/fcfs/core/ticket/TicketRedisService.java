package io.github.funchcode.fcfs.core.ticket;

import io.github.funchcode.fcfs.core.db.LockAndExecuteRedisService;
import io.github.funchcode.fcfs.core.subject.Subject;
import io.github.funchcode.fcfs.core.subject.TicketIssueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TicketRedisService {

    private final LockAndExecuteRedisService lockAndExecuteRedisService;
    private final RedissonClient redissonClient;

    public void cachingTicket(Ticket ticket) {
        Subject subject = ticket.getSubject();
        lockAndExecuteRedisService.execute(subject.getId(), ticket, t -> {
            RSet<String> subjectRSet = redissonClient.getSet(String.format("Set-%s", subject.getId()));
            if (subjectRSet.contains(t.getClientId())) {
                log.error("이미 등록된 요청자");
                throw new TicketIssueException("이미 등록된 요청자");
            }
            if (subject.getLimitedQuantityOf() <= subjectRSet.size()) {
                log.error("여분 티켓 없음");
                throw new TicketIssueException("여분 없음");
            }
            subjectRSet.add(t.getClientId());
        });
    }

    public void removeCache(Ticket ticket) {
        Subject subject = ticket.getSubject();
        lockAndExecuteRedisService.execute(subject.getId(), ticket, t -> {
            RSet<String> subjectRSet = redissonClient.getSet(String.format("Set-%s", subject.getId()));
            subjectRSet.remove(ticket.getClientId());
        });
    }

}
