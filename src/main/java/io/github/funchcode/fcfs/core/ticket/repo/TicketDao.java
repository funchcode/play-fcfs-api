package io.github.funchcode.fcfs.core.ticket.repo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import io.github.funchcode.fcfs.core.db.DynamoDBLocalDateTimeConverter;
import io.github.funchcode.fcfs.core.subject.Subject;
import io.github.funchcode.fcfs.core.subject.SubjectRepository;
import io.github.funchcode.fcfs.core.subject.repo.SubjectDao;
import io.github.funchcode.fcfs.core.ticket.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Deprecated // TODO DynamoDB 데이터 모델링 다시 진행해야 함
@NoArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = "fcfs_ticket")
public class TicketDao {

    @DynamoDBHashKey
    private String id;

    @DynamoDBAttribute
    private String clientId;

    @DynamoDBAttribute
    private String subjectId;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    public TicketDao(Ticket ticket) {
        this.id = ticket.id();
        this.clientId = ticket.clientId();
        this.subjectId = ticket.subject().getId();
    }

    public TicketDao(String id, String clientId, String subjectId, LocalDateTime createdAt) {
        this.id = id;
        this.clientId = clientId;
        this.subjectId = subjectId;
        this.createdAt = createdAt;
    }

    public Ticket toDomain(Subject subject) {
        return new Ticket(id, subject, clientId);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
