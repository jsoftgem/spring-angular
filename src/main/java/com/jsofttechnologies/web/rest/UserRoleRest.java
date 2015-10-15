package com.jsofttechnologies.web.rest;

import com.jsofttechnologies.model.asm.UserRoleResourceASM;
import com.jsofttechnologies.model.jpa.UserRoleJPA;
import com.jsofttechnologies.model.resource.UserRoleResource;
import com.jsofttechnologies.services.app.UserRoleService;
import com.jsofttechnologies.web.util.PageResourceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jerico on 15/10/2015.
 */
@RestController
@RequestMapping("services/user/role")
public class UserRoleRest extends PageResourceImpl<UserRoleJPA, UserRoleResource, Long> {

    public UserRoleRest() {
        super(UserRoleJPA.class, UserRoleResourceASM.class, UserRoleService.class);
    }
}
