package com.jsofttechnologies.services;

import com.jsofttechnologies.services.app.resource.UserRoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Jerico on 15/10/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class UserRoleServiceTest {

    @Autowired
    private UserRoleService userRoleService;

    @Before
    public void setup() {

    }

    @Test
    public void testCreate() {

    }
}



