package com.jsofttechnologies.web.rest;

import com.jsofttechnologies.model.asm.UserResourceASM;
import com.jsofttechnologies.model.jpa.UserJPA;
import com.jsofttechnologies.model.resource.UserResource;
import com.jsofttechnologies.services.app.UserService;
import com.jsofttechnologies.web.util.PageResourceImpl;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jerico on 15/10/2015.
 */
@RestController
@RequestMapping("services/user")
public class UserRest extends PageResourceImpl<UserJPA, UserResource, Long> {

    public UserRest() {
        super(UserJPA.class, UserResourceASM.class, UserService.class);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public
    @ResponseBody
    ResponseEntity<String> duplicateKeyException(HttpServletRequest req, Exception exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
