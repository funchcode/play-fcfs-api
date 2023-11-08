package io.github.funchcode.fcfs.core.mq;

public record Message<T>(String groupId, String deduplicationId, T body) {

}
