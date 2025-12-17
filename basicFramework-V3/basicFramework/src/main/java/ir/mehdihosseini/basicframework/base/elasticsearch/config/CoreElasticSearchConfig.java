package ir.mehdihosseini.basicframework.base.elasticsearch.config;

import ir.mehdihosseini.basicframework.base.config.properties.ManagerPropertiesConfig;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.net.ssl.SSLContext;

@Configuration
@EnableElasticsearchRepositories(basePackages = "ir.mehdihosseini.basicframework.base.elasticsearch")
public class CoreElasticSearchConfig extends ElasticsearchConfiguration {

    private final ManagerPropertiesConfig managerPropertiesConfig;

    public CoreElasticSearchConfig(ManagerPropertiesConfig elkConfig) {
        this.managerPropertiesConfig = elkConfig;
    }

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(managerPropertiesConfig.getElasticSearch().getHost() + ":" + managerPropertiesConfig.getElasticSearch().getPort())
                .withSocketTimeout(3000000)
                .withConnectTimeout(managerPropertiesConfig.getElasticSearch().getConnectionTimeOut())
                .build();
    }


    public static SSLContext buildSslContext() {
        try {
            return new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
