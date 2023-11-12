package io.github.funchcode.fcfs.core.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "cloud.aws")
public final class AwsProperty {

    private final Credentials credentials;
    private final String region;
    private final Sqs sqs;
    private final Dynamodb dynamodb;

    public record Credentials(String accessKey, String secretKey) {

    }

    public record Sqs(String name, String url) {

    }

    public record Dynamodb(String endpoint) {
    }

}
