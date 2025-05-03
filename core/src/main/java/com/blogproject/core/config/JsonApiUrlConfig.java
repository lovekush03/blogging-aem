package com.blogproject.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Json Api URL Config")
public @interface JsonApiUrlConfig {
    @AttributeDefinition(
            name = "API URL",
            description = "String Function to return URL Api"
    )
    String getURL() default "https://jsonplaceholder.typicode.com/todos";
}
