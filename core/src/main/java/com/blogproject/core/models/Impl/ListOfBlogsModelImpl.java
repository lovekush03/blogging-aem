package com.blogproject.core.models.Impl;

import com.blogproject.core.models.Blogs;
import com.blogproject.core.models.ListOfBlogsModel;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class, adapters = ListOfBlogsModel.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ListOfBlogsModelImpl implements ListOfBlogsModel {

    private static final Logger LOG = LoggerFactory.getLogger(ListOfBlogsModelImpl.class);

    @ValueMapValue
    private String parentPath;

    @SlingObject
    ResourceResolver resourceResolver;

    private List<Blogs> blogList;


    @PostConstruct
    protected void init() {
        blogList = new ArrayList<>();

        LOG.info("Initializing ListOfBlogsModelImpl");
        LOG.info("Parent Path: {}", parentPath);


        Resource resource = resourceResolver.getResource(parentPath);
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        if(pageManager != null) {
            Page parentPage = pageManager.getContainingPage(resource);
                Iterator<Page> pageIterator = parentPage.listChildren();
                while(pageIterator.hasNext()){
                    Page childPage = pageIterator.next();
                    String title = childPage.getTitle().isEmpty() ?"Default Title":childPage.getTitle();
                    String path = childPage.getPath().isEmpty()?"Default Path":childPage.getPath()+".html";
                    Calendar date = childPage.getProperties().get("jcr:created", Calendar.class) ;
                    String formattedDate = formatDate(date).isEmpty()?"Default Date":formatDate(date);
                    String photo = getThumbnail(childPage.getPath()).isEmpty()?"Default Photo":getThumbnail(childPage.getPath());
                    String desc = childPage.getDescription().isEmpty()?"Default Description":childPage.getDescription();
                    //Create a Blog Object
                    Blogs blog = new Blogs(
                            title,
                            path,
                            formattedDate,
                            photo,
                            desc
                    );
                    blogList.add(blog);
                }
        }
    }
    public String formatDate(Calendar date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy",Locale.ENGLISH);
        return formatter.format(date.getTime()) ;
    }
    public String getThumbnail(String pagePath){
        String ImagePath = pagePath + "/jcr:content/root/responsivegrid_291805153/banner";
        Resource bannerNode = resourceResolver.getResource(ImagePath);

        String ImageLink = "";
        try{
            ImageLink = bannerNode.getValueMap().get("bannerImage",String.class);
        } catch (Exception e) {
            return "Default Image Link";
        }
        return ImageLink;
    }
    @Override
    public List<Blogs> getBlogList() {
        return blogList;
    }
    @Override
    public String getParentPath() {
        return parentPath;
    }

}
