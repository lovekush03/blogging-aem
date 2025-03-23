package com.blogproject.core.models.Impl;

import com.adobe.granite.security.user.UserProperties;
import com.adobe.granite.security.user.UserPropertiesManager;
import com.adobe.granite.security.user.UserPropertiesService;
import com.blogproject.core.Utils.Utils;
import com.blogproject.core.models.BlogHeadingModel;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.annotation.PostConstruct;
import javax.jcr.Session;

@Model(adaptables = SlingHttpServletRequest.class, adapters = BlogHeadingModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogHeadingModelImpl implements BlogHeadingModel {

    @Self
    private SlingHttpServletRequest request;

    @ScriptVariable
    private Page currentPage;

    private String date;
    private String author;

    @PostConstruct
    public void init() {
        date = Utils.formatDate(currentPage);
        //Fetch Logged in User
        author = getLoggedInUserName();
    }

    private String getLoggedInUserName() {
        ResourceResolver resolver = request.getResourceResolver();
        try{
            Session session = resolver.adaptTo(Session.class);
            if(session != null) {
                String userId = session.getUserID();
                UserPropertiesManager upm = resolver.adaptTo(UserPropertiesManager.class);
                if(upm != null) {
                    UserProperties userProperties = upm.getUserProperties(userId, UserPropertiesService.PROFILE_PATH);
                    if(userProperties != null) {
                        String fullName = userProperties.getProperty("profile/fullName");
                        return fullName != null ? fullName : userId;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown User";
    }


    @Override
    public String getHeading() {
        return (currentPage.getTitle() != null) ? currentPage.getTitle() : "Default Title" ;
    }

    @Override
    public String getDateOfPublish() {
        return date;
    }

    @Override
    public String getAuthor() {
        return author;
    }
}
