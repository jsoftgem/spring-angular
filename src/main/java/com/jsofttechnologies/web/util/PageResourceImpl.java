package com.jsofttechnologies.web.util;

import com.jsofttechnologies.services.util.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Jerico on 08/10/2015.
 */
public abstract class PageResourceImpl<T, TR extends ResourceSupport, ID> implements PageResource<T, TR, ID> {

    private final Class<T> classType;
    private final Class<? extends ResourceAssemblerSupport<T, TR>> resourceASMClass;
    private final Class<? extends PageService<T, ID>> pageServiceClass;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    protected HttpServletRequest request;


    public PageResourceImpl(Class<T> classType, Class<? extends ResourceAssemblerSupport<T, TR>> resourceASMClass, Class<? extends PageService<T, ID>> pageServiceClass) {
        this.classType = classType;
        this.resourceASMClass = resourceASMClass;
        this.pageServiceClass = pageServiceClass;
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Object list(@RequestParam(required = false) String q,
                @RequestParam(required = false) String fields,
                @RequestParam(required = false) String sort, @RequestParam(required = false)
                Integer size) {

        if (q == null && fields == null && sort == null && size == null) {
            PageService<T, ID> pageService = applicationContext.getBean(pageServiceClass);
            List<T> list = pageService.list();
            if (list != null && !list.isEmpty()) {
                try {
                    return new ResponseEntity(((ResourceAssemblerSupport<T, TR>) resourceASMClass.getConstructors()[0].newInstance(getClass())).toResources(list), HttpStatus.OK);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        PageService<T, ID> pageService = applicationContext.getBean(pageServiceClass);

        Map<String, String[]> param = request.getParameterMap();

        Object list = pageService.list(q, fields, sort, size, param);

        return list;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<TR> update(@RequestBody T t, @PathVariable ID id) throws Exception {
        PageService<T, ID> pageService = applicationContext.getBean(pageServiceClass);
        t = pageService.update(id, t);
        TR resource = null;
        if (t != null) {
            try {
                resource = ((ResourceAssemblerSupport<T, TR>) resourceASMClass.getConstructors()[0].newInstance(getClass())).toResource(t);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<TR>(resource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TR> create(@RequestBody T t) throws Exception {
        PageService<T, ID> pageService = applicationContext.getBean(pageServiceClass);
        pageService.create(t);
        TR resource = null;
        if (t != null) {
            try {
                resource = ((ResourceAssemblerSupport<T, TR>) resourceASMClass.getConstructors()[0].newInstance(getClass())).toResource(t);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<TR>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable ID id) {
        PageService<T, ID> pageService = applicationContext.getBean(pageServiceClass);
        pageService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<TR> get(@PathVariable ID id) {
        PageService<T, ID> pageService = applicationContext.getBean(pageServiceClass);
        T t = pageService.find(id);
        TR resource = null;
        if (t != null) {
            try {
                resource = ((ResourceAssemblerSupport<T, TR>) resourceASMClass.getConstructors()[0].newInstance(getClass())).toResource(t);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<TR>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<TR> patch(@PathVariable ID id) {
        PageService<T, ID> pageService = applicationContext.getBean(pageServiceClass);
        T t = pageService.patch(id, request.getParameterMap());
        TR resource = null;
        if (t != null) {
            try {
                resource = ((ResourceAssemblerSupport<T, TR>) resourceASMClass.getConstructors()[0].newInstance(getClass())).toResource(t);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<TR>(resource, HttpStatus.OK);
    }

    @RequestMapping("/info")
    public
    @ResponseBody
    String info() {
        StringBuilder builder = new StringBuilder();
        builder.append("Page Resource Implementation\n");
        builder.append("Version 1.0\n");
        builder.append("Resource Class: ").append(getClass().getName()).append("\n");
        builder.append("Page Resource concept by Jerico de Guzman");
        return builder.toString();
    }
}
