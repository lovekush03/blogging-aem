package com.blogproject.core.servlets;

import com.blogproject.core.service.SearchByTextService;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletFilter;
import org.apache.sling.servlets.annotations.SlingServletName;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, name = "Search Servlet")
//@SlingServletResourceTypes(resourceTypes = "blogproject/components/page", methods = {"GET"})
@SlingServletPaths(value = "/bin/searchServlet")
public class SearchServlet extends SlingSafeMethodsServlet {
    @Reference
    SearchByTextService service;

    @Override
    protected void doGet( SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ResourceResolver resolver = request.getResourceResolver();
        JsonObject resultJson = service.getResult(resolver,"blog");
        response.getWriter().write(resultJson.toString());
    }
}
