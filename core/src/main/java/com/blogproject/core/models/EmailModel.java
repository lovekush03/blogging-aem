package com.blogproject.core.models;

import com.blogproject.core.config.PracticeFactoryConfig;
import com.blogproject.core.service.EmailService;

import java.util.List;

public interface EmailModel {
    String getServiceName();
    String getServiceNumber();
    String getServiceURL();
    List<EmailService> getPracticeFactoryConfigs();
}
