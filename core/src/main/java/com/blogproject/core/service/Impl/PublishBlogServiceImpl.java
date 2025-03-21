package com.blogproject.core.service.Impl;

import com.blogproject.core.config.PublishBlogConfiguration;
import com.blogproject.core.service.PublishBlogService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = PublishBlogService.class, immediate = true)
@Designate(ocd = PublishBlogConfiguration.class)
public class PublishBlogServiceImpl implements PublishBlogService {
    private int maxBlogs;

    @Activate
    @Modified
    protected void activate(PublishBlogConfiguration config) {
        maxBlogs = config.maxBlogs();
    }

    @Override
    public int getMaxBlogs() {
        return maxBlogs;
    }
}
