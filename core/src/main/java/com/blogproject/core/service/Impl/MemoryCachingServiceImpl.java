package com.blogproject.core.service.Impl;

import com.blogproject.core.service.CachingService;
import org.osgi.service.component.annotations.Component;

@Component(service = CachingService.class, immediate = true)
public class MemoryCachingServiceImpl implements CachingService {
    @Override
    public String getCachingImplementation() {
        return "In Memory Caching Service Implementation";
    }
}
