package com.blogproject.core.models.Impl;

import com.blogproject.core.Utils.Utils;
import com.blogproject.core.models.BlogHeadingModel;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Model(adaptables = SlingHttpServletRequest.class, adapters = BlogHeadingModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogHeadingModelImpl implements BlogHeadingModel {

//    @ValueMapValue
//    private String heading;
//
//    @ValueMapValue
//    private String dateOfPublish;
//
//    @ValueMapValue
//    private String author;

    @ScriptVariable
    private Page currentPage;

    private String date;

    @PostConstruct
    public void init() {
//        Calendar curr_date = currentPage.getProperties().get("jcr:created", Calendar.class);
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        date = formatter.format(curr_date.getTime());
        date = Utils.formatDate(currentPage);
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
        return currentPage.getProperties().get("jcr:createdBy", String.class);
    }
}
