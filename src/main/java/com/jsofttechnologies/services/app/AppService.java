package com.jsofttechnologies.services.app;

import com.jsofttechnologies.model.jpa.AppJPA;
import com.jsofttechnologies.services.util.PageServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by Jerico on 04/11/2015.
 */
@Service
public class AppService extends PageServiceImpl<AppJPA, Long> {

    public AppService() {
        super(AppJPA.class);
    }
}
