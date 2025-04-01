package com.blogproject.core.models.Impl;

import com.blogproject.core.models.HeaderModel;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.*;

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



    @ScriptVariable
    private Resource resource;

    @ScriptVariable
    private Page currentPage;

    private List<Map<String,String>> navItems;

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
        //ValueMap Approach
        //resource using sling object annotation .path -> /conf/blogproject/settings/wcm/templates/home-page/structure/jcr:content/root/header
        //resource using script variable annotation -> same as above

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
    public List<Map<String, String>> getNavItems() {
        return navItems;
    }
    @Override
    public String getPage() {
        return currentPage.getPath();
    }
    @Override
    public String getResource() {
        return resource.getPath();
    }
}
