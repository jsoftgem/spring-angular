package com.jsofttechnologies.model.asm;

import com.jsofttechnologies.model.jpa.UserRoleJPA;
import com.jsofttechnologies.model.resource.UserRoleResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Jerico on 15/10/2015.
 */
public class UserRoleResourceASM extends ResourceAssemblerSupport<UserRoleJPA, UserRoleResource> {

    private Class controllerClass;

    public UserRoleResourceASM(Class controllerClass) {
        super(controllerClass, UserRoleResource.class);
        this.controllerClass = controllerClass;
    }

    @Override
    public UserRoleResource toResource(UserRoleJPA userRoleJPA) {
        UserRoleResource userRoleResource = new UserRoleResource();
        userRoleResource.setRole(userRoleJPA.getRole());
        userRoleResource.setActions(userRoleJPA.getActions());
        userRoleResource.setCreatedDt(userRoleJPA.getCreatedDt());
        return userRoleResource;
    }
}
