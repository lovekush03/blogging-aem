package com.blogproject.core.servlets;

import com.blogproject.core.service.JsonApiService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
@Component(
        service = Servlet.class,
        name = "Json Api Servlet",
        property = {
                "sling.servlet.paths=/bin/jsonServlet",
                "sling.servlet.methods=GET"
        }
)
public class JsonDataSevlet extends SlingSafeMethodsServlet {
    @Reference
    JsonApiService service;

    @Override
    protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write(service.getTodos().toString());
    }
}
