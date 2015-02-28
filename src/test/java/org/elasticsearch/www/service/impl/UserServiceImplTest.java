package org.elasticsearch.www.service.impl;

import junit.framework.TestCase;
import org.junit.Test;

public class UserServiceImplTest extends BaseTestCase {

    @Test
    public void testPutUserInfo() throws Exception {
        userService.putUserInfo();
   }
}