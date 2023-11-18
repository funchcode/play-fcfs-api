package io.github.funchcode.fcfs;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractContainerBaseTest {

    public static final DynamoDBMapper dynamoDBMapper;
    static final String REDIS_IMAGE = "redis:6-alpine";
    static final GenericContainer REDIS_CONTAINER;

    static {
        REDIS_CONTAINER = new GenericContainer<>(REDIS_IMAGE)
                .withExposedPorts(6379)
                .withReuse(true);
        REDIS_CONTAINER.start();
        dynamoDBMapper = new DynamoDBMapper(getAmazonDynamoDB(), getMapperConfig());
    }

    static DynamoDBMapperConfig getMapperConfig() {
        return DynamoDBMapperConfig.builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.CLOBBER)
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .build();
    }

    static AWSCredentialsProvider getAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials("key1", "key2"));
    }

    static AmazonDynamoDB getAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "ap-northeast-2"))
                .withCredentials(getAWSCredentialsProvider())
                .build();
    }

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        System.out.println("host :" + REDIS_CONTAINER.getHost());
        System.out.println("port :" + REDIS_CONTAINER.getMappedPort(6379));
        registry.add("spring.data.redis.host", REDIS_CONTAINER::getHost);
        registry.add("spring.data.redis.port", () -> ""+REDIS_CONTAINER.getMappedPort(6379));
    }

    public static CreateTableResult createTable(String tableName, String hashKeyName, String rangeKeyName) {
        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();
        attributeDefinitions.add(new AttributeDefinition(hashKeyName, ScalarAttributeType.S));
        attributeDefinitions.add(new AttributeDefinition(rangeKeyName, ScalarAttributeType.S));

        List<KeySchemaElement> ks = new ArrayList<>();
        ks.add(new KeySchemaElement(hashKeyName, KeyType.HASH));
        ks.add(new KeySchemaElement(rangeKeyName, KeyType.RANGE));

        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(1000L, 1000L);

        CreateTableRequest request = new CreateTableRequest()
                .withTableName(tableName)
                .withAttributeDefinitions(attributeDefinitions)
                .withKeySchema(ks)
                .withProvisionedThroughput(provisionedThroughput);

        return getAmazonDynamoDB().createTable(request);
    }

}
