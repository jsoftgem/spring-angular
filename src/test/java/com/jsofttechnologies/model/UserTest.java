package com.jsofttechnologies.model;

import com.jsofttechnologies.model.impl.UserImpl;
import com.jsofttechnologies.model.impl.UserRoleImpl;
import com.jsofttechnologies.model.jpa.UserJPA;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jerico on 15/10/2015.
 */
public class UserTest {

    @Test
    public void checkDeclaredClass() throws NoSuchFieldException {

        Method[] methods = UserJPA.class.getDeclaredMethods();

        System.out.println("\n");

    }

    @Test
    public void testUserRoleConversion() {
        List<UserRoleImpl> userRoleList = new ArrayList<>();

        UserRoleImpl userRole = new UserRoleImpl();


        userRole.setID(1L);
        userRole.setCreatedDt(new Date());
        userRole.setRole("Sample");

        userRoleList.add(userRole);


        UserImpl user = new UserImpl();

        user.setUserRoles(userRoleList);

        assert (user.getUserRoles() != null);

    }

}
