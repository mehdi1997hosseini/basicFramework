package ir.mehdihosseini.basicframework.base.elasticsearch.repository;

import ir.mehdihosseini.basicframework.base.elasticsearch.entity.RequestElasticEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "elt.manager.config" , name = "is-active" ,havingValue = "true")
public interface RequestElasticRepository extends ElasticsearchRepository<RequestElasticEntity, String> {

}
