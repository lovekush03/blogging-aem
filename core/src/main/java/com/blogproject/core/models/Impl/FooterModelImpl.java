package com.blogproject.core.models.Impl;

import com.blogproject.core.models.FooterModel;
import com.blogproject.core.models.NavItems;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class , adapters = FooterModel.class)
public class FooterModelImpl implements FooterModel {

    @ValueMapValue(name = "navTitle")
    List<String> navTitle;

    @ValueMapValue(name = "navLink")
    List<String> navLinks;

    private List<NavItems> navItems;

    @PostConstruct
    public void init() {
        navItems = new ArrayList<>();
        for(int i = 0 ; i < navTitle.size() ; i++) {
            navItems.add(new NavItems(navTitle.get(i), navLinks.get(i)+".html",""));
        }
    }

    @Override
    public List<NavItems> getNavItems() {
        return navItems;
    }
}
