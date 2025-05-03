package com.blogproject.core.service.Impl;

import com.blogproject.core.service.CachingService;
import org.osgi.service.component.annotations.Component;

@Component(service = CachingService.class , immediate = true)
public class DistributedCachingServiceImpl implements CachingService {
    @Override
    public String getCachingImplementation() {
        return "This is Distributed Caching Service Implementation";
    }
}
