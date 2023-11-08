package io.github.funchcode.fcfs.core.mq;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.funchcode.fcfs.core.common.AwsProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AmazonSqsMessageProducer implements MessageProducer {

    private final ObjectMapper objectMapper;
    private final AwsProperty awsProperty;
    private final AmazonSQS amazonSQS;

    @Override
    public <T> SendMessageResult produce(Message<T> message) throws JsonProcessingException {
        return amazonSQS.sendMessage(
                new SendMessageRequest(
                        awsProperty.getSqs().getUrl(),
                        objectMapper.writeValueAsString(message.body())
                ).withMessageGroupId(message.groupId()).withMessageDeduplicationId(message.deduplicationId())
        );
    }

}
