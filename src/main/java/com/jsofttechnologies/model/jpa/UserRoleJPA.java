package com.jsofttechnologies.model.jpa;

import com.jsofttechnologies.model.impl.UserRoleImpl;
import com.jsofttechnologies.model.util.BaseEntityListener;
import com.jsofttechnologies.model.util.RestAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Jerico on 15/10/2015.
 */
@Entity(name="spUserRole")
@EntityListeners({
        BaseEntityListener.class
})
@Table(name = "sp_user_role")
public class UserRoleJPA extends UserRoleImpl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id", nullable = false)
    @Override
    public Long getID() {
        return super.getID();
    }

    @ElementCollection(targetClass = RestAction.class)
    @Column(name = "user_role_actions", nullable = false)
    @Enumerated(EnumType.STRING)
    @Override
    public List<RestAction> getActions() {
        return super.getActions();
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_dt")
    @Override
    public Date getUpdatedDt() {
        return super.getUpdatedDt();
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_dt")
    @Override
    public Date getCreatedDt() {
        return super.getCreatedDt();
    }
}
