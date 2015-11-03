package com.jsofttechnologies.web.rest;

import com.jsofttechnologies.model.asm.AppResourceASM;
import com.jsofttechnologies.model.jpa.AppJPA;
import com.jsofttechnologies.model.resource.AppResource;
import com.jsofttechnologies.services.app.AppService;
import com.jsofttechnologies.services.util.PageService;
import com.jsofttechnologies.web.util.PageResourceImpl;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jerico on 04/11/2015.
 */
@RestController
@RequestMapping("services/apps")
public class AppRest extends PageResourceImpl<AppJPA, AppResource, Long> {

    public AppRest() {
        super(AppJPA.class, AppResourceASM.class, AppService.class);
    }

}
