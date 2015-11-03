package com.jsofttechnologies.model.asm;

import com.jsofttechnologies.model.impl.AppImpl;
import com.jsofttechnologies.model.jpa.AppJPA;
import com.jsofttechnologies.model.resource.AppResource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Jerico on 04/11/2015.
 */
public class AppResourceASM extends ResourceAssemblerSupport<AppJPA, AppResource> {

    private Class controllerClass;

    public AppResourceASM(Class controllerClass) {
        super(controllerClass, AppResource.class);
        this.controllerClass = controllerClass;
    }

    public AppResourceASM(Class<?> controllerClass, Class<AppResource> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public AppResource toResource(AppJPA appJPA) {
        AppResource appResource = new AppResource();
        appResource.setCreatedDt(appJPA.getCreatedDt());
        appResource.setUpdatedDt(appJPA.getUpdatedDt());
        appResource.setDescription(appJPA.getDescription());
        appResource.setName(appJPA.getName());
        appResource.setTitle(appJPA.getTitle());
        appResource.setIndex(appJPA.getIndex());
        return appResource;
    }
}
