package com.blogproject.core.models;

import java.util.List;
import java.util.Map;

public interface HeaderModel {
        String getLogoImage();
    String getLogoText();
    List<Map<String,String>> getNavItems();
    String getPage();
    String getResource();
}