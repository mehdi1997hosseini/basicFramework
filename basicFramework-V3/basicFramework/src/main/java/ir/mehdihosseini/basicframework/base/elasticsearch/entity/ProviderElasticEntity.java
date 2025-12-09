package ir.mehdihosseini.basicframework.base.elasticsearch.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;

@Document(indexName = "provider_service")
@Setter
@Getter
public class ProviderElasticEntity {

    @Id
    private String id;
    @Field(type = FieldType.Date)
    private Instant createTimeDate;
    private String method;
    private String address;
    private String requestBody;
    private String responseBody;
    private StatusPerRequestType status;
    @Field(type = FieldType.Ip)
    private String hostIp;

}
