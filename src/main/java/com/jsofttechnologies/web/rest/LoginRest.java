package com.jsofttechnologies.web.rest;

import com.jsofttechnologies.model.jpa.UserJPA;
import com.jsofttechnologies.services.ds.ProjectEntityManager;
import com.jsofttechnologies.util.PasswordHash;
import com.jsofttechnologies.web.util.BaseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Jerico on 15/10/2015.
 */
@RestController
@RequestMapping("login")
public class LoginRest extends BaseRest {

    @Autowired
    private ProjectEntityManager projectEntityManager;

    @RequestMapping(method = RequestMethod.POST)
    public void login(@RequestBody Object o) throws LoginException {

        LinkedHashMap<String,String> map = getEntity(o,String.class,String.class);

        String username = map.get("username");
        String password = map.get("password");

        EntityManager entityManager = projectEntityManager.getEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<UserJPA> criteriaQuery = cb.createQuery(UserJPA.class);

        Root<UserJPA> root = criteriaQuery.from(UserJPA.class);

        criteriaQuery.select(root).where(cb.or(cb.equal(root.get("username"), username), cb.equal(root.get("email"), username)));

        TypedQuery<UserJPA> userJPATypedQuery = entityManager.createQuery(criteriaQuery);

        List<UserJPA> resultList = userJPATypedQuery.getResultList();

        if (resultList != null && !resultList.isEmpty()) {

            UserJPA userJPA = resultList.get(0);


            try {
                if (PasswordHash.validatePassword(password, userJPA.getPassword())) {

                } else {
                    throw new LoginException("Invalid username and password");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw new LoginException("Invalid username and password");
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
                throw new LoginException("Invalid username and password");

            }


        } else {
            throw new LoginException("Invalid username and password");
        }

    }

    @ExceptionHandler(LoginException.class)
    public
    @ResponseBody
    ResponseEntity<String> loginException(HttpServletRequest req, Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
