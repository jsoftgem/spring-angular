package com.jsofttechnologies.model;

import com.jsofttechnologies.model.util.BaseEntity;

import java.util.List;

/**
 * Created by Jerico on 15/10/2015.
 */
public interface User extends BaseEntity<Long> {

    public String getUsername();

    public void setUsername(String username);

    public String getEmail();

    public void setEmail(String email);


    public String getPassword();

    public void setPassword(String password);


    public String getFullName();

    public void setFullName(String fullName);


    public List<? extends UserRole> getUserRoles();

    public void setUserRoles(List<? extends UserRole> userRoles);

}
