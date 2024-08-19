package com.haminime.photo.adapter;

import com.haminime.photo.enumeration.AuthPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AdapterProvider {

    private final ApplicationContext applicationContext;

    @Autowired
    public AdapterProvider(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public AuthPlatformAdapter getAdapter(AuthPlatform platform) {
        return this.applicationContext.getBean(platform.getAdapterClass());
    }
}

