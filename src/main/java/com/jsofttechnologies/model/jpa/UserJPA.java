package com.jsofttechnologies.model.jpa;

import com.jsofttechnologies.model.impl.UserImpl;
import com.jsofttechnologies.model.impl.UserRoleImpl;
import com.jsofttechnologies.model.util.BaseEntityListener;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Created by Jerico on 15/10/2015.
 */
@Entity(name = "flowUser")
@EntityListeners({
        BaseEntityListener.class
})
@Table(name = "flow_user")
@XmlRootElement
public class UserJPA extends UserImpl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "flow_user_id", nullable = false)
    public Long getID() {
        return super.getID();
    }

    @Column(name = "flow_username", nullable = false, unique = true)
    public String getUsername() {
        return super.getUsername();
    }

    @Email
    @Column(name = "flow_email", nullable = false, unique = true)
    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Column(name = "flow_password", nullable = false)
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Override
    public Date getCreatedDt() {
        return super.getCreatedDt();
    }

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Override
    public Date getUpdatedDt() {
        return super.getUpdatedDt();
    }


}


