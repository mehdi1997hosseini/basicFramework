package com.mehdihosseini.framework.basicframework.appTest;

import com.mehdihosseini.framework.basicframework.service.BasicEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends BasicEntityServiceImpl<TestEntity, String, TestRepository> implements TestService {

    @Autowired
    public TestServiceImpl(TestRepository repository) {
        super(TestEntity.class,repository);
    }

}
