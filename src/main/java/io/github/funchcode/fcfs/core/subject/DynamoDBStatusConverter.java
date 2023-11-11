package io.github.funchcode.fcfs.core.subject;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDateTime;

public final class DynamoDBStatusConverter implements DynamoDBTypeConverter<String, Status> {

    @Override
    public String convert(Status status) {
        return status == null ? null : status.name();
    }

    @Override
    public Status unconvert(String value) {
        return value == null ? null : Status.valueOf(value);
    }

}
