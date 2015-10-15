package com.jsofttechnologies.model.util;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jerico on 15/10/2015.
 */
public interface BaseEntity<ID> extends Serializable {

    public ID getID();

    public void setID(ID id);

    public Date getCreatedDt();

    public void setCreatedDt(Date createdDt);

    public Date getUpdatedDt();

    public void setUpdatedDt(Date updatedDt);

}
