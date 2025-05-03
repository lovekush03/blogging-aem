package com.blogproject.core.models.Impl;
import com.blogproject.core.models.HeaderModel;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = HeaderModel.class,
        resourceType = "blogproject/components/header",
        defaultInjectionStrategy= DefaultInjectionStrategy.OPTIONAL)
@Exporter(name="jackson" ,extensions = "json")
public class HeaderModelImpl implements HeaderModel {
    @Inject
    private SlingHttpServletRequest request;

    @Inject
    @Via("resource")
    @Default(values = "Default Text")
    private String logoText;

    @Inject
    @Via("resource")
    @Default(values = "Default Image")
    private String logoImage;

    @Inject
    private Resource resource;

    @Inject
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

        navItems = new ArrayList<>();
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

}
