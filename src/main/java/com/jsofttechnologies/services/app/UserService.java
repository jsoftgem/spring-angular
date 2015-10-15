package com.jsofttechnologies.services.app;

import com.jsofttechnologies.model.jpa.UserJPA;
import com.jsofttechnologies.services.util.PageServiceImpl;
import com.jsofttechnologies.util.PasswordHash;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerico on 15/10/2015.
 */
@Service
public class UserService extends PageServiceImpl<UserJPA, Long> {

    public UserService() {
        super(UserJPA.class);
    }


    @Override
    public void prePersist(UserJPA userJPA) throws Exception {
        // check if exists
        //using username
        Map<String, String[]> param = new HashMap<>();
        param.put("username", new String[]{userJPA.getUsername()});

        List<UserJPA> userJPAList = (List<UserJPA>) list(null, null, null, null, param);

        if (userJPAList != null && !userJPAList.isEmpty()) {
            throw new DuplicateKeyException("Username " + userJPA.getUsername() + " already exists.");
        }

        //using email
        param = new HashMap<>();
        param.put("email", new String[]{userJPA.getEmail()});

        userJPAList = (List<UserJPA>) list(null, null, null, null, param);

        if (userJPAList != null && !userJPAList.isEmpty()) {
            throw new DuplicateKeyException("Email " + userJPA.getEmail() + " already exists.");
        }

        if (userJPA.getPassword() != null) {
            String hashedPassword = PasswordHash.createHash(userJPA.getPassword());
            userJPA.setPassword(hashedPassword);
        }
    }


    @Override
    public void preUpdate(UserJPA userJPA) throws Exception {
        // check if exists
        //using username
        Map<String, String[]> param = new HashMap<>();
        param.put("username", new String[]{userJPA.getUsername()});

        if (userJPA.getUsername() != null) {
            List<UserJPA> userJPAList = (List<UserJPA>) list(null, null, null, null, param);

            if (userJPAList != null && !userJPAList.isEmpty()) {
                throw new DuplicateKeyException("Username " + userJPA.getUsername() + " already exists.");
            }
        }

        //using email
        param = new HashMap<>();
        param.put("email", new String[]{userJPA.getEmail()});
        if (userJPA.getEmail() != null) {
            List<UserJPA> userJPAList = (List<UserJPA>) list(null, null, null, null, param);
            if (userJPAList != null && !userJPAList.isEmpty()) {
                throw new DuplicateKeyException("Email " + userJPA.getEmail() + " already exists.");
            }
        }

        if (userJPA.getPassword() != null) {
            String hashedPassword = PasswordHash.createHash(userJPA.getPassword());
            userJPA.setPassword(hashedPassword);
        }
    }
}
