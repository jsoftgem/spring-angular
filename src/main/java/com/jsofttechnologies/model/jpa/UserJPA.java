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
@Entity(name = "spUser")
@EntityListeners({
        BaseEntityListener.class
})
@Table(name = "sp_user")
@XmlRootElement
public class UserJPA extends UserImpl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    public Long getID() {
        return super.getID();
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return super.getUsername();
    }

    @Email
    @Column(name = "email", nullable = false, unique = true)
    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Column(name = "password", nullable = false)
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Column(name = "created_dt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Override
    public Date getCreatedDt() {
        return super.getCreatedDt();
    }

    @Column(name = "update_dt")
    @Temporal(TemporalType.TIMESTAMP)
    @Override
    public Date getUpdatedDt() {
        return super.getUpdatedDt();
    }

    @Column(name = "full_name", nullable = false, length = 50)
    @Override
    public String getFullName() {
        return super.getFullName();
    }

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = UserRoleJPA.class, cascade = CascadeType.PERSIST)
    @Override
    public List<UserRoleImpl> getUserRoles() {
        return super.getUserRoles();
    }
}


