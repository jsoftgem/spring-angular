package com.jsofttechnologies.services.util;

import java.util.List;
import java.util.Map;

/**
 * Created by Jerico on 12/10/2015.
 */
public interface PageService<T, ID> {

    public T find(ID id);

    public List<T> list();

    public Object list(String q, String fields, String sort, Integer size, Map<String, String[]> param);

    public T update(ID id, T t) throws Exception;

    public T update(T t) throws Exception;

    public void create(T t) throws Exception;

    public void delete(ID id);

    public void preUpdate(T t) throws Exception;

    public void prePersist(T t) throws Exception;

    public T patch(ID id, Map<String, String[]> param);
}
