package com.blogproject.core.models.Impl;

import com.blogproject.core.models.HeaderModel;
import com.blogproject.core.models.NavItems;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class, adapters = HeaderModel.class, defaultInjectionStrategy= DefaultInjectionStrategy.OPTIONAL)
public class HeaderModelImpl implements HeaderModel {
    @Self
    private SlingHttpServletRequest request;

    @ValueMapValue
    @Default(values = "Default Title")
    private String logoText;

    @ValueMapValue
    @Default(values = "Default Image")
    private String logoImage;



    @ValueMapValue(name = "navTitle")
    private List<String> navTitle;

    @ValueMapValue(name = "navLink")
    private List<String> navLink;


    private List<NavItems> navItems;


    @Override
    public String getLogoImage() {
        return logoImage;
    }

    @Override
    public String getLogoText() {
        return logoText;
    }

    @PostConstruct
    public void init() {
        navItems = new ArrayList<>();
        for(int i = 0 ; i < navTitle.size() ; i++) {
            String title = navTitle.get(i);
            String pagePath = navLink.get(i);
            String hideProperty = "";
            ResourceResolver resolver = request.getResourceResolver();
            PageManager pageManager = resolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage(pagePath);
            if(page != null) {
                hideProperty = page.getProperties().get("hideInNav", "Default");
            }
            navItems.add(new NavItems(title,pagePath+".html",hideProperty));
        }
    }


    @Override
    public List<NavItems> getNavItems() {
        return navItems;
    }
}
