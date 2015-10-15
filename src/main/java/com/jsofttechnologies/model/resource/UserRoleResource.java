package com.jsofttechnologies.model.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsofttechnologies.model.impl.UserRoleImpl;
import com.jsofttechnologies.model.util.RestAction;

import java.util.Date;
import java.util.List;

/**
 * Created by Jerico on 15/10/2015.
 */
public class UserRoleResource extends UserRoleImpl {

    @JsonIgnore
    @Override
    public List<RestAction> getActions() {
        return super.getActions();
    }

    @JsonIgnore
    @Override
    public Date getUpdatedDt() {
        return super.getUpdatedDt();
    }
}
