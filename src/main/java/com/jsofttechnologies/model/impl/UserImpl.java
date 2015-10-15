package com.jsofttechnologies.model.impl;

import com.jsofttechnologies.model.User;
import com.jsofttechnologies.model.UserRole;
import com.jsofttechnologies.model.asm.UserResourceASM;
import com.jsofttechnologies.model.util.EntityHelper;
import com.jsofttechnologies.model.util.ResourceAssembler;
import com.jsofttechnologies.web.rest.UserRest;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jerico on 15/10/2015.
 */
@ResourceAssembler(controllerClass = UserRest.class, resourceAssemblerSupport = UserResourceASM.class)
public class UserImpl extends ResourceSupport implements User {

    private Long ID;
    private Date createdDt;
    private Date updatedDt;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private List<UserRoleImpl> userRoles;

    @Override
    public Long getID() {
        return ID;
    }

    @Override
    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public Date getCreatedDt() {
        return createdDt;
    }

    @Override
    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    @Override
    public Date getUpdatedDt() {
        return updatedDt;
    }

    @Override
    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }


    @Override
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public List<UserRoleImpl> getUserRoles() {
        return userRoles;
    }

    @Override
    public void setUserRoles(List<? extends UserRole> userRoles) {
        this.userRoles = EntityHelper.convertListType(userRoles, UserRoleImpl.class);
    }
}
