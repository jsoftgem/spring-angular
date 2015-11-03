package com.jsofttechnologies.model.impl;

import com.jsofttechnologies.model.App;
import com.jsofttechnologies.model.asm.AppResourceASM;
import com.jsofttechnologies.model.util.ResourceAssembler;
import com.jsofttechnologies.web.rest.AppRest;
import org.springframework.hateoas.ResourceSupport;
import java.util.Date;

@ResourceAssembler(controllerClass = AppRest.class, resourceAssemblerSupport = AppResourceASM.class)
public class AppImpl extends ResourceSupport implements App {

    private Long ID;
    private Date createdDt;
    private Date updatedDt;
    private String description;
    private String iconUri;
    private String name;
    private String title;
    private Integer index;


    @Override
    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public Long getID() {
        return ID;
    }

    @Override
    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    @Override
    public Date getCreatedDt() {
        return createdDt;
    }

    @Override
    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    @Override
    public Date getUpdatedDt() {
        return updatedDt;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    @Override
    public String getIconUri() {
        return iconUri;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }


    @Override
    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public Integer getIndex() {
        return index;
    }
}
