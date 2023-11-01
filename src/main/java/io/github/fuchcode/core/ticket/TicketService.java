package io.github.fuchcode.core.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public final class TicketService {

    private final StringRedisTemplate redisTemplate;

    public boolean ticketing(String subjectId) {
        SetOperations<String, String> stringStringSetOperations = redisTemplate.opsForSet();
        stringStringSetOperations.size(subjectId);
        stringStringSetOperations.add(subjectId, "a");
        stringStringSetOperations.add(subjectId, "b");
        stringStringSetOperations.add(subjectId, "c");
        return true;
    }

}
