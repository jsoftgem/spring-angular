package com.jsofttechnologies.util;

import java.lang.reflect.Method;

/**
 * Created by Jerico on 15/10/2015.
 */
public class FieldHelper {

    public static String getFieldName(String methodName) {
        return methodName.replaceFirst("get", "");
    }

    public static String toMethodGet(String fieldName) {
        return "get" + (fieldName.replaceFirst(fieldName.charAt(0) + "", fieldName.toUpperCase().charAt(0) + ""));
    }

    public static String toMethodSet(String fieldName) {
        return "set" + (fieldName.replaceFirst(fieldName.charAt(0) + "", fieldName.toUpperCase().charAt(0) + ""));

    }

}
