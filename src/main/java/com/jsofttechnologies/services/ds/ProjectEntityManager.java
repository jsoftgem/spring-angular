package com.jsofttechnologies.services.ds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by Jerico on 14/10/2015.
 */
@Service
public class ProjectEntityManager {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    private static EntityManager entityManager;


    public EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        synchronized (entityManager) {
            return entityManager;
        }
    }


}
