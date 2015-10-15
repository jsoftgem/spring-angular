package com.jsofttechnologies.model.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsofttechnologies.model.impl.UserImpl;

import java.util.Date;

/**
 * Created by Jerico on 15/10/2015.
 */
public class UserResource extends UserImpl {

    @Override
    @JsonIgnore
    public String getPassword() {
        return super.getPassword();
    }

    @JsonIgnore
    @Override
    public Date getCreatedDt() {
        return super.getCreatedDt();
    }

    @JsonIgnore
    @Override
    public Date getUpdatedDt() {
        return super.getUpdatedDt();
    }
}
