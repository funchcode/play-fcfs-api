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
@DynamoDBTable(tableName = "fcfs-ticket")
public class TicketDao {

    private static final String PK_PREFIX = "subject#";
    private static final String PK_SUFFIX = "#ticket";
    private static final String SK_PREFIX = "client#";

    @DynamoDBHashKey(attributeName = "PK")
    private String pk;

    @DynamoDBAttribute(attributeName = "SK")
    private String sk;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    public TicketDao(Ticket ticket) {
        this.pk = toPk(ticket.subject().getId());
        this.sk = toSk(ticket.clientId());
    }

    public TicketDao(String pk, String sk, LocalDateTime createdAt) {
        this.pk = pk;
        this.sk = sk;
        this.createdAt = createdAt;
    }


    public static String toPk(String subjectId) {
        return String.format("%s%s%s", PK_PREFIX, subjectId, PK_SUFFIX);
    }

    public static String toSk(String clientId) {
        return String.format("%s%s", SK_PREFIX, clientId);
    }

    public String getSubjectId() {
        String subjectId = this.pk.replace(PK_PREFIX, "");
        subjectId = subjectId.replace(PK_SUFFIX, "");
        return subjectId;
    }

    public String getClientId() {
        return this.sk.replace(SK_PREFIX, "");
    }

    public Ticket toDomain(Subject subject) {
        return new Ticket(subject, getClientId());
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
