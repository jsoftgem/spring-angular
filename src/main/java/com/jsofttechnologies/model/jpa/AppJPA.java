package com.jsofttechnologies.model.jpa;

import com.jsofttechnologies.model.impl.AppImpl;
import com.jsofttechnologies.model.util.BaseEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "AppJPA")
@Table(name = "flow_group")
@EntityListeners({
        BaseEntityListener.class
})
public class AppJPA extends AppImpl {

    @Id
    @Column(name = "group_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getID() {
        return super.getID();
    }

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Override
    public Date getCreatedDt() {
        return super.getCreatedDt();
    }

    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Override
    public Date getUpdatedDt() {
        return super.getUpdatedDt();
    }

    @Column(name = "description")
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Column(name = "group_icon_uri")
    @Override
    public String getIconUri() {
        return super.getIconUri();
    }

    @Column(name = "group_name", nullable = false)
    @Override
    public String getName() {
        return super.getName();
    }

    @Column(name = "group_title", nullable = false)
    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Column(name = "group_index")
    @Override
    public Integer getIndex() {
        return super.getIndex();
    }
}
