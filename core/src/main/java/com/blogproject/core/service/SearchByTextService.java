package com.blogproject.core.service;

import com.google.gson.JsonObject;
import org.apache.sling.api.resource.ResourceResolver;

public interface SearchByTextService {
    public JsonObject getResult(ResourceResolver resolver , String searchText);
}
