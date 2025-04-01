package com.blogproject.core.servlets;

import com.blogproject.core.config.PracticeFactoryConfig;
import com.blogproject.core.service.EmailService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(service = Servlet.class,name = "Config Instance Listing Servlet ", property = {
        "sling.servlet.paths=/bin/configInstanceList",
        "sling.servlet.method=GET"
})
public class ConfigListing extends SlingSafeMethodsServlet {
    private volatile List<EmailService> configList;
    private List<JsonObject> configJsonList;
    @Reference(
            service = EmailService.class,
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            target = "(Impl=Yahoo)"
    )
    public void bindEmailService(EmailService emailService) {
        if(configList == null){
            configList = new ArrayList<>();
        }
        configList.add(emailService);
    }
    public void unbindEmailService(EmailService emailService) {
        configList.remove(emailService);
    }
    public List<JsonObject> getConfigJsonList(List<EmailService> configList) {
        List<JsonObject> configJsonList = new ArrayList<>();
        for(EmailService emailService : configList){
            JsonObject configJson = new JsonObject();
            configJson.addProperty("Service Number", emailService.getServiceNumber());
            configJson.addProperty("Service Name", emailService.getServiceName());
            configJson.addProperty("Service URL", emailService.getServiceURL());
            configJsonList.add(configJson);
        }
        return configJsonList;
    }
    @Override
    protected void doGet( SlingHttpServletRequest request,  SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        configJsonList = getConfigJsonList(configList);
        response.getWriter().write(configJsonList.toString());
    }
}
