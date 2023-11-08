package io.github.funchcode.fcfs.core.config;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import io.github.funchcode.fcfs.core.common.AwsProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AwsConfig {

    private final AwsProperty awsProperty;

    @Bean(destroyMethod = "shutdown")
    public AmazonSQS amazonSQS() {
        return AmazonSQSClientBuilder.standard()
                .withRegion(awsProperty.getRegion())
                .build();
    }

}
