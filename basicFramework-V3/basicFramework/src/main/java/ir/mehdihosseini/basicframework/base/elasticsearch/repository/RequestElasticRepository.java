package ir.mehdihosseini.basicframework.base.elasticsearch.repository;

import ir.mehdihosseini.basicframework.base.elasticsearch.entity.RequestElasticEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestElasticRepository extends ElasticsearchRepository<RequestElasticEntity, String> {

}
