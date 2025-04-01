package com.blogproject.core.models.Impl;

import com.blogproject.core.config.PracticeFactoryConfig;
import com.blogproject.core.models.EmailModel;
import com.blogproject.core.service.EmailService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import java.util.Collections;
import java.util.List;

@Model(adaptables = Resource.class, adapters = EmailModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EmailModelImpl implements EmailModel {

    @OSGiService(filter = "(component.name=Email Service 1)")
    private EmailService emailService;

    @Override
    public String getServiceNumber() {
        return emailService.getServiceNumber();
    }

    @Override
    public String getServiceName() {
        return emailService.getServiceName();
    }

    @Override
    public String getServiceURL() {
        return emailService.getServiceURL();
    }

    @Override
    public List<EmailService> getPracticeFactoryConfigs() {
        return emailService.getPracticeFactoryConfigs();
    }
}
