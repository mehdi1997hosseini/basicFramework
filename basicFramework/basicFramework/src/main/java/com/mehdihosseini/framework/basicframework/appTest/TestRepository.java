package com.mehdihosseini.framework.basicframework.appTest;

import com.mehdihosseini.framework.basicframework.repository.BasicRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends BasicRepository<TestEntity, String> {
}
