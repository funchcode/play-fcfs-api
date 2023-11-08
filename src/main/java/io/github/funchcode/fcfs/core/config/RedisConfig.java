package io.github.funchcode.fcfs.core.config;

import io.github.funchcode.fcfs.core.common.RedisProperty;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@RequiredArgsConstructor
@EnableRedisRepositories
public class RedisConfig {

    private static final String REDISSON_HOST_PREFIX = "redis://";
    private final RedisProperty redisProperty;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(String.format("%s%s:%s", REDISSON_HOST_PREFIX, redisProperty.getHost(), redisProperty.getPort()));
        return Redisson.create(config);
    }

}
