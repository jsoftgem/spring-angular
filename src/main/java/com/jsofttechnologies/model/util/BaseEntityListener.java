package com.jsofttechnologies.model.util;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import javax.annotation.Resource;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by Jerico on 15/10/2015.
 */
public class BaseEntityListener {

    @PrePersist
    public void prePersist(Object o) {

        if (o instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) o;
            if (baseEntity.getCreatedDt() == null) {
                baseEntity.setCreatedDt(new Date());
            }
        }


    }

    @PreUpdate
    public void preUpdate(Object o) {
        if (o instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) o;
            baseEntity.setUpdatedDt(new Date());
        }
    }

}
