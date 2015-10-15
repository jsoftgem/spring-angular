package com.jsofttechnologies.services.app;

import com.jsofttechnologies.model.jpa.UserRoleJPA;
import com.jsofttechnologies.services.util.PageServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by Jerico on 15/10/2015.
 */
@Service
public class UserRoleService extends PageServiceImpl<UserRoleJPA, Long> {

    public UserRoleService() {
        super(UserRoleJPA.class);
    }

}
