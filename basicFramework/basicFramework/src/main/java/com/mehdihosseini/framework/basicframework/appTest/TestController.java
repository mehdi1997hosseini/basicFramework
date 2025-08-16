package com.mehdihosseini.framework.basicframework.appTest;

import com.mehdihosseini.framework.basicframework.controller.BasicEntityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app-test/")
public class TestController extends BasicEntityController<TestEntity, String, TestService> {

    @Autowired
    protected TestController(TestService service) {
        super(service);
    }


}
