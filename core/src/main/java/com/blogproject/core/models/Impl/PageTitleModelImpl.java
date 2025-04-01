package com.blogproject.core.models.Impl;

import com.blogproject.core.models.PageTitleModel;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(adaptables = SlingHttpServletRequest.class, adapters = PageTitleModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageTitleModelImpl implements PageTitleModel {
    @ValueMapValue(name = "pageLinks")
    private List<String> pageLinks;

    @ScriptVariable
    private PageManager pageManager;

    private List<Map<String,String>> pageObjects;

    @PostConstruct
    public void init() {
        pageObjects = new ArrayList<>();
        //Take all the links from the pageLinks and convert to page
        for (String pageLink : pageLinks) {
            Map<String,String> pageObject = new HashMap<>();



            if (pageManager != null) {
                Page page = pageManager.getPage(pageLink);
                String getTitle = page.getTitle() == null ? "No Output for getTitle" : page.getTitle();
                String getPageTitle = page.getPageTitle() == null ? "No Output for getPageTitle" : page.getPageTitle();
                pageObject.put("getTitle", getTitle);
                pageObject.put("getPageTitle", getPageTitle);
                pageObject.put("link",pageLink + ".html");
                pageObjects.add(pageObject);
            }

        }
    }

    @Override
    public List<Map<String,String>> getPageTitles() {
        return pageObjects;
    }
}
