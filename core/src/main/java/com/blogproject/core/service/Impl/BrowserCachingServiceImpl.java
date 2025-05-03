package com.blogproject.core.service.Impl;

import com.blogproject.core.service.CachingService;
import org.osgi.service.component.annotations.Component;

@Component(service = CachingService.class, immediate = true)
public class BrowserCachingServiceImpl implements CachingService {
    @Override
    public String getCachingImplementation() {
        return "This is Browser Caching Service Implementation.";
    }
}
