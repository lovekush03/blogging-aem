package com.blogproject.core.models.Impl;

import com.blogproject.core.models.ContentModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, adapters = ContentModel.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ContentModelImpl implements ContentModel {

    @ValueMapValue(name="text")
    private String content;

    @Override
    public String getContent() {
        return content == null ? "No Blog Content" : content;
    }
}
