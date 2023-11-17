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
@DynamoDBTable(tableName = "fcfs-subject")
public class SubjectDao {

    static final String PK_PREFIX = "subject#";
    private final String SK_INFO = "info";

    @DynamoDBHashKey(attributeName = "PK")
    private String pk;

    @DynamoDBHashKey(attributeName = "SK")
    private final String sk = SK_INFO;

    @DynamoDBAttribute(attributeName = "openDate")
    @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter.class)
    private LocalDateTime openDate;

    @DynamoDBAttribute(attributeName = "deadlineDate")
    @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter.class)
    private LocalDateTime deadlineDate;

    @DynamoDBAttribute(attributeName = "limitedQuantityOf")
    private int limitedQuantityOf;

    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTypeConverted(converter = DynamoDBStatusConverter.class)
    private Status status;

    @DynamoDBAttribute(attributeName = "createdAt")
    @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @DynamoDBAttribute(attributeName = "updatedAt")
    @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter.class)
    private LocalDateTime updatedAt;

    public SubjectDao(Subject subject) {
        this.pk = toPk(subject.getId());
        this.openDate = subject.getOpenDate();
        this.deadlineDate = subject.getDeadlineDate();
        this.limitedQuantityOf = subject.getLimitedQuantityOf();
        this.status = subject.getStatus();
    }

    public SubjectDao(String pk, LocalDateTime openDate, LocalDateTime deadlineDate, int limitedQuantityOf, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.pk = pk;
        this.openDate = openDate;
        this.deadlineDate = deadlineDate;
        this.limitedQuantityOf = limitedQuantityOf;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Subject toDomain() {
        return new Subject(getId(), this.limitedQuantityOf, this.openDate, this.deadlineDate, this.status);
    }

    public String getId() {
        return this.pk.replace(PK_PREFIX, "");
    }

    public static String toPk(String id) {
        return String.format("%s%s", PK_PREFIX, id);
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
