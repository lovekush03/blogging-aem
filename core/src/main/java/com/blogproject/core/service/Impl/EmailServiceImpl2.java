package com.blogproject.core.service.Impl;

import com.blogproject.core.config.PracticeConfig;
import com.blogproject.core.service.EmailService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import java.util.Collections;
import java.util.List;

@Component(service = EmailService.class,  name = "Email Service 2" , immediate = true, property={
        "Impl=Yahoo",
        "Org=Microsoft"
})
@Designate(ocd = PracticeConfig.class)
public class EmailServiceImpl2  implements EmailService {
    @Override
    public String getServiceName() {
        return "Called From Yahoo ";
    }

    @Override
    public String getServiceNumber() {
        return "Called From Yahoo ";
    }

    @Override
    public String getServiceURL() {
        return "www.yahoo.com";
    }

    @Override
    public List<EmailService> getPracticeFactoryConfigs() {
        return Collections.emptyList();
    }
}
