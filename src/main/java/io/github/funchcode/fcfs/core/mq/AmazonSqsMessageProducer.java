package io.github.funchcode.fcfs.core.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AmazonSqsMessageProducer implements MessageProducer {

    @Override
    public void produce() {
        log.debug("Send Message...");
    }

}
