package io.github.funchcode.fcfs.core.db;

import io.github.funchcode.fcfs.core.subject.Subject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisException;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class LockAndExecuteRedisService {

    private final RedissonClient redissonClient;

    public <T> void execute(String name, T domain, Executable<T> executable) {
        RLock rlock = redissonClient.getLock(name);
        try {
            boolean locked = rlock.tryLock(3, TimeUnit.SECONDS);
            if (!locked) {
                throw new RedisException("락 획득 Timeout 실패");
            }
            executable.execute(domain);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            rlock.unlock();
        }
    }

}
