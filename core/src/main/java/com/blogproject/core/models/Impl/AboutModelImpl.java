package com.blogproject.core.models.Impl;

import com.blogproject.core.models.AboutModel;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = SlingHttpServletRequest.class, adapters = AboutModel.class)
public class AboutModelImpl implements AboutModel {
    @ScriptVariable
    private Page currentPage;

    @Override
    public String getSummary() {
        return currentPage.getProperties().get("jcr:description", String.class) != null ? currentPage.getProperties().get("jcr:description", String.class) : "Default summary";
    }
}
