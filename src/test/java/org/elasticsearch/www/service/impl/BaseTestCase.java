package org.elasticsearch.www.service.impl;
import org.elasticsearch.www.service.UserService;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;

/**
 * Created by phoenix on 12/8/2014.
 */
@ContextConfiguration(locations = {
        "classpath*:/spring/spring.xml"
})
public class BaseTestCase extends AbstractJUnit4SpringContextTests {
    @Resource
    protected UserService userService;
}
