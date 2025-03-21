package com.blogproject.core.models.impl;

import com.blogproject.core.Utils.Utils;
import com.blogproject.core.models.ListingModel;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class, adapters = ListingModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ListingModelImpl implements ListingModel {

    @SlingObject
    private ResourceResolver resolver;

    @ScriptVariable
    private Page currentPage; // Individual Blog Page

    private Set<String> months;
    private String parentPagePath;

    @PostConstruct
    public void init() {
        months = new HashSet<>();

        if (currentPage == null || resolver == null) {
            return; // Avoid null pointer issues
        }

        Resource resource = resolver.getResource(currentPage.getPath());
        if (resource != null) {
            Resource parentResource = resource.getParent();
            parentPagePath = parentResource.getPath();
            if (parentResource != null) {
                PageManager pageManager = resolver.adaptTo(PageManager.class);
                if (pageManager != null) {
                    Page parentPage = pageManager.getContainingPage(parentResource);
                    if (parentPage != null) {
                        Iterator<Page> childPagesIterator = parentPage.listChildren();
                        while (childPagesIterator.hasNext()) {
                            Page childPage = childPagesIterator.next();
                            String createdDate = Utils.formatDate(childPage);
                            months.add(createdDate);
                        }
                    }
                }
            }
        }
    }



    @Override
    public Set<String> getMonths() {
        return months;
    }

    @Override
    public String getParentPagePath() {
        return parentPagePath+".html";
    }
}
