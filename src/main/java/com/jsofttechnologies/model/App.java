package com.jsofttechnologies.model;

import com.jsofttechnologies.model.util.BaseEntity;

/**
 * Created by Jerico on 04/11/2015.
 */
public interface App extends BaseEntity<Long> {


    public String getDescription();

    public void setDescription(String description);

    public String getIconUri();

    public void setIconUri(String iconUri);

    public String getName();

    public void setName(String name);

    public String getTitle();

    public void setTitle(String title);

    public Integer getIndex();

    public void setIndex(Integer index);
}

