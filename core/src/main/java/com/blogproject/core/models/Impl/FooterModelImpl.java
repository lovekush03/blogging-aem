package com.blogproject.core.models.Impl;

import com.blogproject.core.models.FooterModel;
import com.blogproject.core.models.NavItems;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class , adapters = FooterModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModelImpl implements FooterModel {

    @ValueMapValue(name = "navTitle")
    List<String> navTitle;

    @ValueMapValue(name = "navLink")
    List<String> navLinks;

    @ScriptVariable
    private Resource resource;

    private List<Map<String,String>> navItems;

    @PostConstruct
    public void init() {
        String ResourcePath = resource.getPath();

        navItems = new ArrayList<Map<String,String>>();
        Resource actionNode = resource.getChild("actions");
        if (actionNode != null) {
            //get Child node of the actionNode -> all
            Iterator<Resource> navItemsResource = actionNode.listChildren();
            while(navItemsResource.hasNext()){
                Map<String,String> navItem;
                navItem = new HashMap<>();
                Resource navItemResource = navItemsResource.next();
                String navItemTitle = navItemResource.getValueMap().get("navTitle", "Default Title");
                String navItemLink = navItemResource.getValueMap().get("navLink",String.class)+".html";
                navItem.put("title", navItemTitle);
                navItem.put("link", navItemLink);
                navItems.add(navItem);
            }
        }
    }

    @Override
    public List<Map<String,String>>  getNavItems() {
        return navItems;
    }
}
