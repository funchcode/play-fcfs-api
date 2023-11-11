package io.github.funchcode.fcfs.core.db;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDateTime;
import java.util.Optional;

public final class DynamoDBLocalDateTimeConverter implements DynamoDBTypeConverter<String, LocalDateTime> {

    @Override
    public String convert(LocalDateTime date) {
        return date == null ? null : date.toString();
    }

    @Override
    public LocalDateTime unconvert(String value) {
        return value == null ? null : LocalDateTime.parse(value);
    }

}
