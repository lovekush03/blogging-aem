package com.blogproject.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Practice Factory Configuration",
        description = "Practice Factory Configuration Description"
)
public @interface PracticeFactoryConfig {
    @AttributeDefinition(
            name = "Service Number",
            description = "Service Number Function"
    )
    String getServiceNumber() default "#";
    @AttributeDefinition(
            name = "Service Name",
            description = "Service Name Function"
    )
    String getServiceName() default "Service #";
    @AttributeDefinition(
            name = "Service URL",
            description = "Service URL Function"
    )
    String getServiceURL() default "http://localhost:8080";
}
