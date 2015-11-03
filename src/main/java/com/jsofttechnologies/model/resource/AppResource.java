package com.jsofttechnologies.model.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsofttechnologies.model.impl.AppImpl;

import java.util.Date;

/**
 * Created by Jerico on 04/11/2015.
 */
public class AppResource extends AppImpl {

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


