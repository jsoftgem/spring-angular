package com.jsofttechnologies.model.util;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Jerico on 15/10/2015.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceAssembler {
    Class controllerClass();

    Class<? extends ResourceAssemblerSupport> resourceAssemblerSupport();
}
