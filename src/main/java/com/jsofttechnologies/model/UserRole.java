package com.jsofttechnologies.model;

import com.jsofttechnologies.model.util.BaseEntity;
import com.jsofttechnologies.model.util.RestAction;

import java.util.List;

/**
 * Created by Jerico on 15/10/2015.
 */
public interface UserRole extends BaseEntity<Long> {

    public String getRole();

    public void setRole(String role);

    public List<RestAction> getActions();

    public void setActions(List<RestAction> actions);

}
