package com.blogproject.core.models.Impl;

import com.blogproject.core.Utils.Utils;
import com.blogproject.core.models.Blogs;
import com.blogproject.core.models.PublishBlogModel;
import com.blogproject.core.service.PublishBlogService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class, adapters = PublishBlogModel.class,defaultInjectionStrategy= DefaultInjectionStrategy.OPTIONAL)
public class PublishBlogModelImpl implements PublishBlogModel {

    @Self
    private SlingHttpServletRequest request;

    @OSGiService
    private PublishBlogService publishBlogService;

    @ScriptVariable
    private Page currentPage;


    private List<Blogs> blogList;

    @PostConstruct
    protected void init(){
        blogList = new ArrayList<>();
        int maxBlogs = publishBlogService.getMaxBlogs();

        String requestParam = request.getParameter("date");
        ResourceResolver resolver = request.getResourceResolver();
        Resource currPage = resolver.getResource(currentPage.getPath());
        PageManager pageManager = resolver.adaptTo(PageManager.class);
        if(pageManager != null){
            Page page = pageManager.getContainingPage(currPage);
            if(page != null){
                Iterator<Page> childPages = page.listChildren();
                int counter = 0;
                while(childPages.hasNext() && counter < maxBlogs){
                    Page childPage = childPages.next();
                    String title = childPage.getTitle() == null ?"Default Title":childPage.getTitle();
                    String path = childPage.getPath() == null ?"Default Path":childPage.getPath()+".html";
                    String description = childPage.getDescription() == null ?"Default Description":childPage.getDescription();
                    String formattedDate = Utils.formatDate(childPage);
                    String photo = Utils.getThumbnailPath(request,childPage);

                    Blogs blog = new Blogs(title, path, formattedDate, photo, description);
                    blogList.add(blog);
                    counter++;
                }
            }
        }
        if(requestParam != null){
            blogList = filterBlogList(blogList,requestParam);
        }
    }
    public List<Blogs> filterBlogList(List<Blogs> blogList, String date){
        for(Blogs blog : blogList){
            if(!blog.getDate().equals(date)){
                blogList.remove(blog);
            }
        }
        return blogList;
    }
    @Override
    public List<Blogs> getBlogList() {
        return blogList;
    }
}
