package com.jsofttechnologies.model.jpa;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Jerico on 15/10/2015.
 */
public class UserJPATest {

    @Test
    public void lookForColumnAnnotated() {

        Class<UserJPA> userJPAClass = UserJPA.class;


        Annotation[] annotationTypes = userJPAClass.getAnnotations();


        Field[] fields = userJPAClass.getDeclaredFields();

        Method[] method = userJPAClass.getDeclaredMethods();


        System.out.print(annotationTypes);


    }

}
