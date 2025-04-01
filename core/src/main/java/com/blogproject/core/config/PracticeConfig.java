package com.blogproject.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(
        name = "Practice OSGi configuration",
        description = "Various OSGi Configuration fields "
)
public @interface PracticeConfig {
    @AttributeDefinition(
            name = "String type OSGi field",
            description = "This is String Type OSGi Field",
            type = AttributeType.STRING
    )
    String name() default "Lovepreet Singh";
    @AttributeDefinition(
            name = "Integer Type OSGi field",
            description = "This is a Integer Type Field",
            type = AttributeType.INTEGER
    )
    int number() default 0;
    @AttributeDefinition(
            name = "Boolean Type OSGi field",
            description = "This is boolean Type OSGi field",
            type = AttributeType.BOOLEAN
    )
    boolean isChecked() default true;
    @AttributeDefinition(
            name = "String Array OSGi configuration",
            description = "This is  a String Array Type OSGi configuration",
            type = AttributeType.STRING
    )
    String[] getCountries() default {"in" , "aus"};
    @AttributeDefinition(
            name = "Dropdown OSGi configuration type",
            description = "This is DropDown Type of OSGi configuration",
            type = AttributeType.STRING,
            options = {
                    @Option(label = "QA" , value = "qa"),
                    @Option(label = "DEV" , value = "dev"),
                    @Option(label = "PROD" , value = "prod")
            }
    )
    String getRunmode() default "Select a Runmode";
}
