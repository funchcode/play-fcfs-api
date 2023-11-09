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

    @Getter
    @RequiredArgsConstructor
    public static final class Credentials {

        private final String accessKey;
        private final String secretKey;

    }

    @Getter
    @RequiredArgsConstructor
    public static final class Sqs {

        private final String name;
        private final String url;

    }

    @Getter
    @RequiredArgsConstructor
    public static final class Dynamodb {

        private final String endpoint;

    }

}
