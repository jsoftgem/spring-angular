package com.jsofttechnologies.model.impl;

import com.jsofttechnologies.model.UserRole;
import com.jsofttechnologies.model.asm.UserResourceASM;
import com.jsofttechnologies.model.asm.UserRoleResourceASM;
import com.jsofttechnologies.model.util.ResourceAssembler;
import com.jsofttechnologies.model.util.RestAction;
import com.jsofttechnologies.web.rest.UserRest;
import com.jsofttechnologies.web.rest.UserRoleRest;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import java.util.List;

/**
 * Created by Jerico on 15/10/2015.
 */
@ResourceAssembler(controllerClass = UserRoleRest.class, resourceAssemblerSupport = UserRoleResourceASM.class)
public class UserRoleImpl extends ResourceSupport implements UserRole {

    private Long ID;
    private String role;
    private List<RestAction> actions;
    private Date createdDt;
    private Date updatedDt;

    @Override
    public Long getID() {
        return ID;
    }

    @Override
    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public List<RestAction> getActions() {
        return actions;
    }

    @Override
    public void setActions(List<RestAction> actions) {
        this.actions = actions;
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
}
