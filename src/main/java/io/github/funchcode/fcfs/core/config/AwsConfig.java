package io.github.funchcode.fcfs.core.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import io.github.funchcode.fcfs.core.common.AwsProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    private final AwsProperty awsProperty;
    private final AWSCredentialsProvider awsCredentialsProvider;

    public AwsConfig(AwsProperty awsProperty) {
        this.awsProperty = awsProperty;
        this.awsCredentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsProperty.getCredentials().accessKey(), awsProperty.getCredentials().secretKey()));
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.CLOBBER)
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .build();
        return new DynamoDBMapper(amazonDynamoDB(), mapperConfig);
    }

    @Bean(destroyMethod = "shutdown")
    public AmazonSQS amazonSQS() {
        return AmazonSQSClientBuilder.standard()
                .withRegion(awsProperty.getRegion())
                .withCredentials(awsCredentialsProvider)
                .build();
    }

    @Bean(destroyMethod = "shutdown")
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsProperty.getRegion())
                .build();
    }

}
