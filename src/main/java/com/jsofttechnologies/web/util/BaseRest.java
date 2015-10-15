package com.jsofttechnologies.web.util;

import java.util.LinkedHashMap;

/**
 * Created by Jerico on 15/10/2015.
 */
public abstract class BaseRest {

    public <T, V> LinkedHashMap<T, V> getEntity(Object o, Class<T> keyType, Class<V> valueType) {
        return (LinkedHashMap<T, V>) o;
    }

}
