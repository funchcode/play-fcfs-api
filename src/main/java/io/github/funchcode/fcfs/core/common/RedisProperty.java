package io.github.funchcode.fcfs.core.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.data.redis")
public final class RedisProperty {

    private final String host;
    private final int port;

}
