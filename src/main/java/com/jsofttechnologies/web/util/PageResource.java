package com.jsofttechnologies.web.util;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

/**
 * Created by Jerico on 13/10/2015.
 */
public interface PageResource<T, TR extends ResourceSupport, ID> {

    public Object list(String q, String fields, String sort, Integer size);

    public ResponseEntity<TR> update(T t, ID id) throws Exception;

    public ResponseEntity<TR> get(ID id);

    public ResponseEntity<TR> create(T t) throws Exception;

    public void delete(ID id);

    public ResponseEntity<TR> patch(ID id);

    public String info();
}
