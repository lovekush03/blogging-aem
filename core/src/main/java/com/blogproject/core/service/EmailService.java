package com.blogproject.core.service;



import java.util.List;

public interface EmailService {
    String getServiceName();
    String getServiceNumber();
    String getServiceURL();
    List<EmailService> getPracticeFactoryConfigs();
}
