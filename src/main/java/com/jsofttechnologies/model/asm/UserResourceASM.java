package com.jsofttechnologies.model.asm;

import com.jsofttechnologies.model.User;
import com.jsofttechnologies.model.jpa.UserJPA;
import com.jsofttechnologies.model.resource.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by Jerico on 15/10/2015.
 */
public class UserResourceASM extends ResourceAssemblerSupport<UserJPA, UserResource> {

    private Class controllerClass;


    public UserResourceASM(Class controllerClass) {
        super(controllerClass, UserResource.class);
        this.controllerClass = controllerClass;
    }

    @Override
    public UserResource toResource(UserJPA user) {
        UserResource userResource = new UserResource();
        userResource.setID(user.getID());
        userResource.setUsername(user.getUsername());
        userResource.setEmail(user.getEmail());
        userResource.setFullName(user.getFullName());
        userResource.setUserRoles(user.getUserRoles());
        Link selfLink = linkTo(controllerClass).slash(user.getID()).withSelfRel();
        userResource.add(selfLink);
        return userResource;
    }
}
