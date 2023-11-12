package io.github.funchcode.fcfs.core.subject.repo;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import io.github.funchcode.fcfs.core.db.DynamoDBLocalDateTimeConverter;
import io.github.funchcode.fcfs.core.subject.DynamoDBStatusConverter;
import io.github.funchcode.fcfs.core.subject.Status;
import io.github.funchcode.fcfs.core.subject.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = "fcfs_subject")
public class SubjectDao {

    @DynamoDBHashKey
    private String id;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter.class)
    private LocalDateTime openDate;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter.class)
    private LocalDateTime deadlineDate;

    @DynamoDBAttribute
    private int limitedQuantityOf;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = DynamoDBStatusConverter.class)
    private Status status;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter.class)
    private LocalDateTime updatedAt;

    public SubjectDao(Subject subject) {
        this.id = subject.getId();
        this.openDate = subject.getOpenDate();
        this.deadlineDate = subject.getDeadlineDate();
        this.limitedQuantityOf = subject.getLimitedQuantityOf();
        this.status = subject.getStatus();
    }

    public SubjectDao(String id, LocalDateTime openDate, LocalDateTime deadlineDate, int limitedQuantityOf, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.openDate = openDate;
        this.deadlineDate = deadlineDate;
        this.limitedQuantityOf = limitedQuantityOf;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Subject toDomain() {
        return new Subject(this.id, this.limitedQuantityOf, this.openDate, this.deadlineDate, this.status);
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
