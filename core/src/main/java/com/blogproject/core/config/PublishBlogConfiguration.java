package com.blogproject.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Publish Blog Listing Configuration",
        description = "This Configuration is for passing Number of blogs that can be listed on the Publish Blog Page"
)
public @interface PublishBlogConfiguration {
    @AttributeDefinition(
            name = "No of Blogs",
            description = "No of Blogs Listed on the page"
    )
    int maxBlogs() default 3;
}
