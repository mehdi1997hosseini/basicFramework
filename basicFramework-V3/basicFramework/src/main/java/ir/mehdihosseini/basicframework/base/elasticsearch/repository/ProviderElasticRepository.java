package ir.mehdihosseini.basicframework.base.elasticsearch.repository;

import ir.mehdihosseini.basicframework.base.elasticsearch.entity.ProviderElasticEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "manager.elastic-search.trace.third-party", name = "isEnable", havingValue = "true")
public interface ProviderElasticRepository extends ElasticsearchRepository<ProviderElasticEntity, String> {
}
