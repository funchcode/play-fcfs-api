package io.github.funchcode.fcfs.core.db;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = "fcfs-ticket")
public class TicketDao {

    @DynamoDBHashKey(attributeName = "PK")
    private String pk;

    @DynamoDBAttribute(attributeName = "SK")
    private String sk;

    @DynamoDBAttribute
    private int limitedQuantityOf;

}
