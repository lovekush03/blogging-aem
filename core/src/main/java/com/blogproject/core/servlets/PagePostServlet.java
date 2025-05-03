package com.blogproject.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, name = "Post Servlet for changing Page Title")
@SlingServletResourceTypes(resourceTypes = "blogproject/components/page", methods = {"POST","GET"})
public class PagePostServlet extends SlingAllMethodsServlet {
    @Override
    protected void doGet( SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("{Message: New Title is not Passed from Get Method}");

    }

    @Override
    protected void doPost(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
        String newTitle = request.getParameter("newTitle");
        if(newTitle == null){
            response.getWriter().write("{Message: New Title is not Passed}");
            return;
        }
        Resource resource = request.getResource();
        if(resource == null){
            response.getWriter().write("{Message : Resource is NULL}");
            return ;
        }
        ResourceResolver resolver = request.getResourceResolver();

        //PageManager pm = resolver.adaptTo(PageManager.class);
        //Page page  = pm.getPage(resource.getPath());
        Resource PagejcrNode = resolver.getResource(resource.getPath());
        ModifiableValueMap map = PagejcrNode.adaptTo(ModifiableValueMap.class);
        if(map.containsKey("jcr:title")){
            map.put("jcr:title",newTitle);
            resolver.commit();
            response.getWriter().write("{message : Title Updated Successfully Please Check CRX/DE}");
        }
    }
}
