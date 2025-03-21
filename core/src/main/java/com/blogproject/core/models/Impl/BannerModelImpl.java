package com.blogproject.core.models.Impl;

import com.blogproject.core.models.BannerModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, adapters = BannerModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BannerModelImpl implements BannerModel {

    @ValueMapValue
    private String bannerImage;

    @ValueMapValue
    private String alternateText;

    @Override
    public String getBannerImage() {
        return bannerImage;
    }

    @Override
    public String getAlternateText() {
        return alternateText;
    }
}
