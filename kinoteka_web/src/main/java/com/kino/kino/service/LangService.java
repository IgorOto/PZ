package com.kino.kino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class LangService {
    ResourceLoader resourceLoader;

    @Autowired
    public LangService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource getLangResource(String slug){
        return resourceLoader.getResource("classpath:/static/json/" + slug.toUpperCase() + ".json");
    }
}
