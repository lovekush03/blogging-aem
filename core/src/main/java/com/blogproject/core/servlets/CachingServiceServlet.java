package com.blogproject.core.servlets;

import com.blogproject.core.service.CachingService;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component(service = Servlet.class,
        name = "Caching Service Implementation Servlet",
        property = {
        "sling.servlet.paths=/bin/cacheImpl",
        "sling.servlet.method=GET"
})
public class CachingServiceServlet extends SlingSafeMethodsServlet {
    List<CachingService> serviceList;

    @Reference(
            service = CachingService.class,
            policy = ReferencePolicy.DYNAMIC,
            cardinality = ReferenceCardinality.MULTIPLE
    )
    public void bindCachingServices(CachingService cachingService){
        if(serviceList == null){
            serviceList = new ArrayList<>();
        }
        serviceList.add(cachingService);
    }
    public void unbindCachingServices(CachingService cachingService){
        serviceList.remove(cachingService);
    }
    @Override
    protected void doGet(SlingHttpServletRequest request,  SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        List<JsonObject> serviceJsonList = new ArrayList<>();
        for(CachingService service : serviceList){
            JsonObject serviceObj = new JsonObject();
            serviceObj.addProperty("Implementation",service.getCachingImplementation());
            serviceJsonList.add(serviceObj);
        }
        response.getWriter().write(serviceJsonList.toString());
    }
}
