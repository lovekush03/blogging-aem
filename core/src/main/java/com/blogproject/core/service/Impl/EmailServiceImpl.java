package com.blogproject.core.service.Impl;

import com.blogproject.core.config.PracticeFactoryConfig;
import com.blogproject.core.service.EmailService;
import org.osgi.service.component.annotations.*;
import org.osgi.service.component.propertytypes.ServiceRanking;
import org.osgi.service.metatype.annotations.Designate;

import java.util.ArrayList;
import java.util.List;

@Component(service = EmailService.class, name = "Email Service 1" ,immediate = true, property={
        "Impl=Gmail",
        "Org=Google"
})
@Designate(ocd = PracticeFactoryConfig.class, factory = true)
//@ServiceRanking(1001)
public class EmailServiceImpl implements EmailService {

    private String serviceName;
    private String serviceNumber;
    private String serviceURL;

    private List<EmailService> configList ;

    @Activate
    @Modified
    void activate(PracticeFactoryConfig practiceFactoryConfig) {
        this.serviceName = practiceFactoryConfig.getServiceName();
        this.serviceNumber = practiceFactoryConfig.getServiceNumber();
        this.serviceURL = practiceFactoryConfig.getServiceURL();
//        configList.add(practiceFactoryConfig);
    }

    @Reference(service = EmailService.class , cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bindConfig(EmailService practiceFactoryConfig) {
        if(configList == null){
            configList = new ArrayList<>();
        }
        configList.add(practiceFactoryConfig);
    }

    public void unbindConfig(EmailService practiceFactoryConfig){
        configList.remove(practiceFactoryConfig);
    }

    @Override
    public List<EmailService> getPracticeFactoryConfigs() {
        return configList;
    }
    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String getServiceNumber() {
        return serviceNumber;
    }

    @Override
    public String getServiceURL() {
        return serviceURL;
    }
}
