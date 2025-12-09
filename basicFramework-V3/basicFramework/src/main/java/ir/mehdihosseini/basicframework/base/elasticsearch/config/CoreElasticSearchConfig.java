package ir.mehdihosseini.basicframework.base.elasticsearch.config;

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

    private final ElasticSearchPropertyConfig elkConfig;

    public CoreElasticSearchConfig(ElasticSearchPropertyConfig elkConfig) {
        this.elkConfig = elkConfig;
    }

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(elkConfig.getHost() + ":" + elkConfig.getPort())
                .withSocketTimeout(3000000)
                .withConnectTimeout(elkConfig.getConnectionTimeOut())
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
