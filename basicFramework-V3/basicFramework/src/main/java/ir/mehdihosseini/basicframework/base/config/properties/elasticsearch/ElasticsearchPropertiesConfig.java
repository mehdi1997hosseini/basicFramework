package ir.mehdihosseini.basicframework.base.config.properties.elasticsearch;

import lombok.Data;

@Data
public class ElasticsearchPropertiesConfig {

    private String host = "localhost";
    private Integer port = 9200;
    private String username;
    private String password;
    private String token;
    private Integer connectionTimeOut = 3000000;
    private TraceRequestPropertiesConfig trace;

}
