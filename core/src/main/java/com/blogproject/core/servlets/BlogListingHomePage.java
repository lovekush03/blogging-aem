package com.blogproject.core.servlets;

import com.blogproject.core.Utils.Utils;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;

@Component(service = Servlet.class, name="Blog Listing Servlet for Home Page", property = {
        "sling.servlet.resourceTypes=" + "blogproject/components/page",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.extensions=" + "json"
})
public class BlogListingHomePage extends SlingSafeMethodsServlet {
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ServletException, IOException {
        //Takes the page path from request Parameter
        //page path should be of Publish Blog Page bcz inside that we have all Blog Pages stored
        String pagePath = req.getParameter("page");

        if(pagePath == null || pagePath.isEmpty()){
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\" : \" Please Pass Path in Query Parameter\"");
        }

        ResourceResolver resolver = req.getResourceResolver();
        Resource page = resolver.getResource(pagePath);

        if(page == null){
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\" : \" Please Pass Path in Query Parameter\"");
        }

        PageManager pageManager = resolver.adaptTo(PageManager.class);
        Page currentPage = pageManager.getContainingPage(page);

        if(currentPage == null){
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\" : \" Could not find a Vlid AEM Page\"");
        }

        //Create JSON Array for Child Pages
        JsonArray jsonArray = new JsonArray();
        Iterator<Page> childPageIterator = currentPage.listChildren();
        while(childPageIterator.hasNext()){
            Page childPage = childPageIterator.next();
            try{
                JsonObject jsonChildPage = new JsonObject();
                jsonChildPage.addProperty("title", childPage.getTitle());
                jsonChildPage.addProperty("Path" , childPage.getPath());
                jsonChildPage.addProperty("description", childPage.getDescription());
                jsonChildPage.addProperty("createdDate",Utils.formatDate(childPage));
                jsonChildPage.addProperty("Thumbnail" , Utils.getThumbnailPath(req,childPage));

                jsonArray.add(jsonChildPage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //return the List of JSON Objects
        resp.setContentType("application/json");
        resp.getWriter().write(jsonArray.toString());
    }

}
