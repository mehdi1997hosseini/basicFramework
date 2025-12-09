package ir.mehdihosseini.basicframework.base.elasticsearch.repository;

import ir.mehdihosseini.basicframework.base.elasticsearch.entity.ProviderElasticEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderElasticRepository extends ElasticsearchRepository<ProviderElasticEntity, String> {
}
