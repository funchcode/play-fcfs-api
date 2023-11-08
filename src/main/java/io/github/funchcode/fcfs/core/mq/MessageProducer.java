package io.github.funchcode.fcfs.core.mq;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageProducer {

    <T> SendMessageResult produce(Message<T> message) throws JsonProcessingException;

}
