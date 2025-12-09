package ir.mehdihosseini.basicframework.base.elasticsearch.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;

@Document(indexName = "request_response")
@Setter
@Getter
public class RequestElasticEntity {

    @Id
    @Field(type = FieldType.Auto)
    private String id;
    @Field(type = FieldType.Date_Nanos)
    private Instant createTimeDate;
    private String agentName;
    @Field(type = FieldType.Ip)
    private String ip;
    private String address;
    private String method;
    private String requestId;
    private String requestBody;
    private String responseBody;
    private StatusPerRequestType status;

}
